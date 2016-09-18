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
<title>Dentist - Create Patient</title>
</head>
<body>
	<h1>Create new patient</h1>
	<form action="RegisterPatientServlet" method="post">
		First name:<input type="text" name="patient_first_name" required><br>
		Last name:<input type="text" name="patient_last_name" required><br>
		Email:<input type="email" name="patient_email" required> <br>
		<input type="submit" value="Submit">
	</form>
</body>
</html>