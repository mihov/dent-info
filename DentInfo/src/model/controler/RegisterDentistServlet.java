package model.controler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.exceptions.InvalidEmailException;
import model.exceptions.InvalidUserNameException;
import model.mainObjects.DentistManager;

@WebServlet("/RegisterDentistServlet")
public class RegisterDentistServlet extends HttpServlet {


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("dentist_email");
		String password = request.getParameter("dentist_email");
		String name = request.getParameter("dentist_first_name");
		String family = request.getParameter("dentist_last_name");
		System.out.println(password);
		System.out.println(email);
		try {
			DentistManager.getInstance().registerDentist(password, email);
		} catch (InvalidEmailException e) {
			System.out.println("Someone added an ivalid email");
			//TODO add message page when invalid email
		} catch (InvalidUserNameException e) {
			System.out.println("Someone added an ivalid username");
			//TODO add message page when invalid username
		}
		System.out.println("Dentist registered successfully");
		RequestDispatcher rd = request.getRequestDispatcher("login.html");
		rd.forward(request, response);
	}

}
