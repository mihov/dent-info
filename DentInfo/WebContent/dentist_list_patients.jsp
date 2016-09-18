<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="model.mainObjects.*, java.util.ArrayList"%>
<%
	Dentist dentist = (Dentist) request.getSession().getAttribute("user");
	ArrayList<Patient> patientList = PatientManager.getInstance().getPatientsByDentist(dentist);
	String username = (String) request.getSession().getAttribute("logged");
	RequestDispatcher rd = null;
	if (username == null) {
		rd = request.getRequestDispatcher("login.html");
		rd.forward(request, response);
	}
%>

<head>
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dentist Patients</title>
</head>
<body>
<h1>D-r <%out.print(dentist.getFirstName()+" "+ dentist.getLastName()); %> patients list</h1>
<hr>
	<table border="1">
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
</body>
</html>