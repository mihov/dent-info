package model.inTheLab;

import java.util.concurrent.ConcurrentHashMap;

import model.db.LabDAO;
import model.exceptions.InvalidUserNameException;


public class LaboratoryManager {
	
	private ConcurrentHashMap<Integer, DentalLaboratory> registerredLaboratories;
	
	private static LaboratoryManager labMan;
	
	private LaboratoryManager(){
		registerredLaboratories = new ConcurrentHashMap<>();
		try {
			for(DentalLaboratory d : LabDAO.getInstance().getAllLabs()){
				registerredLaboratories.put(d.getLabID(), d);
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
	
	public synchronized DentalLaboratory getLab(int lab_id){
		return registerredLaboratories.get(lab_id);
	}
	
	public void registerLab(String bulstat,String name,String address,Manager manager){
		try {
			DentalLaboratory dl = new DentalLaboratory(bulstat, name, address, manager,0);
			System.out.println("Lab created");
			LabDAO.getInstance().createLab(dl);
			manager.setCurrentLab(dl);
			registerredLaboratories.put(dl.getLabID(), dl);
		} catch (InvalidUserNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void editLab(int labId,String name,String bulstat,String address){
		DentalLaboratory dentLab = LaboratoryManager.getInstatnce().getLab(labId);
		LabDAO.getInstance().editLab(dentLab, name, bulstat, address);
		try {
			registerredLaboratories.get(labId).setName(name);
			registerredLaboratories.get(labId).setBulstat(bulstat);
			registerredLaboratories.get(labId).setAddress(address);
		} catch (InvalidUserNameException e) {
			System.out.println("Invalid name in laboratory manager");
			e.printStackTrace();
		}
	}

}
