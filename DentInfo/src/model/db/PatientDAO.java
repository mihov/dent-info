package model.db;

public class PatientDAO {
	
	private static PatientDAO instance;
	
	private PatientDAO(){}
	
	public synchronized static PatientDAO getInstance(){
		if(instance == null){
			instance = new PatientDAO();
		}
		return instance;
	}

}
