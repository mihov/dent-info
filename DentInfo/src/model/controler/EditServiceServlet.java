package model.controler;

import java.io.IOException;
import java.util.Map;

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
		
		String serviceShortName = request.getParameter("service_short_name");
		String serviceNewShortName = request.getParameter("service_new_long_name");
		String serviceNewLongName = request.getParameter("service_new_short_name");
		String serviceNewPrice = request.getParameter("service_new_price");
		
		Person p = UserManager.getInstance().getUser((String)request.getSession().getAttribute("logged"));
		DentalLaboratory dl = LaboratoryManager.getInstatnce().getLab(p.getLab_id());
		
		for(Service s : dl.getAllServices().values()){
			if(s.getShortName() == serviceShortName){
				ServiceManager.getInstance().
				s.setShortName(serviceNewShortName);
				s.setShortName(serviceNewLongName);
				s.setShortName(serviceNewPrice);
			}
		}
		
		
	}

}
