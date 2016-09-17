<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="model.mainObjects.*"%>
<%
	String username = (String) request.getSession().getAttribute("logged");
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
		Hello D-r <%=((Dentist) session.getAttribute("user")).getEmail()%>
		</h1>
	<a href="dentist_create_patient.jsp">Create new patient</a>
	<br>
	<a href="dentist_create_order.jsp">Create new order</a>
	<br>
	<hr>
	<a href="dentist_edit_patient.jsp">Create Lab</a>
	<br>
	<a href="dentist_edit_order.jsp">Create Dentist</a>
	<br>
	<hr>
	<a href="dentist_edit_patient.jsp">Create Lab</a>
	<br>
	<a href="dentist_edit_order.jsp">Create Dentist</a>
	<br>
</body>
</html>