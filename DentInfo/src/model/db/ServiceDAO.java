package model.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import model.inTheLab.DentalLaboratory;
import model.inTheLab.LaboratoryManager;
import model.inTheLab.Service;
import model.inTheLab.UserManager;
import model.mainObjects.Person;

public class ServiceDAO {
	
	private static ServiceDAO instance;
	
	private ServiceDAO(){}
	
	public synchronized static ServiceDAO getInstance(){
		if(instance == null){
			instance = new ServiceDAO();
		}
		return instance;
	}
	
	public Set<Service> getAllServices(){
		Set<Service> services = new HashSet<Service>();
		try {
			Statement st = DBManager.getInstance().getConnection().createStatement();
			System.out.println("Statement made");
			ResultSet rs = st.executeQuery("SELECT services_id , name_short, name_long, price, fk_lab_id FROM services s;");
			System.out.println("result set created");
			while(rs.next()){
				DentalLaboratory dl = LaboratoryManager.getInstatnce().getLab(rs.getInt("fk_lab_id"));
				Service service = new Service(rs.getString("name_long"), rs.getString("name_short"), rs.getDouble("price"), dl);
				services.add(service);
				service.setLabId(dl.getLabID());
				service.setSerialNumber(rs.getInt("services_id"));
				dl.addServiceToList(service);
			}
		} catch (SQLException e) {
			System.out.println("Error getting the labs!");
			e.printStackTrace();
		}
		System.out.println("labs loaded successfully");
		DBManager.getInstance().closeConnection();
		return services;
	}
	
	public void createService(Service s){
		try {
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement("INSERT INTO services (name_short,name_long,price,fk_lab_id) VALUE(?,?,?,?);");
			st.setString(1, s.getShortName());
			st.setString(2, s.getLongName());
			st.setDouble(3, s.getPrice());
			st.setInt(4, s.getLabId());
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			if(rs.next())
            {
                int last_inserted_id = rs.getInt(1);
                s.setSerialNumber(last_inserted_id);
            }
			System.out.println("Added the service in db here!");
			DBManager.getInstance().closeConnection();
		} catch (SQLException e) {
			System.out.println("ERROR with statement for creating service in db");
			e.printStackTrace();
		}
	}
	
	public void changerService(Service s,String newShortName,String newLongName,Double newPrice){
		try {
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement("UPDATE services SET name_long=? WHERE services_id LIKE (?);");
			st.setString(1, newLongName);
			st.setInt(2, s.getSerialNumber());
			st.executeUpdate();
			st = DBManager.getInstance().getConnection().prepareStatement("UPDATE services SET price=? WHERE services_id LIKE (?);");
			st.setDouble(1, newPrice);
			st.setInt(2, s.getSerialNumber());
			st.executeUpdate();
			st = DBManager.getInstance().getConnection().prepareStatement("UPDATE services SET name_short=? WHERE services_id LIKE (?);");
			st.setString(1, newShortName);
			st.setInt(2, s.getSerialNumber());
			st.executeUpdate();
			DBManager.getInstance().closeConnection();
			System.out.println("Edited service !!!");
		} catch (SQLException e) {
			System.out.println("ERROR with statement for editing service in db");
			e.printStackTrace();
		}
	}

}
