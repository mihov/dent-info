<%@page import="model.inTheLab.UserManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="model.mainObjects.*, model.inTheLab.*"%>
<html>
		<%
			String username = (String) request.getSession().getAttribute("logged");
			Person p = UserManager.getInstance().getUser(username);
			RequestDispatcher rd = null;
			if (username == null) {
				rd = request.getRequestDispatcher("login.html");
				rd.forward(request, response);
			}
			if (request.getSession().getAttribute("lab") != null) {
				rd = request.getRequestDispatcher("exist_lab.jsp");
				rd.forward(request, response);
			}
			if(p.getCurrentLab() != null){
				rd = request.getRequestDispatcher("exist_lab.jsp");
				rd.forward(request, response);
			}
		%>
	<head>
	<%@include file="head.html"%>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Create laboratory</title>
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
							class="btn btn-primary">Edit lab</button></a><a
						href="manager_edit_service.jsp"><button type="submit"
							class="btn btn-primary">Edit service</button></a><a
							href="manager_get_orders.jsp"><button type="submit"
								class="btn btn-primary">Orders</button></a>
							<a href="Logout"><button type="submit"
							class="btn btn-primary">Logout</button></a>
				</p>
			</div>
				<h1>Make changes to <%= p.getCurrentLab().getName() %></h1>
				<form action="RegisterLabServlet" method="post">
					<p>Laboratory name:	</p><input type="text" name="name" required		><br>
					<p>Bulstat:			</p><input type="text" name="bulstat" required	><br>
					<p>Address:			</p><input type="text" name="occupation"		><br>
					<input 	type="submit" value="Submit">
				</form>
		</div>
	</div>
	<%@include file="bottom.html"%>
	</body>
</html>