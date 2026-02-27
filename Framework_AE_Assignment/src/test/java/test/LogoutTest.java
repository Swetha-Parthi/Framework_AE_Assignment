package test;

import org.testng.annotations.Test;

import base.BaseTest;
import framework.reporting.ReportManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import pages.ae.HomePage;
import pages.ae.SignupLoginPage;

public class LogoutTest extends BaseTest {
	// Case:4 - Check whether user is able to logout successfully

		@Test(description = "AE01_TC04_Verify whether user is able to logout")
		@Epic("Logout")
		@Feature("User Logout")
		@Story("Logout user and Navigate back to Login page")
		public void Test_AE01_TC04_Verify_LogoutUser() {
			
			logger.info("===============================================");
			logger.info("Start, Case:4 - Verify user is able to logout");
			logger.info("===============================================");
			
			HomePage homepage = new HomePage(page);
			SignupLoginPage slpage = new SignupLoginPage(page);
			
			// Step:1 - Navigate to Login page and check 'Login into your acccount' is visible		
			logger.info("Running, Step:1 - Navigate to Login page and check 'Login into your acccount' is visible");
			ReportManager.logStep("Naviagting to Login page");
			homepage.clickSignupLoginLink();
			homepage.verifyTextMessageDisplayed("Login to your account", false);

			// Step:2 - Entering user credentials
			logger.info("Running, Step:2 - Entering user credentials");
			ReportManager.logStep("Entering user credentials");
			slpage.enterLoginDetails("CFTestUser1770978867421@gmail.com", "CF@pwd0a0");
			slpage.clickLogin();
			
			// Step:3 - verify Logged in as username is visible
			logger.info("Running, Step: 3 - Logged in as username is visible");
			ReportManager.logStep("Verifying whether logged in as username is visible");
			homepage.verifyLoggedIn("CFTestUser1770978867421");
			
			// Step:4 - verify user is able to logout and navigate to login page
			logger.info("Running, Step: 4 - verify user is able to logout and navigate to login page");
			ReportManager.logStep("Verify whether user is able to logout and navigate to login page back");
			homepage.LogoutLink();
			slpage.verifyPageLoaded("/login", "Signup");
			
			logger.info("==================End case: Logout User================");
		}
}
