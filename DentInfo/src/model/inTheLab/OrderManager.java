package model.inTheLab;

import java.util.concurrent.ConcurrentHashMap;

import model.db.ManagerDAO;
import model.db.OrderDAO;
import model.exceptions.InvalidEmailException;
import model.exceptions.InvalidUserNameException;

public class OrderManager {	
	
	private ConcurrentHashMap<Integer, Order> orders;
	
	private static OrderManager instance;
	
	private OrderManager(){
		orders = new ConcurrentHashMap<>();
		try {
			for(Order o : OrderDAO.getInstance().getAllOrders()){
				orders.put(o.getId(), o);
			}
			System.out.println("Orders added");
		} catch (InvalidEmailException e) {
			System.out.println("An error with adding the managers in ManagersManager");
		} catch (InvalidUserNameException e) {
			System.out.println("An error with adding the managers in ManagersManager");
		}
	}
	
	public synchronized static OrderManager getInstance(){
		if(instance == null){
			instance = new OrderManager();
		}
		return instance;
	}
	
	public Order getOrder(Integer id){
		return orders.get(id);
	}
	
}
