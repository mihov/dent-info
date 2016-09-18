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
				System.out.println(d.getBulstat() + " -bulstat " + d.getName() + " - name");
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
	
	public DentalLaboratory getLab(int lab_id){
		System.out.println(registerredLaboratories.get(lab_id).getName());
		return registerredLaboratories.get(lab_id);
	}
	
	public void registerLab(String bulstat,String name,String address,Manager manager){
		try {
			DentalLaboratory dl = new DentalLaboratory(bulstat, name, address, manager,0);
			System.out.println("Lab created");
			LabDAO.getInstance().createLab(dl);
			manager.setCurrentLab(dl);
			registerredLaboratories.put(dl.getBulstat(), dl);
		} catch (InvalidUserNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
