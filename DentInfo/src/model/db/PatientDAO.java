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
import model.tools.*;

public class PatientDAO {

	private static PatientDAO instance;

	private PatientDAO() {
	}

	public synchronized static PatientDAO getInstance() {
		if (instance == null) {
			instance = new PatientDAO();
		}
		return instance;
	}

	public Set<Patient> getAllPatients() throws InvalidEmailException, InvalidUserNameException {
		Set<Patient> patients = new HashSet<Patient>();
		try {
			Statement st = DBManager.getInstance().getConnection().createStatement();
			System.out.println("statement created");
			ResultSet resultSet = st.executeQuery(
					"SELECT password, email, address, first_name, last_name, egn, phone, user_id, fk_user_type_id, fk_dentist_id, lab_id FROM users WHERE fk_user_type_id LIKE (3);");
			System.out.println("result set created");
			while (resultSet.next()) {
				// Patient p = new Patient(resultSet.getString("email"),
				// resultSet.getString("password"),
				// resultSet.getInt("user_id"));
				Patient patient = new Patient(resultSet.getInt("user_id"), resultSet.getString("email"),
						resultSet.getString("password"), resultSet.getString("first_name"),
						resultSet.getString("last_name"), resultSet.getString("egn"), resultSet.getString("address"),
						resultSet.getString("phone"), resultSet.getInt("fk_user_type_id"),
						resultSet.getInt("fk_dentist_id"), resultSet.getInt("lab_id"));
				// if (resultSet.getString("address") != null) {
				// p.setAddress(resultSet.getString("address"));
				// }
				// p.setFirstName(resultSet.getString("first_name"));
				// p.setLastName(resultSet.getString("last_name"));
				// p.setEgn(resultSet.getString("egn"));
				//
				// if (resultSet.getString("phone") != null) {
				// p.setPhone(resultSet.getString("phone"));
				// }
				// patients.add(p);
				patients.add(patient);
			}
		} catch (SQLException e) {
			System.out.println("Oops, cannot make statement.");
			return patients;
		}
		System.out.println("Patients loaded successfully");
		DBManager.getInstance().closeConnection();
		return patients;
	}

	public void savePatient(Patient patient) {
		try {
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(
					"INSERT INTO users (email, password, first_name, last_name, fk_user_type_id, fk_dentist_id, lab_id) VALUES (?, ?, ?, ?, ?, ?, ?);");
			/*
			 * email, password, firstName, lastName, fk_user_type_id,
			 * fk_dentist_id, lab_id
			 */
			st.setString(1, patient.getEmail());
			st.setString(2, patient.getPassword());
			st.setString(3, patient.getFirstName());
			st.setString(4, patient.getLastName());
			st.setInt(5, patient.getFk_user_type_id());
			st.setInt(6, patient.getFk_dentist_id());
			st.setInt(7, patient.getLab_id());

			st.executeUpdate();
			System.out.println("inserted patient in db");
			System.out.println(patient.getEmail());
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				int last_inserted_id = rs.getInt(1);
				patient.setUserId(last_inserted_id);
			}
			System.out.println("Patient added successfully");
			String sub = "New account!!!";
			String msg = "<h1>You have an account</h1><a href=\"http://dent-info.net\"><h1>www.dent-info.net</h1></a><h1>password: "
					+ patient.getPassword() + "</h1>";
			SendMail.sendMail(patient.getEmail(), sub, msg);
		} catch (SQLException e) {
			System.out.println("Oops .. did not save the user");
			e.printStackTrace();
		}
	}

}
