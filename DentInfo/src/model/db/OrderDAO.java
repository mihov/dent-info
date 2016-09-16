package model.db;

public class OrderDAO {	
	
	private static OrderDAO instance;
	
	private OrderDAO(){}
	
	public synchronized static OrderDAO getInstance(){
		if(instance == null){
			instance = new OrderDAO();
		}
		return instance;
	}

}
