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
			ResultSet rs = st.executeQuery("SELECT name_short, name_long, price, fk_lab_id FROM services s;");
			System.out.println("result set created");
			while(rs.next()){
				DentalLaboratory dl = LaboratoryManager.getInstatnce().getLab(rs.getInt("fk_lab_id"));
				Service service = new Service(rs.getString("name_long"), rs.getString("name_short"), rs.getDouble("price"), dl);
				services.add(service);
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
                s.setDbId(last_inserted_id);
            }
			System.out.println("Added the service in db here!");
			DBManager.getInstance().closeConnection();
		} catch (SQLException e) {
			System.out.println("ERROR with statement for creating service in db");
			e.printStackTrace();
		}
	}

}
