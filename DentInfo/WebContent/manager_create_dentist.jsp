<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String username = (String) request.getSession().getAttribute("logged");
	RequestDispatcher rd = null;
	if (username == null) {
		rd = request.getRequestDispatcher("login.html");
		rd.forward(request, response);
	}
	if (request.getSession().getAttribute("lab") != null) {
		rd = request.getRequestDispatcher("manager_main.jsp");
		rd.forward(request, response);
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manager - Create Dentist</title>
</head>
<body>
	<h1>Create new dentist</h1>
	<form action="RegisterDentistServlet" method="post">
		First name:<br> <input type="text" name="dentist_first_name" required>
		Last name:<br> <input type="text" name="dentist_last_name" required>
		Email:<br> <input type="email" name="dentist_email" required> 
		<input 	type="submit" value="Submit">
	</form>
</body>
</html>