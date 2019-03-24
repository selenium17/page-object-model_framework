package page.objects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ReportingUtil;

public class LandingPage {

	WebDriver driver;
	ReportingUtil report;
	
	//elements
	@FindBy(className="user-account-link")
	private WebElement MyAccountLinkInHeader;
	
	//constructor
	public LandingPage(WebDriver d, ReportingUtil ru) {
		driver=d;
		report = ru;
		PageFactory.initElements(driver, this);
	}

	//methods
	public void clickMyAccount() throws IOException {
		try {
			MyAccountLinkInHeader.click();
			report.report("PASS", "Click My Account", "My Account is clicked");
		}catch(Exception e) {
			report.report("FAIL", "Click My Account", "Failed to click on My Account");
		}		
	}
}
