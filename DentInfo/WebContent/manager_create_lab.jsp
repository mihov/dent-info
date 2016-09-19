<%@page import="model.inTheLab.UserManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.mainObjects.*, model.inTheLab.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String username = (String) request.getSession().getAttribute("logged");
	Person p = UserManager.getInstance().getUser(username);
	RequestDispatcher rd = null;
	if (username == null) {
		rd = request.getRequestDispatcher("login.html");
		rd.forward(request, response);
	}
	if (request.getSession().getAttribute("lab") != null) {
		rd = request.getRequestDispatcher("manager_main.jsp");
		rd.forward(request, response);
	}
	if(p.getCurrentLab() != null){
		rd = request.getRequestDispatcher("manager_main.jsp");
		rd.forward(request, response);
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Register a new laboratory</h1>
	<form action="RegisterLabServlet" method="post">
		Laboratory name:<input type="text" name="name" required><br>
		Bulstat:<input type="text" name="bulstat" required><br>
		Address:<input type="text" name="occupation"><br>
		<input 	type="submit" value="Submit">
	</form>
</body>
</html>