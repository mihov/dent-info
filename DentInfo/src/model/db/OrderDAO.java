package model.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import model.exceptions.InvalidEmailException;
import model.exceptions.InvalidUserNameException;
import model.inTheLab.Manager;
import model.inTheLab.Order;
import model.inTheLab.UserManager;
import model.mainObjects.Dentist;
import model.mainObjects.Patient;

public class OrderDAO {	
	
	private static OrderDAO instance;
	
	private OrderDAO(){}
	
	public synchronized static OrderDAO getInstance(){
		if(instance == null){
			instance = new OrderDAO();
		}
		return instance;
	}

	public Set<Order> getAllOrders() throws InvalidEmailException, InvalidUserNameException{
		Set<Order> orders = new HashSet<Order>();
		try {
			Statement st = DBManager.getInstance().getConnection().createStatement();
			System.out.println("statement created");
			ResultSet resultSet = st.executeQuery("SELECT order_id, fk_dentist_id, fk_patient_id, fk_lab_id, date_enter, price FROM orders;");
			System.out.println("result set created");
			while(resultSet.next()){
				Patient p = (Patient) UserManager.getInstance().getUserById(resultSet.getInt("fk_patient_id"));
				Dentist d = (Dentist) UserManager.getInstance().getUserById(resultSet.getInt("fk_dentist_id"));
				Order o = null;
				if(p != null || d != null){
					o = new Order(p,d);
				}
				else{
					System.out.println("ERROR LOOK AT ORDER DAO SOMETHING IS WRONG");
					throw new NullPointerException("ERROR AT ORDER DAO");
				}

				orders.add(o);
			}
		} catch (SQLException e) {
			System.out.println("Oops, cannot make statement.");
			return orders;
		}
		System.out.println("Orders loaded successfully");
		DBManager.getInstance().closeConnection();
		return orders;
	}
	
}
