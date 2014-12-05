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
Debug Message:<br/><hr/>
<%
out.println("Protocol: " + request.getProtocol() + "<br>");
out.println("Scheme: " + request.getScheme() + "<br>");
out.println("Server Name: " + request.getServerName() + "<br>" );
out.println("Server Port: " + request.getServerPort() + "<br>");
out.println("Protocol: " + request.getProtocol() + "<br>");
out.println("Server Info: " + getServletConfig().getServletContext().getServerInfo() + "<br>");
out.println("Remote Addr: " + request.getRemoteAddr() + "<br>");
out.println("Remote Host: " + request.getRemoteHost() + "<br>");
out.println("Character Encoding: " + request.getCharacterEncoding() + "<br>");
out.println("Content Length: " + request.getContentLength() + "<br>");
out.println("Content Type: "+ request.getContentType() + "<br>");
out.println("Auth Type: " + request.getAuthType() + "<br>");
out.println("HTTP Method: " + request.getMethod() + "<br>");
out.println("Path Info: " + request.getPathInfo() + "<br>");
out.println("Path Trans: " + request.getPathTranslated() + "<br>");
out.println("Query String: " + request.getQueryString() + "<br>");
out.println("Remote User: " + request.getRemoteUser() + "<br>");
out.println("Session Id: " + request.getRequestedSessionId() + "<br>");
out.println("Request URL: " + request.getRequestURL() + "<br>");
out.println("Request URI: " + request.getRequestURI() + "<br>");
out.println("Servlet Path: " + request.getServletPath() + "<br>");
out.println("Created : " + session.getCreationTime() + "<br>");
out.println("LastAccessed : " + session.getLastAccessedTime() + "<br>");
out.println("Accept: " + request.getHeader("Accept") + "<br>");
out.println("Host: " + request.getHeader("Host") + "<br>");
out.println("Referer : " + request.getHeader("Referer") + "<br>");
out.println("Accept-Language : " + request.getHeader("Accept-Language") + "<br>");
out.println("Accept-Encoding : " + request.getHeader("Accept-Encoding") + "<br>");
out.println("User-Agent : " + request.getHeader("User-Agent") + "<br>");
out.println("Connection : " + request.getHeader("Connection") + "<br>");
out.println("Cookie : " + request.getHeader("Cookie") + "<br>");
%>
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