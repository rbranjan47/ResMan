package resman.qa.baseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class configure {
	public static Properties properties;
	public static String word;
	public static String value;
	public static String filePath = System.getProperty("user.dir") + "//src//main//java//resman//qa//baseClass//resManconfig.properties";
	
	public static String TEST_BROWSER = readData(filePath, "Browser");
	public static String APPLICATION_URL = readData(filePath, "Application_url");
	public static String property = readData(filePath, "property");

	public static String Username = readData(filePath, "Username");
	public static String Password = readData(filePath, "Password");
	public static String user = readData(filePath, "user");

	/*
	 * [TestMethod] [Description("To read the configuration file")]
	 */

	public static String readData(String file, String key) {
		try {
			FileInputStream fileIn = new FileInputStream(file);
			properties = new Properties();
			properties.load(fileIn);
			fileIn.close();

			FileReader reader = new FileReader(file);
			properties.load(reader);
			value = properties.getProperty(key);
		} catch (FileNotFoundException e) {
			System.out.println("Config file not locatable");

		} catch (IOException e) {
			System.out.println("Config file not readable");
		}
		return value;
	}
}
