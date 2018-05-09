<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Access Denied</h1>

<h2>${SPRING_SECURITY_403_EXCEPTION.getMessage()}</h2>

<ul></ul>
<%
	Enumeration<String> en = request.getAttributeNames();

	while(en.hasMoreElements()){
		
		String name = en.nextElement();
		
		Object value = request.getAttribute(name);
		
	%>
		<li><div><%=name %></div><div><%=value %></div></li>	
<% 		
	}

%>



</body>
</html>