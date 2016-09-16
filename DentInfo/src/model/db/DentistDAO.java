package model.db;

public class DentistDAO {
	
	private static DentistDAO instance;
	
	private DentistDAO(){}
	
	public synchronized static DentistDAO getInstance(){
		if(instance == null){
			instance = new DentistDAO();
		}
		return instance;
	}
	
	
	
}
