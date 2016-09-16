package model.inTheLab;

public class MasterManager {
	
	private static int user_id = 6;
	
	public static int getNextUserId(){
		return user_id++;
	}
	
}
