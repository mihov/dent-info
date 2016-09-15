package model.db;
/*Server: sql7.freemysqlhosting.net
Name: sql7133613
Username: sql7133613
Password: AnggnZlgzp
Port number: 3306

jdbc:mysql://sql7.freemysqlhosting.net:3306/?user=sql7133613*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	private static DBManager instance;
	private Connection connection;

	private static final String DB_IP = "sql7.freemysqlhosting.net";
	private static final String DB_PORT = "3306";
	private static final String DB_NAME = "sql7133613";
	private static final String DB_USERNAME = "sql7133613";
	private static final String DB_PASSWORD = "AnggnZlgzp";
	private static final String URL = "jdbc:mysql://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;

	private DBManager() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	synchronized static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	Connection getConnection() {
		return connection;
	}
}
