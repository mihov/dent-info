<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.mainObjects.*, model.inTheLab.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<%
			String username = (String) request.getSession().getAttribute("logged");
			Person p = UserManager.getInstance().getUser(username);
			p.setCurrentLab(LaboratoryManager.getInstatnce().getLab(p.getLab_id()));
			DentalLaboratory dl =  p.getCurrentLab();
		%>
	</head>
	<body>
		<form action="AddServiceServlet" method="POST">
			<p>Service short name	</p>	<input type="text" name="service_short_name" 	required>
			<p>Service long name	</p>	<input type="text" name="service_long_name" 	required>
			<p>Service price		</p>	<input type="text" name="service_price" 		required>
			<input 	type="submit" value="Submit" href="manager_create_service.jsp"					>
		</form>
		<table border="solid">
			<tr>
				<th></th>
				<th>Long name</th>
				<th>Short name</th>
				<th>Price</th>
			</tr>
				<%for(Service s : dl.getAllServices().values()){	%>	
					<tr>
						<th><%= s.getSerialNumber()						%></th>
						<td><%= s.getLongName()							%></td>
						<td><%= s.getShortName()						%></td>
						<td><%= s.getPrice()							%></td>
					</tr>
				<%} 												%>
		</table>
	</body>
</html>