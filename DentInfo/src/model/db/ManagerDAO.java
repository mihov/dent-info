package model.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import model.exceptions.InvalidEmailException;
import model.exceptions.InvalidUserNameException;
import model.inTheLab.Manager;

public class ManagerDAO {
	
	private static ManagerDAO instance;
	
	private ManagerDAO(){}
	
	public synchronized static ManagerDAO getInstance(){
		if(instance == null){
			instance = new ManagerDAO();
		}
		return instance;
	}
	
	public Set<Manager> getAllManagers() throws InvalidEmailException, InvalidUserNameException{
		Set<Manager> users = new HashSet<Manager>();
		try {
			Statement st = DBManager.getInstance().getConnection().createStatement();
			System.out.println("statement created");
			ResultSet resultSet = st.executeQuery("SELECT password, email, address, first_name, last_name, egn, phone, user_id FROM users;");
			System.out.println("result set created");
			while(resultSet.next()){
				Manager m = new Manager(resultSet.getString("email"),resultSet.getString("password"),resultSet.getInt("user_id"));
				m.setAddress(resultSet.getString("address"));
				m.setFirstName(resultSet.getString("first_name"));
				m.setLastName(resultSet.getString("last_name"));
				m.setEgn(resultSet.getString("egn"));
				if(resultSet.getString("phone") != null){
					m.setPhone(resultSet.getString("phone"));
				}
				users.add(m);
			}
		} catch (SQLException e) {
			System.out.println("Oops, cannot make statement.");
			return users;
		}
		System.out.println("Managers loaded successfully");
		return users;
	}
	
	public void saveUser(Manager manager){
		try {
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement("INSERT INTO users (password, email, fk_user_type_id) VALUES (?, ?, ?);");
			st.setString(1, manager.getPassword());
			st.setString(2, manager.getEmail());
			st.setInt(3, 1);
			st.executeUpdate();
			System.out.println("User added successfully");
		} catch (SQLException e) {
			System.out.println("Oops .. did not save the user");
			e.printStackTrace();
		}
		
	}
}
