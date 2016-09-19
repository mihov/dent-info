<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.mainObjects.*, model.inTheLab.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Edit laboratory</title>
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
		<h1>Make changes to <%= p.getCurrentLab().getName() %></h1>
		<form action="EditLabServlet" method="POST">
			Name:<br> <input type="text" name="lab_name" required><br/>
			Bulstat:<br> <input type="text" name="lab_bulstat" required><br/>
			Adress:<br> <input type="text" name="lab_address" required> <br/>
			<input type="submit" value="Submit">
		</form>
	</body>
</html>