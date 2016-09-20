<%@ page errorPage="login.html" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% response.setHeader("Pragma", "No-cache"); response.setHeader("Pragma", "No-cache");
response.setDateHeader("Expires", 0); response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache"); %>
<html>
<%@ page import="model.mainObjects.*, java.util.ArrayList"%>
<%
	String username = (String) request.getSession().getAttribute("logged");
	RequestDispatcher rd = null;
	if (username == null) {
		rd = request.getRequestDispatcher("login.html");
		rd.forward(request, response);
	}
	Dentist dentist = (Dentist) request.getSession().getAttribute("user");
	ArrayList<Patient> patientList = PatientManager.getInstance().getPatientsByDentist(dentist);
%>
<head>
<%@include file="head.html"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dentist - Create Patient</title>
</head>
<body>
	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>
					D-r
					<%
					out.print(dentist.getFirstName() + " " + dentist.getLastName());
				%>
					add new patient
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
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<br>
				<form action="RegisterPatientServlet" method="post"
					class="form-horizontal form-label-left">

					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12">First
							name:</label>
						<div class="col-md-9 col-sm-9 col-xs-12">
							<input type="text" class="form-control"
								placeholder="First name here" name="patient_first_name" required>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12">Last
							name:</label>
						<div class="col-md-9 col-sm-9 col-xs-12">
							<input type="text" class="form-control"
								placeholder="Last name here" name="patient_last_name" required>
						</div>
					</div>


					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12">First
							name:</label>
						<div class="col-md-9 col-sm-9 col-xs-12">
							<input type="email" class="form-control"
								placeholder="email@put.here" name="patient_email" required>
						</div>
					</div>

					<div class="ln_solid"></div>
					<div class="form-group">
						<div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
							<button type="reset" class="btn btn-primary">Cancel</button>
							<button type="submit" class="btn btn-success">Add</button>
						</div>
					</div>
				</form>
				<div class="clearfix"></div>
			</div>

		</div>
	</div>
	<%@include file="bottom.html"%>
</body>
</html>