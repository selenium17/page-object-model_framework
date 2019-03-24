package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {
	
	Properties props;
	
	public PropertyFileReader() throws IOException {
		//1. Locate the file
		String fileName = "C:\\Learning\\page-object-model-data-driven-framework\\config\\configuration.properties";		
		
		//2. open the file
		FileInputStream fis1 = new FileInputStream(fileName);
		
		//3. read the data from file
		props = new Properties();
		props.load(fis1);
	}
		
	public String readProperty(String key) {
		String value = props.getProperty(key);
		return value;
	}

}
