package model.controler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.db.ManagerDAO;
import model.inTheLab.ManagersManager;

@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String htmlFile;
		System.out.println(email);
		System.out.println(password);
		System.out.println("Someone is trying to log in");
		if(ManagersManager.getInstance().validLogIn(email, password)){
			htmlFile = "manager_lib_create.html";
		}
		else{
			htmlFile = "login.html";
		}
		RequestDispatcher rd = request.getRequestDispatcher(htmlFile);
		rd.forward(request, response);
	}

}
