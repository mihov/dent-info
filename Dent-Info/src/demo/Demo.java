package demo;

import java.util.Date;

import exceptions.InvalidEmailException;
import exceptions.InvalidUserNameException;
import mainObjects.DateReason;
import mainObjects.Dentist;
import mainObjects.Order;
import mainObjects.Patient;
import mainObjects.Service;
import mainObjects.ToothPosition;

public class Demo {

	public static void main(String[] args) {
		Patient p1 = null;
		Dentist d1 = null;
		try {
			p1 = new Patient("patient1", "patient1@email.com", "Password");
		} catch (InvalidEmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidUserNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			d1 = new Dentist("dentist1", "dentist1@mail.com", "Password");
		} catch (InvalidEmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidUserNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		Order or1 = new Order(p1, d1);
		or1.addDate(DateReason.ENTER, new Date() );
		System.out.println(
		or1.getDateList());
		
		or1.addService(ToothPosition.DL1, new Service("Metalo-keramika", "MeKer", 20.00));
		or1.addService(ToothPosition.DL1, new Service("Plastika", "Plast", 30.00));
		or1.addService(ToothPosition.DL1, new Service("Korona", "Kor", 40.00));
		or1.addService(ToothPosition.DL2, new Service("Metalo-keramika", "MeKer", 20.00));
		or1.addService(ToothPosition.DL3, new Service("Metalo-keramika", "MeKer", 20.00));
		
		
		
		
		System.out.println(or1.getServiceList());

	}
}