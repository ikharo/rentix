<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>
<%@ page import="org.springframework.security.authentication.AnonymousAuthenticationToken" %>

<%
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	if (auth != null && (!AnonymousAuthenticationToken.class.isAssignableFrom(auth.getClass()))) {%>
		<h2>Welcome <sec:authentication property="principal.username"/></h2>
	<%}
	else	{%>
		<h2>Not authenticated!!</h2>
	<%}
%>