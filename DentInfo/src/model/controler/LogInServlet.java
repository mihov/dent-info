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
import model.inTheLab.ManagersManager;
import model.inTheLab.UserManager;

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
			//request.getSession().setAttribute("user", UserManager.getInstance().getUser(email));
			System.out.println(UserManager.getInstance().getUser(email));
			//request.getSession().setAttribute("logged", email);
			// request.getSession().setAttribute("user", email);
			// request.getSession().setAttribute("firstName",
			// ManagersManager.getInstance().getManager(email).getFirstName());
			// request.getSession().setAttribute("lastName",
			// ManagersManager.getInstance().getManager(email).getLastName());

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

		RequestDispatcher rd = request.getRequestDispatcher(htmlFile);
		rd.forward(request, response);
	}

}
