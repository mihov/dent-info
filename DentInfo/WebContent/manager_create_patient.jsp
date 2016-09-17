<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%
	String username = (String) request.getSession().getAttribute("logged");
	Integer labId = (request.getSession().getAttribute("lab") == null) ? 0
			: (Integer) request.getSession().getAttribute("lab");
	RequestDispatcher rd = null;
	if (username == null) {
		rd = request.getRequestDispatcher("login.html");
	}
	rd.forward(request, response);
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manager - Create Patient</title>
</head>
<body>
	<h1>Create new patient</h1>
	<form action="RegisterDentistServlet" method="post">
		First name:<br> <input type="text" name="dentist_first_name" required>
		Last name:<br> <input type="text" name="dentist_last_name" required>
		Email:<br> <input type="email" name="dentist_email" required> 
		<input type="submit" value="Submit">
	</form>
</body>
</html>