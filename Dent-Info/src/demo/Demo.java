package demo;

import java.util.Date;

import exceptions.InvalidEmailException;
import exceptions.InvalidUserNameException;
import inTheLab.DateReason;
import inTheLab.Order;
import inTheLab.Service;
import inTheLab.ToothPosition;
import mainObjects.Dentist;
import mainObjects.Patient;
import mainObjects.TheSystem;

public class Demo {

	public static void main(String[] args) {
		System.out.println("Start!");
		
		TheSystem s1 = new TheSystem();

		System.out.println("Create manager: " + s1.createNewManager("manager", "Manager1@abv.bg", "Password"));
		System.out.println("Create manager: " + s1.createNewManager("manager", "Manager1@abv.bg", "Password"));

		System.out.println("Login: " + s1.logInCheck("manager", "Passwor"));
		System.out.println("Login: " + s1.logInCheck("manage", "Password"));
		System.out.println("Login: " + s1.logInCheck("manager", "Password"));

		System.out.println("Create Lab: " + s1.getManager("manager").createLab("1234567890", "Dental Lab 123", null));
		System.out.println("Create Lab: " + s1.getManager("manager").createLab("1234567890", "Dental Lab 123", null));
		System.out.println(s1.getLab("1234567890").getName());
		System.out.println(s1.getLab("123456780").getName());
		

		System.out.println("End!");
	}
}