package tests;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import page.objects.AccountPage;
import page.objects.LandingPage;
import page.objects.SignInPage;
import utils.DriverFactory;
import utils.ExcelReaderUtil;
import utils.PropertyFileReader;
import utils.ReportingUtil;

public class SignInTest {
	
	WebDriver driver;
	ReportingUtil report;
	
	@BeforeClass
	@Parameters("browser")
	public void setup(String browserName) throws Exception {
		DriverFactory df = new DriverFactory();
		driver = df.getBrowser(browserName);
		
		//getting test name dynamically
		String testName = getClass().getSimpleName();
		report = new ReportingUtil(driver, testName);
	}
	
	@DataProvider
	public Object[][] getTestData() throws IOException{
		ExcelReaderUtil loginDataObj = new ExcelReaderUtil();
		Object[][] data = loginDataObj.readExcel();
		return data;
	}
	
	@Test(dataProvider="getTestData")
	public void verifySignInFunctionality(String emailAddress, String password) throws IOException {
		
		PropertyFileReader pfr = new PropertyFileReader(); 
		String appUrl = pfr.readProperty("app.url");
		driver.get(appUrl);
		
		LandingPage lp = new LandingPage(driver, report);
		lp.clickMyAccount();
		
		SignInPage sip = new SignInPage(driver, report);
		sip.login(emailAddress, password);
		
		AccountPage accountPage = new AccountPage(driver, report);
		accountPage.verifyMyAccountPageIsDisplayed();
		accountPage.clickSignOut();
		
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
		report.generateReport();
	}

}
