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
import model.mainObjects.Dentist;
import model.mainObjects.PatientManager;

@WebServlet("/RegisterPatientServlet")
public class RegisterPatientServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("patient_email");
		String password = request.getParameter("patient_email");
		String firstName = request.getParameter("patient_first_name");
		String lastName = request.getParameter("patient_last_name");
		Integer lab_id = ((Dentist) request.getSession().getAttribute("user")).getLab_id();
		Integer dentist_id = ((Dentist) request.getSession().getAttribute("user")).getUser_id();

		try {
			PatientManager.getInstance().registerPatient(password, email, firstName, lastName, dentist_id, lab_id);
		} catch (InvalidEmailException e) {
			System.out.println("Someone added an ivalid email");
			// TODO add message page when invalid email
		} catch (InvalidUserNameException e) {
			System.out.println("Someone added an ivalid username");
			// TODO add message page when invalid username
		}
		System.out.println("Patient registered successfully");

		RequestDispatcher rd = request.getRequestDispatcher("dentist_list_patients.jsp");
		rd.forward(request, response);
	}
}
