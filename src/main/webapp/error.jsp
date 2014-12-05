<%@ page language="java" import="java.util.*, java.io.*" pageEncoding="UTF-8" isErrorPage="true"%>
<%!
public String getCookie(HttpServletRequest request, String key) throws UnsupportedEncodingException {
	Cookie cookies[] = request.getCookies();
	if(null == cookies) return null;
	for(Cookie cookie : cookies) {
		if(key.equals(cookie.getName())) return cookie.getValue();
	}
	return null;
}
%>
<%
String debugCode = getCookie(request, "debug");
if("iisquare.com".equals(debugCode)) {
%>
Debug Message:<br/>
<hr/>
<font color=red>
	getMessage():<br/>
	<%=exception.getMessage()%><br/>
	<hr/>
	getLocalizedMessage():<br>
	<%=exception.getLocalizedMessage()%><br/>
	<hr>
	PrintStatckTrace():<br/>
	<%
	StringWriter sw = new StringWriter();
	PrintWriter pw = new PrintWriter(sw);
	exception.printStackTrace(pw);
	out.println(sw);
	%><br/>
</font>
<%
} else {
	StringBuilder sb = new StringBuilder();
	sb.append(request.getScheme())
			.append("://")
			.append(request.getServerName());
	if(80 != request.getServerPort()) {
		sb.append(":").append(request.getServerPort());
	}
	sb.append("/404.html");
	response.sendRedirect(sb.toString());
}
%>