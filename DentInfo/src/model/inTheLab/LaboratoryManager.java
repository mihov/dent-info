package model.inTheLab;

import java.util.concurrent.ConcurrentHashMap;

import model.db.LabDAO;
import model.exceptions.InvalidUserNameException;


public class LaboratoryManager {
	
	private ConcurrentHashMap<String, DentalLaboratory> registerredLaboratories;
	
	private static LaboratoryManager labMan;
	
	private LaboratoryManager(){
		registerredLaboratories = new ConcurrentHashMap<>();
		try {
			for(DentalLaboratory d : LabDAO.getInstance().getAllLabs()){
				registerredLaboratories.put(d.getBulstat(), d);
			}
		} catch (InvalidUserNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized static  LaboratoryManager getInstatnce(){
		if(labMan == null){
			labMan = new LaboratoryManager();
		}
		return labMan;
	}
	
	public void registerLab(String bulstat,String name,String address,Manager manager,String website){
		try {
			DentalLaboratory dl = new DentalLaboratory(bulstat, name, address, manager,website,0);
			System.out.println("Lab created");
			manager.setCurrentLab(dl);
			LabDAO.getInstance().createLab(dl);
			registerredLaboratories.put(dl.getBulstat(), dl);
		} catch (InvalidUserNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
