package Assessment.UI.config;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {
	private static final Properties properties = new Properties();
	
	static {
		try 
		{
			FileInputStream file = new FileInputStream("src/main/resources/config.properties");
			properties.load(file);
			file.close();
		} 
		catch (Exception ex) 
		{
			throw new RuntimeException("Failed to load configuration file", ex);
		}
	}
	
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}