package utils;

import static org.testng.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReportingUtil {
	
	WebDriver driver;
	String testName;
	
	ExtentReports report;
	ExtentTest logger;
	PropertyFileReader pfr;
	
	public ReportingUtil(WebDriver d, String testname) throws IOException {
		driver = d;
		testName = testname;
		
		pfr = new PropertyFileReader();
		//location of the HTML report file
		String reportLocation = pfr.readProperty("html.report.location");		
		report =  new ExtentReports(reportLocation); 
		
		//create ExtentTest object for logger object 
		logger = report.startTest(testName);
	}
	
	//method to capture the screenshot
	public String captureScreenshot() throws IOException {
		File sourcefile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destinationfile = new File(pfr.readProperty("screenshots.location") + testName + ".jpg");
		Files.copy(sourcefile, destinationfile);
		return destinationfile.getAbsolutePath();
	}
	
	public void report(String status, String step, String details) throws IOException {
		
		switch(status) {
			case "PASS":
				logger.log(LogStatus.PASS, step, details);
				break;
			case "FAIL":
				String screenshotpath = logger.addScreenCapture(captureScreenshot());
				logger.log(LogStatus.FAIL, step, details + screenshotpath);
				fail(testName + " test is failed");
				break;
		}		
		
	}
	
	public void generateReport() {
		report.endTest(logger);
		report.flush();
		report.close();
	}

}