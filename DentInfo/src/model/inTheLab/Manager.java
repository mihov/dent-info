package model.inTheLab;

import model.exceptions.InvalidEgnException;
import model.exceptions.InvalidEmailException;
import model.exceptions.InvalidUserNameException;
import model.exceptions.ThisUserExistException;
import model.mainObjects.Dentist;
import model.mainObjects.Person;

public class Manager extends Person {

	public Manager(String email, String password,int user_id)throws InvalidEmailException, InvalidUserNameException{
		super(email, password, user_id);
	}
	
	
	public Manager(int user_id, String email, String password, String firstName, String lastName, String egn,
			String address, String phone, Integer fk_user_type_id, Integer fk_dentist_id, Integer lab_id) {
		super( user_id,  email,  password,  firstName,  lastName,  egn,
				 address,  phone,  fk_user_type_id,  fk_dentist_id,  lab_id);
		System.out.println("Manager created!!!");
	}


	public Boolean createLab(String bulstat, String name) {
//		if (this.getCurrentLab() == null) {
//			if (!this.theSystem.doesThisLabExist(bulstat)) {
//				try {
//					this.setCurrentLab(new DentalLaboratory(bulstat, name, null, this));
//					this.theSystem.addNewLab(getCurrentLab());
//					return true;
//				} catch (InvalidUserNameException e) {
//					System.err.println(e.getMessage());
//					return false;
//				}
//			}
//			System.err.println("This Lab exist!!!");
//			return false;
//		} else {
//			System.err.println("You already have a Lab.");
//			return false;
//		}
		return false;
	}

	public Boolean addNewDentist(String username, String email,String password,String egn) {
//		try {
//			Dentist tempDentist = new Dentist(username, email,password,egn);
//
//			if (this.theSystem.addUser(tempDentist)) {
//				this.getCurrentLab().setNewDentist(tempDentist);
//				return true;
//			} else {
//				throw new ThisUserExistException(username);
//			}
//		} catch (InvalidEmailException e) {
//			System.out.println(e.getMessage());
//		} catch (InvalidUserNameException e1) {
//			System.out.println(e1.getMessage());
//		} catch (ThisUserExistException e2) {
//			System.out.println(e2.getMessage());
//		}
		return false;
	}

//	public Boolean addNewService(String longName, String shortName, Double price, String serialNumber) {
//		Service service = new Service(longName, shortName, price, serialNumber,this.getCurrentLab());
//		if (this.getCurrentLab().getService(serialNumber) == null) {
//			this.getCurrentLab().addServiceToList(service);
//			return true;
//		} else {
//			return false;
//		}
//	}

//	public Boolean changeService(String longName, String shortName, Double price, String serialNumber) {
//		Service service = new Service(longName, shortName, price, serialNumber,this.getCurrentLab());
//		if (this.getCurrentLab().getService(serialNumber) != null) {
//			return this.getCurrentLab().changeService(service);
//			
//		} else {
//			return false;
//		}
//	}
	
	public void changeManagerData(){
		//TODO changing the managers personal data 
	}
}
