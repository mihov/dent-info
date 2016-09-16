package model.db;

public class ServiceDAO {
	
	private static ServiceDAO instance;
	
	private ServiceDAO(){}
	
	public synchronized static ServiceDAO getInstance(){
		if(instance == null){
			instance = new ServiceDAO();
		}
		return instance;
	}

}
