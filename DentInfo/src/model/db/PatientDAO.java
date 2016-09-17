package model.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import model.exceptions.InvalidEmailException;
import model.exceptions.InvalidUserNameException;
import model.mainObjects.Patient;

public class PatientDAO {
	
	private static PatientDAO instance;
	
	private PatientDAO(){}
	
	public synchronized static PatientDAO getInstance(){
		if(instance == null){
			instance = new PatientDAO();
		}
		return instance;
	}
	
	public Set<Patient> getAllPatients() throws InvalidEmailException, InvalidUserNameException{
		Set<Patient> patients = new HashSet<Patient>();
		try {
			Statement st = DBManager.getInstance().getConnection().createStatement();
			System.out.println("statement created");
			ResultSet resultSet = st.executeQuery("SELECT password, email, address, first_name, last_name, egn, phone, user_id FROM users WHERE fk_user_type_id LIKE (3);");
			System.out.println("result set created");
			while(resultSet.next()){
				Patient p = new Patient(resultSet.getString("email"),resultSet.getString("password"),resultSet.getInt("user_id"));
				if(resultSet.getString("address") != null){
					p.setAddress(resultSet.getString("address"));
				}
				p.setFirstName(resultSet.getString("first_name"));
				p.setLastName(resultSet.getString("last_name"));
				p.setEgn(resultSet.getString("egn"));
				if(resultSet.getString("phone") != null){
					p.setPhone(resultSet.getString("phone"));
				}
				patients.add(p);
			}
		} catch (SQLException e) {
			System.out.println("Oops, cannot make statement.");
			return patients;
		}
		System.out.println("Managers loaded successfully");
		DBManager.getInstance().closeConnection();
		return patients;
	}
	
	public void savePatient(Patient patient){
		try {
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement("INSERT INTO users (password, email, fk_user_type_id) VALUES (?, ?, ?);");
			st.setString(1, patient.getPassword());
			st.setString(2, patient.getEmail());
			st.setInt(3, 3);
			st.executeUpdate();
			System.out.println("inserted patient in db");
			System.out.println(patient.getEmail());
			ResultSet rs = st.getGeneratedKeys();
			if(rs.next())
            {
                int last_inserted_id = rs.getInt(1);
                patient.setUserId(last_inserted_id);
            }
			System.out.println("Patient added successfully");
		} catch (SQLException e) {
			System.out.println("Oops .. did not save the user");
			e.printStackTrace();
		}
	}

}
