package model.controler;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.inTheLab.DentalLaboratory;
import model.inTheLab.LaboratoryManager;
import model.inTheLab.Service;
import model.inTheLab.ServiceManager;
import model.inTheLab.UserManager;
import model.mainObjects.Person;

@WebServlet("/EditServiceServlet")
public class EditServiceServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = (String)request.getSession().getAttribute("logged");
		System.out.println("email " + email + " ------------------------>");
		Person p = UserManager.getInstance().getUser(email);
		DentalLaboratory dl = LaboratoryManager.getInstatnce().getLab(p.getLab_id());
		Map<Integer,Service> m = ServiceManager.getInstance().getAllServices(dl.getLabID());
		Integer numb = Integer.parseInt(request.getParameter("serial_number"));
		String serviceNewShortName = request.getParameter("service_new_long_name");
		String serviceNewLongName = request.getParameter("service_new_short_name");
		String serviceNewPrice = request.getParameter("service_new_price");
		Double newPrice = Double.parseDouble(serviceNewPrice);
		System.out.println("Entered the servlet");
		System.out.println("serial number --------" );
		Service s = ServiceManager.getInstance().getService(numb);
		System.out.println("--------------" + s + "------------------");
		ServiceManager.getInstance().editService(s, serviceNewShortName, serviceNewLongName, newPrice);
		RequestDispatcher rd = request.getRequestDispatcher("manager_create_service.jsp");
		rd.forward(request, response);
	}

}
