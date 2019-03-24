package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	
	public WebDriver getBrowser(String browser) throws Exception {
		
		WebDriver driver;
		
		PropertyFileReader pfr = new PropertyFileReader();
		
		switch(browser.toLowerCase()) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver", pfr.readProperty("chrome.driver.path"));
				driver = new ChromeDriver();
				break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver", pfr.readProperty("gecko.driver.path"));
				driver = new FirefoxDriver();
				break;				
			default:
				throw new Exception("Unknown browser - " + browser);
		}
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		return driver;
	}

}
