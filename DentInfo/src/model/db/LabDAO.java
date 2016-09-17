package model.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import model.exceptions.InvalidUserNameException;
import model.inTheLab.DentalLaboratory;
import model.inTheLab.Manager;
import model.inTheLab.ManagersManager;

public class LabDAO {
	
	private static LabDAO instance;
	
	private LabDAO(){}
	
	public synchronized static LabDAO getInstance(){
		if(instance == null){
			instance = new LabDAO();
		}
		return instance;
	}
	
	public Set<DentalLaboratory> getAllLabs() throws InvalidUserNameException{
		Set<DentalLaboratory> labs = new HashSet<DentalLaboratory>();
		try {
			Statement st = DBManager.getInstance().getConnection().createStatement();
			System.out.println("Statement made");
			ResultSet rs = st.executeQuery("SELECT l.name, l.bulstat, l.address, l.lab_id, u.email FROM laboratories l JOIN users u ON (l.lab_id = u.lab_id);");
			System.out.println("result set created");
			while(rs.next()){
				ManagersManager managers = ManagersManager.getInstance();
				Manager m = managers.getManager(rs.getString("email"));
				DentalLaboratory laboratory = new DentalLaboratory(rs.getString("bulstat"), rs.getString("name"), rs.getString("address"),  m, rs.getInt("lab_id"));
				labs.add(laboratory);
			}
		} catch (SQLException e) {
			System.out.println("Error getting the labs!");
			e.printStackTrace();
		}
		System.out.println("labs loaded successfully");
		DBManager.getInstance().closeConnection();
		return labs;
	}
	
	public void createLab(DentalLaboratory dentLab){
		
		try {
			PreparedStatement ps = DBManager.getInstance().getConnection().prepareStatement("INSERT INTO laboratories (name,bulstat,fk_manager_id,address) VALUES (?,?,?,?); ");
			ps.setString(1, dentLab.getName());
			ps.setString(2, dentLab.getBulstat());
			ps.setInt(3, dentLab.getManager().getUser_id());
			ps.setString(4, dentLab.getAddress());
			ps.executeUpdate();			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next())
            {
                int last_inserted_id = rs.getInt(1);
                dentLab.setLabId(last_inserted_id);
            }
		} catch (SQLException e) {
			System.out.println("Error creating the lab!");
			e.printStackTrace();
		}
		
	}
	
	
}
