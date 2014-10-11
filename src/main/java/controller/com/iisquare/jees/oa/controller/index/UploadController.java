package com.iisquare.jees.oa.controller.index;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.iisquare.jees.core.component.PermitController;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.framework.util.FileUtil;
import com.iisquare.jees.framework.util.ServletUtil;
import com.iisquare.jees.framework.util.ValidateUtil;
import com.iisquare.jees.oa.domain.Upload;
import com.iisquare.jees.oa.service.UploadService;

/**
 * 上传文件管理
 * @author Ouyang <iisquare@163.com>
 *
 */
@Controller
@Scope("prototype")
public class UploadController extends PermitController {
	
	@Autowired
	public UploadService uploadService;
	
	public String layoutAction() throws Exception {
		return displayTemplate();
	}
	
	public String listAction () throws Exception {
		int page = ValidateUtil.filterInteger(get("page"), true, 0, null);
		int pageSize = ValidateUtil.filterInteger(get("rows"), true, 0, 500);
		Map<Object, Object> map = uploadService.search(ServletUtil.singleParameterMap(_REQUEST_), "operate_time desc", page, pageSize);
		assign("total", map.get("total"));
		assign("rows", DPUtil.collectionToArray((Collection<?>) map.get("rows")));
		return displayJSON();
	}
	
	public String uploadJsonAction() throws Exception {
		/* 各项参数待配置 */
		String uploadFolder = "files/attached/"; // 文件上传目录
		long maxSize = 1000000; // 允许上传大小
		HashMap<String, String> extMap = new HashMap<String, String>(); // 允许上传的文件扩展名
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
		extMap.put("icon", "gif,jpg,jpeg,png,bmp");

		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(_REQUEST_.getSession().getServletContext());
		if(!multipartResolver.isMultipart(_REQUEST_)) return displayError(3000, "请选择上传文件");
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) _REQUEST_;
		
		File tempFile;
		/* 检查上传目录读写权限 */
		tempFile = new File(DPUtil.stringConcat(_WEB_ROOT_, "/", uploadFolder));
		if(!tempFile.isDirectory() || !tempFile.canWrite()) return displayError(3001, "上传目录不存在或无访问权限");
		
		/* 上传类别检查 */
		String type = ValidateUtil.filterItem(multiRequest.getParameter("type"), false, DPUtil.collectionToStringArray(extMap.keySet()), null);
		if(DPUtil.empty(type)) return displayError(3002, "暂不支持该类别的文件");
		
		/* 生成上传目录 */
		uploadFolder = DPUtil.stringConcat(uploadFolder, type, "/", DPUtil.getCurrentDateTime("yyyyMMdd"), "/");
		if(!FileUtil.mkdirs(DPUtil.stringConcat(_WEB_ROOT_, "/", uploadFolder))) return displayError(500, "生成上传目录失败");
		
		Iterator<String> iterator = multiRequest.getFileNames();
		StringBuilder sb = new StringBuilder();
		while (iterator.hasNext()) {
			MultipartFile file = multiRequest.getFile(iterator.next());
			if(file.getSize() > maxSize) return displayError(3003, "上传文件大小超过限制");
			String typeStr = extMap.get(type);
			String originalFilename = file.getOriginalFilename();
			String fileSuffix = DPUtil.subString(originalFilename, originalFilename.lastIndexOf(".") + 1).toLowerCase();
			if(!DPUtil.isItemExist(DPUtil.explode(typeStr, ",", " "), fileSuffix)) return displayError(3004, DPUtil.stringConcat("仅支持以下文件类型\n", typeStr));
			long time = System.currentTimeMillis();
			String uri = DPUtil.stringConcat(uploadFolder, time, ".", fileSuffix);
			Upload persist = new Upload();
			persist.setName(originalFilename); // 需要做安全验证
			persist.setUri(uri);
			persist.setOperateId(currentMember.getId());
			persist.setOperateIp(ServletUtil.getRemoteAddr(_REQUEST_));
			persist.setOperateTime(time);
			tempFile = new File(DPUtil.stringConcat(_WEB_ROOT_, "/", uri));
			try {
				file.transferTo(tempFile);
			} catch (Exception e) {
				return displayError(500, "文件上传失败");
			}
			int result = uploadService.insert(persist);
			if(result < 1) return displayError(500, "记录保存失败");
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("uri", uri);
			sb.append(obj.toString() + "\r\n");
		}
		return displayText(sb.toString());
	}
	
	private String displayError(int error, String message) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put("error", error);
		map.put("message", message);
		return displayJSON(map);
	}
}
