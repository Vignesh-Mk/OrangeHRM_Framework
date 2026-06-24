package configPackage;

import java.io.FileInputStream;
import java.util.Properties;

public class UniversalProperties {

	public static String GetProperty(String propertyIndex)
	{
		String value = "default";
		
		Properties props = new Properties();
		
		String userDir = System.getProperty("user.dir");
		
		String cfgDir = userDir + "/src/test/java/configPackage/config.properties";
		
		try
		{
			FileInputStream input = new FileInputStream(cfgDir);
			props.load(input);
			
			value = props.getProperty(propertyIndex);
			
		}
		catch(Exception e)
		{
			e.getMessage();
			e.getCause();
			e.printStackTrace();
		}
		
		return value;
	}

}
