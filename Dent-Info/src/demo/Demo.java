package demo;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import mainObjects.Patient;

public class Demo {

	public static void main(String[] args) {

		Logger logger = Logger.getLogger("MyLog");
		FileHandler fh = null;

		// This block configure the logger with handler and formatter
		try {
			fh = new FileHandler("LogFile.log");
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.addHandler(fh);
		SimpleFormatter formatter = new SimpleFormatter();
		fh.setFormatter(formatter);

	

		Patient p1 = new Patient("UserName", "qw e@abvbg", "Password");
		System.out.println(p1.getEmail());
		logger.warning("Warming msg");
		logger.getClass();

	}
}