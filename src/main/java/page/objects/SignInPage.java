package page.objects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ReportingUtil;

public class SignInPage {
	
	WebDriver driver;
	ReportingUtil report;
	
	//elements
	@FindBy(id="login-email")
	private WebElement EmailTextBox;
	
	@FindBy(id="login-password")
	private WebElement PasswordTextBox;
	
	@FindBy(xpath="//button[@class='btn btn-primary'][contains(text(),'Sign In')]")
	private WebElement SignInButton;
	
	//constructor
	public SignInPage(WebDriver d, ReportingUtil ru) {
		driver=d;
		report = ru;
		PageFactory.initElements(driver, this);
	}
	
	//methods
	public void login(String emailAddress, String password) throws IOException {
		try {
			EmailTextBox.sendKeys(emailAddress);
			report.report("PASS", "Enter email address - " + emailAddress, "Email address is entered");
		}catch(Exception e) {
			report.report("FAIL", "Enter email address - " + emailAddress, "Failed to enter email address");
		}
		
		try {
			PasswordTextBox.sendKeys(password);
			report.report("PASS", "Enter password - " + password, "Password is entered");
		}catch(Exception e) {
			report.report("FAIL", "Enter password - " + password, "Failed to enter password");
		}
		
		try {
			SignInButton.click();
			report.report("PASS", "Click sign in button", "Sign in button is clicked");
		}catch(Exception e) {
			report.report("FAIL", "Click sign in button", "Failed to click on Sign in button");
		}
	}

}
