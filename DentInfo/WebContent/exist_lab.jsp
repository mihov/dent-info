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
							class="btn btn-primary">Edit service</button></a>
							<a href="Logout"><button type="submit"
							class="btn btn-primary">Logout</button></a>
				</p>
			</div>
			<h2><font color="red">You already have a lab</font></h2>
		</div>
	</div>
	<%@include file="bottom.html"%>
	</body>
</html>