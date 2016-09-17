<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% String username = (String)request.getSession().getAttribute("logged"); 
if(username == null) {
	RequestDispatcher rd = request.getRequestDispatcher("login.html");
	rd.forward(request, response);}
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manager - main</title>
</head>
<body>
	<a href="manager_create_lab.jsp">Create Lab</a>
	<a href="manager_create_dentist.jsp">Create Dentist</a>
	<a href="manager_create_service.jsp">Create Service</a>
	<hr>
	<a href="manager_edit_lab.jsp">Edit Lab</a>
	<a href="manager_edit_dentist.jsp">Edit Dentist</a>
	<a href="manager_edit_service.jsp">Edit Service</a>
</body>
</html>