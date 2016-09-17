<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String username = (String) request.getSession().getAttribute("logged");
	Integer labId = (request.getSession().getAttribute("lab") == null) ? 0
			: (Integer) request.getSession().getAttribute("lab");
	RequestDispatcher rd = null;
	if (username == null) {
		rd = request.getRequestDispatcher("login.html");
	}
	if (labId > 0) {
		rd = request.getRequestDispatcher("manager_main.jsp");
	}
	rd.forward(request, response);
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String email2 = request.getParameter("confirm_email");
		String bulstat = request.getParameter("bulstat");
		String website = request.getParameter("website");
		String address = request.getParameter("occupation");

	<form action="RegisterLabServlet" method="post">
		Laboratory name:<br> <input type="text" name="name" required>
		Email:<br> <input type="email" name="email" required> 
		Bulstat:<br> <input type="text" name="bulstat" required>
		Website:<br> <input type="text" name="website">
		Address:<br> <input type="text" name="occupation">
		<input 	type="submit" value="Submit">
	</form>
</body>
</html>