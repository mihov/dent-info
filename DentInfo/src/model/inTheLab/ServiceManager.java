package model.inTheLab;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import model.db.ManagerDAO;
import model.db.ServiceDAO;

public class ServiceManager {	
	
	private ConcurrentHashMap<Integer, Service> services;
	private static ServiceManager instance;
	
	private ServiceManager(){
		services = new ConcurrentHashMap<>();
		for(Service service : ServiceDAO.getInstance().getAllServices()){
			services.put(service.getSerialNumber(), service);
		}
	}
	
	public synchronized static ServiceManager getInstance(){
		if(instance == null){
			instance = new ServiceManager();
		}
		return instance;
	}
	
	public synchronized Service getService(Integer serialNumb){
		return services.get(serialNumb);
	}
	
	public Map<Integer, Service> getAllServices(int lab_id){
		HashMap<Integer, Service> services = new HashMap<>();
		for(Service s : this.services.values()){
			Service serv = s;
			System.out.println("getting services -------------" + serv.getShortName() + " lab " + s.getLabId());
			if(serv.getLabId() == lab_id){
				services.put(s.getSerialNumber(),serv);
			}
		}
		System.out.println("done adding services !!!!!!!!!!!!");
		return services;
	}
	
	public void registerService(Service s){
		System.out.println("service made");
		services.put(s.getSerialNumber(), s);
		ServiceDAO.getInstance().createService(s);
	}
	
	public void editService(Service s,String newShortName,String newLongName,Double newPrice){
		ServiceDAO.getInstance().changerService(s,newShortName,newLongName,newPrice);;
		s.setShortName(newShortName);
		s.setLongName(newLongName);
		s.setPrice(newPrice);
	}

}
