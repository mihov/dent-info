package model.controler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			request.getSession().setAttribute("logged", email);
			request.getSession().setAttribute("firstName", ManagersManager.getInstance().getManager(email).getFirstName());
			request.getSession().setAttribute("lastName", ManagersManager.getInstance().getManager(email).getLastName());
			request.getSession().setAttribute("userId", ManagersManager.getInstance().getManager(email).getUser_id());
			if(ManagersManager.getInstance().getManager(email).getCurrentLab() != null){
				request.getSession().setAttribute("lab", ManagersManager.getInstance().getManager(email).getCurrentLab().getLabID());
			}else{
				request.getSession().setAttribute("lab", 0);
			}
			System.out.println(ManagersManager.getInstance().getManager(email).getUser_id());
			System.out.println(request.getSession());
			htmlFile = "manager_main.jsp";
		}
		else{
			htmlFile = "login.html";
		}
		RequestDispatcher rd = request.getRequestDispatcher(htmlFile);
		rd.forward(request, response);
	}

}
