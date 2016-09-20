package model.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import model.exceptions.InvalidEmailException;
import model.exceptions.InvalidUserNameException;
import model.inTheLab.DentalLaboratory;
import model.inTheLab.LaboratoryManager;
import model.mainObjects.Dentist;
import model.tools.SendMail;

public class DentistDAO {
	
	private static DentistDAO instance;
	
	private DentistDAO(){}
	
	public synchronized static DentistDAO getInstance(){
		if(instance == null){
			instance = new DentistDAO();
		}
		return instance;
	}
	
	public Set<Dentist> getAllDentists() throws InvalidEmailException, InvalidUserNameException{
		Set<Dentist> users = new HashSet<Dentist>();
		try {
			Statement st = DBManager.getInstance().getConnection().createStatement();
			System.out.println("statement created");
			ResultSet resultSet = st.executeQuery("SELECT password, email, address, first_name, last_name, egn, phone, user_id, lab_id FROM users WHERE fk_user_type_id LIKE (2);");
			System.out.println("result set created");
			while(resultSet.next()){
				DentalLaboratory dl = LaboratoryManager.getInstatnce().getLab(resultSet.getInt("lab_id"));
				Dentist d = new Dentist(resultSet.getString("email"),resultSet.getString("password"),resultSet.getInt("user_id"),dl);
				if(resultSet.getString("address") != null){
					d.setAddress(resultSet.getString("address"));
				}
				d.setFirstName(resultSet.getString("first_name"));
				d.setLastName(resultSet.getString("last_name"));
				d.setEgn(resultSet.getString("egn"));
				if(resultSet.getString("phone") != null){
					d.setPhone(resultSet.getString("phone"));
				}
				users.add(d);
			}
		} catch (SQLException e) {
			System.out.println("Oops, cannot make statement.");
			return users;
		}
		System.out.println("Dentists loaded successfully");
		DBManager.getInstance().closeConnection();
		return users;
	}
	
	public void saveDentist(Dentist dentist){
		try {
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement("INSERT INTO users (password, email, fk_user_type_id) VALUES (?, ?, ?);");
			st.setString(1, dentist.getPassword());
			st.setString(2, dentist.getEmail());
			st.setInt(3, 2);
			st.executeUpdate();
			System.out.println("inserted dentist in db");
			System.out.println(dentist.getEmail());
			ResultSet rs = st.getGeneratedKeys();
			if(rs.next())
            {
                int last_inserted_id = rs.getInt(1);
                dentist.setUserId(last_inserted_id);
            }
			
			String sub = "New account!!!";
			String msg = "<h1>You have an dentist account</h1><a href=\"http://dent-info.net\"><h1>www.dent-info.net</h1></a><h1>password: "
					+ dentist.getPassword() + "</h1>";
			SendMail.sendMail(dentist.getEmail(), sub, msg);
			DBManager.getInstance().closeConnection();
			System.out.println("Dentist added successfully");
		} catch (SQLException e) {
			System.out.println("Oops .. did not save the dentist");
			e.printStackTrace();
		}
	}
	
}
