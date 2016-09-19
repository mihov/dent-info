package model.controler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.db.ManagerDAO;
import model.db.UserDAO;
import model.inTheLab.LaboratoryManager;
import model.inTheLab.Manager;
import model.inTheLab.ManagersManager;
import model.inTheLab.Service;
import model.inTheLab.ServiceManager;
import model.inTheLab.UserManager;
import model.mainObjects.Person;

@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String htmlFile;
		System.out.println(email);
		System.out.println(password);
		System.out.println("Someone is trying to log in");
		if (UserManager.getInstance().validLogIn(email, password)) {
			request.getSession().setAttribute("user", UserManager.getInstance().getUser(email));
			request.getSession().setAttribute("logged", email);
			System.out.println(UserManager.getInstance().getUser(email));
			System.out.println(request.getSession());
			
			switch (UserManager.getInstance().getUser(email).getFk_user_type_id()) {
			case 1:
				htmlFile = "manager_main.jsp";
				break;

			case 2:
				htmlFile = "dentist_main.jsp";
				break;

			default:
				htmlFile = "login.html";
				break;
			}

		} else {
			htmlFile = "login.html";
		}
		Person p = UserManager.getInstance().getUser(email);
		System.out.println("adding the lab " + LaboratoryManager.getInstatnce().getLab(p.getLab_id()) + " to " + p.getEmail());
		p.setCurrentLab(LaboratoryManager.getInstatnce().getLab(p.getLab_id()));
		LaboratoryManager.getInstatnce();
		ServiceManager.getInstance();
		System.out.println("services should be added here !!!!!!!!!!!!");
		System.out.println("And it is: " + p.getCurrentLab());
		System.out.println(UserManager.getInstance().getUser(email).getLab_id());
		RequestDispatcher rd = request.getRequestDispatcher(htmlFile);
		rd.forward(request, response);
	}

}
