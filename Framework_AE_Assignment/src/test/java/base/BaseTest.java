package base;

import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.microsoft.playwright.Page;

import framework.drivers.DriverManager;
import framework.logging.LogManager;
import pages.ae.HomePage;
import pages.ae.SignupDetailPage;
import pages.ae.SignupLoginPage;

public class BaseTest {

	protected Page page;
	protected Logger logger;
	protected HomePage homepage;
	protected SignupDetailPage sdpage;
	protected SignupLoginPage slpage;
	
	@BeforeMethod(description = "Browser and URL Launch")

	public void setup() {
		
		logger = LogManager.getLogger(this.getClass());
		
		DriverManager.initDriver();
		
		page = DriverManager.getPage();
		
		logger.info("Initial Setup is completed");
		
		homepage = new HomePage(page);
		sdpage = new SignupDetailPage(page);
		slpage = new SignupLoginPage(page);
	}

	@AfterMethod(description = "Browser and Playwright close")
	public void teardown() {
		
		DriverManager.quitDriver();
		
		logger.info("Driver Shutdown successful !!");
	}
	
	protected byte[] captureScreenshot() {
		return page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
	}
}
