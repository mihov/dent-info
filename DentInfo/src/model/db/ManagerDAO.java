package model.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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
	
	public Set<Manager> getAllUsers(){
		Set<Manager> users = new HashSet<Manager>();
		try {
			Statement st = DBManager.getInstance().getConnection().createStatement();
			ResultSet resultSet = st.executeQuery("SELECT username, name, password, email, address FROM users;");
			while(resultSet.next()){
				users.add(new Manager(resultSet.getString("username"),
									resultSet.getString("name"),
									resultSet.getString("password"),
									resultSet.getString("email"),
									resultSet.getString("address")
									));
			}
		} catch (SQLException e) {
			System.out.println("Oops, cannot make statement.");
			return users;
		}
		System.out.println("Users loaded successfully");
		return users;
	}
	
	public void saveUser(Manager user){
		try {
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement("INSERT INTO users (username, name, password, email, address) VALUES (?, ?, ?, ?, ?);");
			st.setString(1, user.getUsername());
			st.setString(2, user.getName());
			st.setString(3, user.getPassword());
			st.setString(4, user.getEmail());
			st.setString(5, user.getAddress());
			st.executeUpdate();
			System.out.println("User added successfully");
		} catch (SQLException e) {
			System.out.println("Oops .. did not save the user");
			e.printStackTrace();
		}
		
	}
}
