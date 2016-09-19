<%@page import="model.inTheLab.UserManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.mainObjects.*, model.inTheLab.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%
			String username = (String) request.getSession().getAttribute("logged");
			Person p = UserManager.getInstance().getUser(username);
			RequestDispatcher rd = null;
			if (username == null) {
				rd = request.getRequestDispatcher("login.html");
				rd.forward(request, response);
			}
			if (request.getSession().getAttribute("lab") != null) {
				rd = request.getRequestDispatcher("manager_main.jsp");
				rd.forward(request, response);
			}
			if(p.getCurrentLab() != null){
				rd = request.getRequestDispatcher("manager_main.jsp");
				rd.forward(request, response);
			}
		%>
		<%@include file="head.html"%>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
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
							href="manager_edit_dentist.jsp"><button type="submit"
								class="btn btn-primary">Edit Dentist</button></a> <a
							href="manager_edit_service.jsp"><button type="submit"
								class="btn btn-primary">Edit service</button></a>
							<a href="Logout"><button type="submit"
								class="btn btn-primary">Logout</button></a>
					</p>
				</div>
		<h2>Register a new laboratory</h2>
		<form action="RegisterLabServlet" method="post">
			<p>Laboratory name:	</p><input type="text" name="name" required		><br>
			<p>Bulstat:			</p><input type="text" name="bulstat" required	><br>
			<p>Address:			</p><input type="text" name="occupation"		><br>
			<input 	type="submit" value="Submit">
		</form>
	<%@include file="bottom.html"%>
	</body>
</html>