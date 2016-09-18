package model.controler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.inTheLab.Manager;
import model.inTheLab.ManagersManager;
import model.inTheLab.Service;

@WebServlet("/AddServiceServlet")
public class AddServiceServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Manager m = ManagersManager.getInstance().getManager((String) request.getSession().getAttribute("logged"));
		String servLongName = (String)request.getParameter("service_long_name");
		String servShortName = (String)request.getParameter("service_short_name");
		String servPrice = request.getParameter("service_price");
		String serialNumber = request.getParameter("serial_number");
		Double price = Double.parseDouble(servPrice);
		Service s = new Service(servLongName, servShortName, price, serialNumber);
		m.getCurrentLab().addServiceToList(s);
		RequestDispatcher rd = request.getRequestDispatcher("manager_create_service.jsp");
	}

}
