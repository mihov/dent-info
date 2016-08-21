package inTheLab;

import exceptions.InvalidEmailException;
import exceptions.InvalidUserNameException;
import mainObjects.Address;
import mainObjects.People;
import mainObjects.TheSystem;

public class Manager extends People {
	private TheSystem theSystem;

	public Manager(String username, String email, String password, TheSystem theSystem)
			throws InvalidEmailException, InvalidUserNameException {
		super(username, email, password);
		this.theSystem = theSystem;

	}

	public Boolean createLab(String bulstat, String name, Address address) {
		if (!this.theSystem.isThisLabExist(bulstat)) {
			try {
				this.setCurrentLab(new DentalLaboratory(bulstat, name, address, this));
				this.theSystem.addNewLab(getCurrentLab());
				return true;
			} catch (InvalidUserNameException e) {
				System.err.println(e.getMessage());
				return false;
			}
		}
		System.err.println("This Lab exist!!!");
		return false;
	}

	
	
}
