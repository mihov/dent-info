package model.controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.inTheLab.Order;

@WebServlet("/SaveOrderStatusServlet")
public class SaveOrderStatusServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order om = OrderManager.getInstance.getOrder(request.getAttribute("order"));//vzimame order po order id
		//������ �� OrderManager
		String act = request.getParameter("act");
		if (act == null) {
		    
		} else if (act.equals("payed")) {
		    om.setAsPayed();
		} else if (act.equals("ready")) {
		    om.setAsReady();
		} else {
		    
		}
	}

}
