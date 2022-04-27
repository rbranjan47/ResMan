package resman.qa.baseClass;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;

public class configure {
	public static String word;
	public static String value;
	public static String TEST_BROWSER=readData("qaConfig", "Browser");
	public static String APPLICATION_URL=readData("qaConfig","Application_url");
	
	public static String Username=readData("qaConfig", "Username");
	public static String Password=readData("qaConfig", "Password");		
	
	/*
	 *  [TestMethod]
	 *  [Description("To read the configuration file")] 
	 */

	public static String readData(String file,String key)
	{
		try 
		{			
			FileInputStream in=new FileInputStream(""+file+".txt");	 
			Properties properties=new Properties();			
			properties.load(in);
			in.close();
			
			FileReader reader=new FileReader(System.getProperty("user.dir")+"//resmanConfig.txt");
			properties.load(reader);
			value=properties.getProperty(key);
		}
		catch(Exception e)
		{
			System.out.println("Configuration file not readable");
		}
		return value;
	}
}
