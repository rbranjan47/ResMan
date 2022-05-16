package resman.qa.TestCases;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Logs {
	// Not usable in another class or Listeners
	private static Logger log = LogManager.getLogger(Logs.class.getName());

	// info method
	public static void infoMethod(String message) {
		log.info(message);
	}

	// debug method
	public static void debugMethod(String message) {
		log.debug(message);
	}

	// fatal method
	public static void fatalMethod(String message) {
		log.fatal(message);
	}
}
