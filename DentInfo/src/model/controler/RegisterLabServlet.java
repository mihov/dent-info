package model.controler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.inTheLab.LaboratoryManager;
import model.inTheLab.Manager;
import model.inTheLab.ManagersManager;

@WebServlet("/RegisterLabServlet")
public class RegisterLabServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String email2 = request.getParameter("confirm_email");
		String bulstat = request.getParameter("bulstat");
		String website = request.getParameter("website");
		String address = request.getParameter("occupation");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		HttpSession ses = request.getSession();
		String user = (String) request.getSession().getAttribute("logedAs");
		System.out.println(user);
		Manager manager = ManagersManager.getInstance().getManager(user);
		manager.getPassword();
		LaboratoryManager.getInstatnce().registerLab(bulstat, name, address, manager, website);
		RequestDispatcher rd = request.getRequestDispatcher("manager_lib_create.html");//TODO kade shte izvejda pri registraciq na laboratoriq
		rd.forward(request, response);
	}

}
