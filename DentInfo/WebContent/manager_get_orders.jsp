<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.mainObjects.*, model.inTheLab.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<%@include file="head.html"%>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Manager create service</title>
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
			Person p = UserManager.getInstance().getUser(username);
			p.setCurrentLab(LaboratoryManager.getInstatnce().getLab(p.getLab_id()));
			DentalLaboratory dl =  p.getCurrentLab();
		%>
	</head>
	<body>
	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>
					Mr.
					<%
					out.print(p.getFirstName() + " " + p.getLastName());
				%>
					patients list
				</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<p class="text-muted font-13 m-b-30">
					<a href="manager_main.jsp"><button type="submit"
							class="btn btn-primary">Manager main</button></a> <a
						href="manager_create_dentist.jsp"><button type="submit"
							class="btn btn-primary">Create dentist</button></a> <a
						href="manager_create_service.jsp"><button type="submit"
							class="btn btn-primary">Create service</button></a> <a
						href="manager_create_lab.jsp"><button type="submit"
							class="btn btn-primary">Create lab</button></a> <a
						href="manager_edit_lab.jsp"><button type="submit"
							class="btn btn-primary">Edit lab</button></a> <a
						href="manager_edit_service.jsp"><button type="submit"
							class="btn btn-primary">Edit service</button></a><a
							href="manager_get_orders.jsp"><button type="submit"
								class="btn btn-primary">Orders</button></a>
							<a href="Logout"><button type="submit"
							class="btn btn-primary">Logout</button></a>
				</p>
			</div>
				<table id="datatable-responsive"
					class="table table-striped table-bordered dt-responsive nowrap"
					cellspacing="0" width="100%">
						<tr>
							<th>Service serial number</th>
							<th>Long name</th>
							<th>Short name</th>
							<th>Price</th>
						</tr>
						<%for(Order o : dl.getAllOrders().values()){		%>	
							<tr>					
								<th rowspan="4">Order num:<%= o.getId() %>from:<%= o.getDentist().getEmail()%> Status:<%= o.getStatus() %></th>
							</tr>

							<%for(Integer i : o.getAllServices().keySet()){	%>		
								<%for(Service s : o.getAllServices().get(i)){%>	
							<tr>
								<th><%= s.getSerialNumber()					%></th>
								<td><%= s.getLongName()						%></td>
								<td><%= s.getShortName()					%></td>
								<td><%= o.getPrice()						%></td>
							</tr>
								<%} 										%>
							<%} 											%>
							<tr>
								<th rowspan="2">Price for all:<%= o.getPrice() 	%></th>
								<form action="SetOrderStatusServlet" method="POST">
									<% request.getSession().setAttribute("ored", o.getId()); %>
									<td><a href="manager_get_orders.jsp"><input type="submit" name="act" value="payed" class="btn btn-primary">Set As Delivered</input></a></td>
									<td><a href="manager_get_orders.jsp"><input type="submit" name="act" value="ready" class="btn btn-primary">Set As Ready</input></a></td>
								</form>
							</tr>
						<%} 												%>
				</table>
		</div>
	</div>
	</body>
</html>