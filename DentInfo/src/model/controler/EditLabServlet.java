package model.controler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.exceptions.InvalidUserNameException;
import model.inTheLab.DentalLaboratory;
import model.inTheLab.LaboratoryManager;
import model.inTheLab.UserManager;
import model.mainObjects.Person;

@WebServlet("/EditLabServlet")
public class EditLabServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String labNewName = (String) request.getParameter("lab_name");
		String labNewBulstat = (String) request.getParameter("lab_bulstat");
		String labNewAdress = (String) request.getParameter("lab_address");
		String managerEmail = (String) request.getSession().getAttribute("logged");
		Person currentManager = UserManager.getInstance().getUser(managerEmail);
		DentalLaboratory currentLab = LaboratoryManager.getInstatnce().getLab(currentManager.getLab_id());
		System.out.println("New name of Lab ----------- " + labNewName + " ---------------------");
		RequestDispatcher rd = null;
		if(!(labNewName.isEmpty() || labNewName == null)){
			if(!(labNewName.isEmpty() || labNewName == null)){
				if(!(labNewName.isEmpty() || labNewName == null)){
					LaboratoryManager.getInstatnce().editLab(currentLab.getLabID(), labNewName, labNewBulstat, labNewAdress);
					rd = request.getRequestDispatcher("manager_main.jsp");
				}else{
					rd = request.getRequestDispatcher("manager_edit_lab.jsp");
				}
			}else{
				rd = request.getRequestDispatcher("manager_edit_lab.jsp");
			}
		}else{
			rd = request.getRequestDispatcher("manager_edit_lab.jsp");
		}
		
		rd.forward(request, response);
		
	}

}
