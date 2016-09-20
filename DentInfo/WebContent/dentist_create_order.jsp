
<%@page import="java.util.Arrays"%>
<%@page import="java.util.prefs.BackingStoreException"%>
<%@page import="model.inTheLab.ServiceManager"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="model.inTheLab.Service"%>
<%@page import="model.inTheLab.Basket"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	response.setHeader("Pragma", "No-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-cache");
%>
<html>

<%@ page import="model.mainObjects.*, java.util.ArrayList"%>
<%
	String username = (String) request.getSession().getAttribute("logged");
	if (username == null) {
		RequestDispatcher rd = request.getRequestDispatcher("login.html");
		rd.forward(request, response);
	}
	Dentist dentist = ((Dentist) session.getAttribute("user"));
	ArrayList<Patient> patientList = PatientManager.getInstance().getPatientsByDentist(dentist);
	String firstName = dentist.getFirstName();
	String lastName = dentist.getLastName();
	if (session.getAttribute("basket") == null) {
		session.setAttribute("basket", new Basket());
	}
	Basket basket = (Basket) session.getAttribute("basket");

	if (request.getParameter("action") != null && request.getParameter("action").equalsIgnoreCase("DELETE")
			&& request.getParameter("position") != "" && request.getParameter("index") != "") {

		System.out.print("DELETE" + request.getParameter("position") + "-> " + request.getParameter("index"));
		if (!basket.isEmptyAt(Integer.parseInt(request.getParameter("position")))) {
			basket.removeService(Integer.parseInt(request.getParameter("position")),
					Integer.parseInt(request.getParameter("index")));
		}
	}

	if (request.getParameter("action") != null && request.getParameter("action").equalsIgnoreCase("ADD")
			&& request.getParameter("position") != "" && request.getParameter("service_id") != "") {

		basket.putServiceByID(Integer.parseUnsignedInt(request.getParameter("position")),
				Integer.parseUnsignedInt(request.getParameter("service_id")));

	}

	Map<Integer, Service> serviceList = ServiceManager.getInstance().getAllServices(dentist.getLab_id());
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<%@include file="head.html"%>
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
					create order
				</h2>
				<a
					href="dentist_create_order.jsp?action=DELETE&position=48&dervice_id=0">delete</a>
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
		</div>
		<div class="row">
			<div class="col-md-6 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>All Services</h2>
						<div class="clearfix"></div>
					</div>

					<div class="x_content">
						<form class="form-horizontal form-label-left"
							action="dentist_create_order.jsp" method="get">
							<input type="hidden" name="action" value="ADD">
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Position</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<%@include file="position_selector.html"%>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Services</label>
								<div class="col-md-9 col-sm-9 col-xs-12">

									<select class="form-control" name="service_id">
										<option></option>
										<%
											for (Entry<Integer, Service> element : serviceList.entrySet()) {
										%>
										<option value="<%=element.getValue().getSerialNumber()%>">
											<%=element.getValue().getLongName()%>
											&nbsp;&nbsp;&nbsp;&nbsp;$
											<%=element.getValue().getPrice()%>


										</option>
										<%
											}
										%>
									</select>
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
					</div>
				</div>
			</div>
			<div class="col-md-6 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>Order</h2>
						<div class="clearfix"></div>
					</div>

					<div class="x_content">
						<br>
						<form class="form-horizontal form-label-left"
							action="dentist_create_order.jsp" method="get">
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Patient</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<select class="form-control">
										<option></option>
										<%
											for (Patient patient : patientList) {
												String fullname = patient.getFirstName() + " " + patient.getLastName();
										%>
										<option value="<%out.print(patient.getUser_id());%>">
											<%
												out.print(fullname);
											%>
										</option>
										<%
											}
										%>
									</select>
								</div>
							</div>



							<div class="x_content">
								<table id="datatable-responsive"
									class="table table-striped table-bordered dt-responsive nowrap"
									cellspacing="0" width="100%">
									<thead>
										<tr>
											<th>Position</th>
											<th>Name</th>
											<th>Price</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
										<%
											for (Entry<Integer, ArrayList<Service>> element : basket.getAll().entrySet()) {
												int index = 0;
												for (Service service_element : element.getValue()) {
										%>
										<tr>
											<td><%=element.getKey()%></td>
											<td><%=service_element.getLongName()%></td>
											<td><%=service_element.getPrice()%></td>
											<td><a
												href="dentist_create_order.jsp?action=DELETE&position=<%=element.getKey()%>>&index=<%=index++%>>">
													Delete <%=index - 1%> ##<%=element.getKey()%></a></td>
											<td><a
												href="dentist_create_order.jsp?action=DELETE&position=48&index=0">
													Delete</a></td>
										</tr>
										<%
											}
										%>

										<%
											}
										%>
										<tr>
											<th></th>
											<th>Total:</th>
											<th><%=basket.getTotal()%>></th>
											<th></th>
										</tr>

									</tbody>
								</table>
							</div>






							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Default
									Input</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<input type="text" class="form-control"
										placeholder="Default Input">
								</div>
							</div>

							<div class="ln_solid"></div>
							<div class="form-group">
								<div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">

									<button type="reset" class="btn btn-primary">Cancel</button>
									<button type="submit" class="btn btn-success">Send</button>
								</div>
							</div>

						</form>
					</div>
				</div>
			</div>

		</div>
	</div>

	<%@include file="bottom_libs.html"%>
</body>
</html>