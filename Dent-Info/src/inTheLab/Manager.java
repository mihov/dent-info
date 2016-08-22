package inTheLab;

import exceptions.InvalidEmailException;
import exceptions.InvalidUserNameException;
import exceptions.ThisUserExist;
import mainObjects.Address;
import mainObjects.Dentist;
import mainObjects.People;
import mainObjects.TheSystem;

public class Manager extends People {
	private TheSystem theSystem;

	public Manager(String username, String email, String password, TheSystem theSystem)throws InvalidEmailException, InvalidUserNameException {
		super(username, email, password);
		this.theSystem = theSystem;

	}

	public Boolean createLab(String bulstat, String name) {
		if (this.getCurrentLab() == null) {
			if (!this.theSystem.doesThisLabExist(bulstat)) {
				try {
					this.setCurrentLab(new DentalLaboratory(bulstat, name, null, this));
					this.theSystem.addNewLab(getCurrentLab());
					return true;
				} catch (InvalidUserNameException e) {
					System.err.println(e.getMessage());
					return false;
				}
			}
			System.err.println("This Lab exist!!!");
			return false;
		} else {
			System.err.println("You already have a Lab.");
			return false;
		}

	}

	public Boolean addNewDentist(String username, String email) {
		try {
			Dentist tempDentist = new Dentist(username, email, email);

			if (this.theSystem.addUser(tempDentist)) {
				this.getCurrentLab().setNewDentist(tempDentist);
				return true;
			} else {
				throw new ThisUserExist(username);
			}
		} catch (InvalidEmailException e) {
			System.out.println(e.getMessage());
		} catch (InvalidUserNameException e) {
			System.out.println(e.getMessage());
		} catch (ThisUserExist e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public Boolean addNewService(String longName, String shortName, Double price, String serialNumber) {
		Service service = new Service(longName, shortName, price, serialNumber);
		if (this.getCurrentLab().getService(serialNumber) == null) {
			this.getCurrentLab().addServiceToList(service);
			return true;
		} else {
			return false;
		}
	}

	public Boolean changeService(String longName, String shortName, Double price, String serialNumber) {
		Service service = new Service(longName, shortName, price, serialNumber);
		if (this.getCurrentLab().getService(serialNumber) != null) {
			return this.getCurrentLab().changeService(service);
			
		} else {
			return false;
		}
	}
}
