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
<title>Dentist Patients</title>
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
			<div class="x_content">
				<table id="datatable-responsive"
					class="table table-striped table-bordered dt-responsive nowrap"
					cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>First name</th>
							<th>Last name</th>
							<th>Email</th>
							<th>EGN</th>
							<th>Phone</th>
							<th>Address</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (Patient patient : patientList) {
						%>
						<tr>
							<th>
								<%
									out.print(patient.getFirstName());
								%>
							</th>
							<th>
								<%
									out.print(patient.getLastName());
								%>
							</th>
							<th>
								<%
									out.print(patient.getEmail());
								%>
							</th>
							<th>
								<%
									out.print(patient.getEgn());
								%>
							</th>
							<th>
								<%
									out.print(patient.getPhone());
								%>
							</th>
							<th>
								<%
									out.print(patient.getAddress());
								%>
							</th>
							<th><form action="dentist_edit_patient.jsp">
									<input type="hidden" name="edit"
										value="		<%out.print(patient.getUser_id());%>"> <input
										type="submit" value="Edit">
								</form></th>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<%@include file="bottom.html"%>
</body>
</html>