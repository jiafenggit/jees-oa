<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
StringBuilder sb = new StringBuilder();
sb.append(request.getScheme())
		.append("://")
		.append(request.getServerName());
if(80 != request.getServerPort()) {
	sb.append(":").append(request.getServerPort());
}
sb.append("/404.html");
response.sendRedirect(sb.toString());
%>
