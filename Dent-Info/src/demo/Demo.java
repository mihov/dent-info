package demo;

import exceptions.InvalidEmailException;
import exceptions.InvalidUserNameException;
import inTheLab.DateReason;
import inTheLab.Manager;
import inTheLab.Order;
import inTheLab.Service;
import inTheLab.ToothPosition;
import mainObjects.Dentist;
import mainObjects.Patient;
import mainObjects.Person;
import mainObjects.TheSystem;

public class Demo {

	public static void main(String[] args) {
		System.out.println("Start!");

		TheSystem s1 = new TheSystem();

		s1.createNewManager("manager", "Manager1@abv.bg", "Password","8512136427");
		s1.createNewManager("manager", "Manager1@abv.bg", "Password", "9003145012");

		System.out.println("Login: " + s1.logIn("manager", "Passwor"));
		System.out.println("Login: " + s1.logIn("manage", "Password"));
		System.out.println("Login: " + s1.logIn("manager", "Password"));
		
		
		
		Person pp1 = s1.logIn("manager", "Password");
		
		Manager m1 = null;
		Dentist d1 = null;
		Patient p1 = null;
		//checking the instance of the person
		if (pp1 instanceof Manager) {
			m1 = (Manager) pp1;
			System.out.println("I'm a manager");
		}

		if (pp1 instanceof Dentist) {
			d1 = (Dentist) pp1;
			System.out.println("I'm a dentist");
		}

		if (pp1 instanceof Patient) {
			p1 = (Patient) pp1;
			System.out.println("I'm a patient");
		}

		System.out.println("Create Lab: " + s1.getManager("manager").createLab("1234567890", "Dental Lab 123"));
		System.out.println("Create Lab: " + s1.getManager("manager").createLab("1234567890", "Dental Lab 123"));
		System.out.println(s1.getLab("1234567890").getName());
		
		
		if (m1 != null) {
			m1.addNewDentist("dentist1", "dentist1@asd.ddd","324252","7507238541");
			m1.addNewService("Metalo-Keramika", "MK", 24.00, "mk01");
			System.out.println(m1.getCurrentLab().getService("mk01").showFull());
		}

		System.out.println("End!");
	}
}