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
import model.inTheLab.Manager;
import model.mainObjects.Dentist;
import model.mainObjects.Patient;
import model.mainObjects.Person;

public class UserDAO {
	private static UserDAO instance;

	private UserDAO() {
	}

	public synchronized static UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}

	/*
	 * user_id password fk_user_type_id fk_dentist_id email address phone egn
	 * first_name last_name lab_id
	 */

	public Set<Person> getAllUsers() throws InvalidEmailException, InvalidUserNameException {
		Set<Person> users = new HashSet<Person>();
		try {
			Statement st = DBManager.getInstance().getConnection().createStatement();
			System.out.println("statement created");
			ResultSet resultSet = st.executeQuery("SELECT user_id,email,password,first_name,last_name,egn,address,phone,fk_user_type_id,fk_dentist_id,lab_id FROM users;");
			System.out.println("result set created");
			while (resultSet.next()) {

				switch (resultSet.getInt("fk_user_type_id")) {
				case 1:
					users.add(new Manager(resultSet.getInt("user_id"), resultSet.getString("email"),
							resultSet.getString("password"), resultSet.getString("first_name"),
							resultSet.getString("last_name"), resultSet.getString("egn"), resultSet.getString("address"),
							resultSet.getString("phone"), resultSet.getInt("fk_user_type_id"),
							resultSet.getInt("fk_dentist_id"), resultSet.getInt("lab_id")));
					break;
				case 2:
					users.add(new Dentist(resultSet.getInt("user_id"), resultSet.getString("email"),
							resultSet.getString("password"), resultSet.getString("first_name"),
							resultSet.getString("last_name"), resultSet.getString("egn"), resultSet.getString("address"),
							resultSet.getString("phone"), resultSet.getInt("fk_user_type_id"),
							resultSet.getInt("fk_dentist_id"), resultSet.getInt("lab_id")));
					break;
				case 3:
					users.add(new Patient(resultSet.getInt("user_id"), resultSet.getString("email"),
							resultSet.getString("password"), resultSet.getString("first_name"),
							resultSet.getString("last_name"), resultSet.getString("egn"), resultSet.getString("address"),
							resultSet.getString("phone"), resultSet.getInt("fk_user_type_id"),
							resultSet.getInt("fk_dentist_id"), resultSet.getInt("lab_id")));
					break;

				default:
					break;
				}
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getSQLState());
			System.out.println(e.getErrorCode());
			System.out.println("Oops, cannot make statement.");
			return users;
		}
		System.out.println("Managers loaded successfully");
		return users;
	}
	
	public void setLab(DentalLaboratory dent,Person man){
		try {
			System.out.println(dent.getLabID());
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement("UPDATE users SET lab_id=? WHERE email LIKE (?);");
			st.setInt(1, dent.getLabID());
			st.setString(2, man.getEmail());
			st.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error while seting lab to a manager");
			e.printStackTrace();
		}
		
	}
	
}
