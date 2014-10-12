package com.iisquare.jees.oa.controller.index;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
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
	
	public String deleteAction() throws Exception {
		Integer id = ValidateUtil.filterInteger(get("id"), true, 0, null);
		Upload info = uploadService.getById(id);
		if(null == info) return displayMessage(3001, "记录不存在");
		String filePath = DPUtil.stringConcat(_WEB_ROOT_, "/", info.getUri());
		File file = new File(filePath);
		if(file.exists()) {
			if(!file.delete()) return displayMessage(3002, "文件删除失败");
		}
		int result = uploadService.delete(id);
		if(result > 0) {
			return displayMessage(0, "操作成功");
		} else {
			return displayMessage(500, "操作失败");
		}
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
		String dirName = ValidateUtil.filterItem(multiRequest.getParameter("dir"), false, DPUtil.collectionToStringArray(extMap.keySet()), null);
		if(DPUtil.empty(dirName)) return displayError(3002, "暂不支持该类别的文件");
		
		/* 生成上传目录 */
		uploadFolder = DPUtil.stringConcat(uploadFolder, dirName, "/", DPUtil.getCurrentDateTime("yyyyMMdd"), "/");
		if(!FileUtil.mkdirs(DPUtil.stringConcat(_WEB_ROOT_, "/", uploadFolder))) return displayError(500, "生成上传目录失败");
		
		Iterator<String> iterator = multiRequest.getFileNames();
		StringBuilder sb = new StringBuilder();
		while (iterator.hasNext()) {
			MultipartFile file = multiRequest.getFile(iterator.next());
			if(file.getSize() > maxSize) return displayError(3003, "上传文件大小超过限制");
			String typeStr = extMap.get(dirName);
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
			obj.put("url", DPUtil.stringConcat(_WEB_URL_, "/", uri));
			sb.append(obj.toString() + "\r\n");
		}
		return displayText(sb.toString());
	}
	
	public String editorAction() throws Exception {
		return displayTemplate();
	}
	
	public String fileManagerJsonAction() throws Exception {
		String uploadFolder = "files/attached/"; // 文件上传目录
		//根目录路径，可以指定绝对路径
		String rootPath = DPUtil.stringConcat(_WEB_ROOT_, "/", uploadFolder);
		//根目录URL，可以指定绝对路径
		String rootUrl  = DPUtil.stringConcat(_WEB_URL_, "/", uploadFolder);
		//图片扩展名
		String[] fileTypes = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};
		
		String dirName = DPUtil.parseString(get("dir"));
		if (dirName != null) {
			if(!Arrays.<String>asList(new String[]{"image", "flash", "media", "file"}).contains(dirName)){
				return displayText("无效的目录名称");
			}
			rootPath = DPUtil.stringConcat(rootPath, dirName, "/");
			rootUrl = DPUtil.stringConcat(rootUrl, dirName, "/");
			File saveDirFile = new File(rootPath);
			if (!saveDirFile.exists()) saveDirFile.mkdirs();
		}
		//根据path参数，设置各路径和URL
		String path = DPUtil.parseString(get("path"));
		String currentPath = DPUtil.stringConcat(rootPath, path);
		String currentUrl = DPUtil.stringConcat(rootUrl, path);
		String currentDirPath = path;
		String moveupDirPath = "";
		if (!"".equals(path)) {
			String str = DPUtil.subString(currentDirPath, 0, currentDirPath.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0 ? DPUtil.subString(str, 0, str.lastIndexOf("/") + 1) : "";
		}
		
		//排序形式，name or size or type
		String order = DPUtil.parseString(get("order"));
		if(DPUtil.empty(order)) {
			order = order.toLowerCase();
		} else {
			order = "name";
		}
		
		//不允许使用..移动到上一级目录
		if (path.indexOf("..") >= 0) {
			return displayText("访问被拒绝");
		}
		//最后一个字符不是/
		if (!"".equals(path) && !path.endsWith("/")) {
			return displayText("无效的参数");
		}
		//目录不存在或不是目录
		File currentPathFile = new File(currentPath);
		if(!currentPathFile.isDirectory()){
			return displayText("目录不存在");
		}
		
		//遍历目录取的文件信息
		List<Hashtable<?, ?>> fileList = new ArrayList<Hashtable<?, ?>>();
		if(currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles()) {
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				String fileName = file.getName();
				if(file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
				} else if(file.isFile()){
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
					hash.put("filetype", fileExt);
				}
				hash.put("filename", fileName);
				hash.put("datetime", DPUtil.millisToDateTime(file.lastModified(), configuration.getDateTimeFormat()));
				fileList.add(hash);
			}
		}
		
		if ("size".equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		} else if ("type".equals(order)) {
			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new NameComparator());
		}
		JSONObject result = new JSONObject();
		result.put("moveup_dir_path", moveupDirPath);
		result.put("current_dir_path", currentDirPath);
		result.put("current_url", currentUrl);
		result.put("total_count", fileList.size());
		result.put("file_list", fileList);
		return displayJSON(result);
	}
	
	private String displayError(int error, String message) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put("error", error);
		map.put("message", message);
		return displayJSON(map);
	}
}

class NameComparator implements Comparator<Object> {
	public int compare(Object a, Object b) {
		Hashtable<?, ?> hashA = (Hashtable<?, ?>)a;
		Hashtable<?, ?> hashB = (Hashtable<?, ?>)b;
		if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
			return -1;
		} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
			return 1;
		} else {
			return ((String)hashA.get("filename")).compareTo((String)hashB.get("filename"));
		}
	}
}

class SizeComparator implements Comparator<Object> {
	public int compare(Object a, Object b) {
		Hashtable<?, ?> hashA = (Hashtable<?, ?>)a;
		Hashtable<?, ?> hashB = (Hashtable<?, ?>)b;
		if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
			return -1;
		} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
			return 1;
		} else {
			if (((Long)hashA.get("filesize")) > ((Long)hashB.get("filesize"))) {
				return 1;
			} else if (((Long)hashA.get("filesize")) < ((Long)hashB.get("filesize"))) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}

class TypeComparator implements Comparator<Object> {
	public int compare(Object a, Object b) {
		Hashtable<?, ?> hashA = (Hashtable<?, ?>)a;
		Hashtable<?, ?> hashB = (Hashtable<?, ?>)b;
		if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
			return -1;
		} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
			return 1;
		} else {
			return ((String)hashA.get("filetype")).compareTo((String)hashB.get("filetype"));
		}
	}
}