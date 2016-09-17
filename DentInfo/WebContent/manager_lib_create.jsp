<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% String username = (String)request.getSession().getAttribute("logged"); 
if(username == null) {
	RequestDispatcher rd = request.getRequestDispatcher("login.html");
	rd.forward(request, response);}
%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1><%=username %></h1>
<h1><%=(String)request.getSession().getAttribute("FirstName") %></h1>
<h1><%=(String)request.getSession().getAttribute("LastName") %></h1>
</body>
</html>