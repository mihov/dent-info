<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="model.mainObjects.*"%>
<%
	

String username = (String) request.getSession().getAttribute("logged");
	if (username == null) {
		RequestDispatcher rd = request.getRequestDispatcher("login.html");
		rd.forward(request, response);
	}

	Dentist dentist = ((Dentist) session.getAttribute("user"));
	String firstName = dentist.getFirstName();
	String lastName = dentist.getLastName();
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<head>
<%@include file="head.html"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dentist Patients</title>
</head>
<body>
	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>
					D-r
					<%
					out.print(firstName + " " + lastName);
				%>
					patients list
				</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<p class="text-muted font-13 m-b-30">
					<a href="dentist_main.jsp"><button type="submit"
							class="btn btn-primary">Dentist main</button></a> <a
						href="dentist_create_patient.jsp"><button type="submit"
							class="btn btn-primary">New patient</button></a> <a
						href="dentist_create_order.jsp"><button type="submit"
							class="btn btn-primary">New order</button></a> <a
						href="dentist_list_patients.jsp"><button type="submit"
							class="btn btn-primary">Patients list</button></a> <a
						href="dentist_view_orders.jsp"><button type="submit"
							class="btn btn-primary">Orders List</button></a> <a href="Logout"><button
							type="submit" class="btn btn-primary">Logout</button></a>
				</p>
			</div>
			<div class="x_content"></div>
		</div>
	</div>
	<%@include file="bottom.html"%>
</body>
</html>