<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Log Out Redirect</title>
</head>
<body>

<%
	String redirectURL = request.getContextPath() + "/login/logout-success.jsp";
	response.sendRedirect(redirectURL);
%>


</body>
</html>