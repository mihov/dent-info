package model.controler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.inTheLab.LaboratoryManager;
import model.inTheLab.Manager;
import model.inTheLab.ManagersManager;
import model.inTheLab.Service;
import model.inTheLab.ServiceManager;
import model.inTheLab.UserManager;
import model.mainObjects.Person;

@WebServlet("/AddServiceServlet")
public class AddServiceServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Person p = UserManager.getInstance().getUser((String) request.getSession().getAttribute("logged"));
		String servLongName = (String)request.getParameter("service_long_name");
		String servShortName = (String)request.getParameter("service_short_name");
		String servPrice = request.getParameter("service_price");
		Double price = Double.parseDouble(servPrice);
		p.setCurrentLab(LaboratoryManager.getInstatnce().getLab(p.getLab_id()));
		Service s = new Service(servLongName, servShortName, price,p.getCurrentLab());
		s.setLabId(p.getCurrentLab().getLabID());
		ServiceManager.getInstance().registerService(s);
		System.out.println("Adding the service to " + p.getEmail() + " with lab " + p.getCurrentLab());
		p.getCurrentLab().addServiceToList(s);
		RequestDispatcher rd = request.getRequestDispatcher("manager_create_service.jsp");
		rd.forward(request, response);
	}

}
