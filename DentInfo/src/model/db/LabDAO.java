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
import model.inTheLab.UserManager;
import model.mainObjects.Person;

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
			ResultSet rs = st.executeQuery("SELECT l.name, l.bulstat, l.address, l.lab_id, l.fk_manager_id, u.email FROM laboratories l JOIN users u ON (l.lab_id = u.lab_id);");
			System.out.println("result set created");
			while(rs.next()){
				UserManager managers = UserManager.getInstance();
				Person m =managers.getUser(rs.getString("email"));
				System.out.println("--------- vzemam manager: " + m.getEmail() + " ---------");
				System.out.println("--------- lab bulstat: " + rs.getString("bulstat") + " ---------");
				System.out.println("--------- lab name: " + rs.getString("name") + " ---------");
				System.out.println("--------- lab id: " + rs.getString("lab_id") + " ---------");
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
                Person p = dentLab.getManager();
                UserDAO.getInstance().setLab(dentLab, UserManager.getInstance().getUser(p.getEmail()));
                System.out.println("Dentist lab set: " + dentLab.getLabID() + " - " + dentLab.getManager().getEmail());
            }
			DBManager.getInstance().closeConnection();
		} catch (SQLException e) {
			System.out.println("Error creating the lab!");
			e.printStackTrace();
		}
		
	}
	
	public void editLab(DentalLaboratory dentLab,String name,String bulstat,String address){
		try {
			System.out.println(dentLab.getAddress() + " " + dentLab.getName() +  " " + dentLab.getBulstat() + " is this the problem");
			PreparedStatement ps = DBManager.getInstance().getConnection().prepareStatement("UPDATE laboratories SET name=? WHERE name LIKE (?);");
			ps.setString(1, name);
			ps.setString(2, dentLab.getName());
			ps.executeUpdate();
			DBManager.getInstance().closeConnection();
			PreparedStatement ps1 = DBManager.getInstance().getConnection().prepareStatement("UPDATE laboratories SET bulstat=? WHERE name LIKE (?);");
			ps1.setString(1, bulstat);
			ps1.setString(2, dentLab.getName());
			ps1.executeUpdate();
			DBManager.getInstance().closeConnection();
			PreparedStatement ps2 = DBManager.getInstance().getConnection().prepareStatement("UPDATE laboratories SET address=? WHERE name LIKE (?);");
			ps2.setString(1, address);
			ps2.setString(2, dentLab.getName());
			ps2.executeUpdate();
			DBManager.getInstance().closeConnection();
		} catch (SQLException e) {
			System.out.println("ERROR WITH prepared statement");
			e.printStackTrace();
		}
	}
	
	
}
