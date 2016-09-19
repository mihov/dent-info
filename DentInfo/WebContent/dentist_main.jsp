<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="model.mainObjects.*"%>
<%
	String username = (String) request.getSession().getAttribute("logged");
	String firstName = ((Dentist) session.getAttribute("user")).getFirstName();
	String lastName = ((Dentist) session.getAttribute("user")).getLastName();
	if (username == null) {
		RequestDispatcher rd = request.getRequestDispatcher("login.html");
		rd.forward(request, response);
	}
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dentist - main</title>
</head>
<body>
	<h1>
		Hello D-r
		<%=(firstName + " " + lastName)%>

	</h1>
	<a href="dentist_create_patient.jsp">Create new patient</a>
	<br>
	<a href="dentist_create_order.jsp">Create new order</a>
	<br>
	<hr>
	<a href="dentist_list_patients.jsp">Patients list</a>
	<br>
	<hr>
	<a href="dentist_view_orders.jsp">Orders list</a>
	<br>
</body>
</html>