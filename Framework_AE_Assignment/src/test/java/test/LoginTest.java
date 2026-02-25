package test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;
import framework.reporting.ReportManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

public class LoginTest extends BaseTest {

	// Case:2 - Login user with correct details

	@Test(description = "AE01_TC02_Verify whether user be able to Login using correct details")
	@Parameters({"loginEmail","loginPassword","userName"})
	@Epic("Login")
	@Feature("User Login")
	@Story("Login with correct user credentials")
	public void Test_AE01_TC02_Verify_Login_CorrectDetails(String loginEmail, String loginPassword, String userName) {

		logger.info("=======================================================");
		logger.info("Start, Case:2 - Login with correct email and password");
		logger.info("=======================================================");

		// Step:1 - Navigate to Login page and check 'Login into your acccount' is
		// visible
		logger.info("Running, Step:1 - Navigate to Login page and check 'Login into your acccount' is visible");
		ReportManager.logStep("Naviagting to Login page");
		homepage.clickSignupLoginLink();
		homepage.verifyTextMessageDisplayed("Login to your account", false);

		// Step:2 - Entering user credentials
		logger.info("Running, Step:2 - Entering user credentials");
		ReportManager.logStep("Entering user credentials");
		slpage.enterLoginDetails(loginEmail, loginPassword);
		slpage.clickLogin();
		ReportManager.logStep("Verifying whether logged in as username is visible");
		homepage.verifyLoggedIn(userName);

		// Step:3 - Delete Account
		logger.info("Running, Step:3 - Delete Account");
		ReportManager.logStep("Verifying whether account is deleted");
		homepage.DeleteAccountLink();
		homepage.verifyTextMessageDisplayed("Account Deleted", false);

		logger.info("==========End Case: Login user with correct details===========");
	}

	// Case:3 - Login user with incorrect details

	@Test(description = "AE01_TC03_Verify whether user getting error message with incorrect login details")
	@Epic("Login")
	@Feature("User Login")
	@Story("Login with incorrect user credentials")
	public void Test_AE01_TC03_Verify_Login_Incorrect_MailPwd() {

		logger.info("=====================================================================");
		logger.info("Start, Case:3 - Entering Incorrect Login Details(Mail and Password)");
		logger.info("=====================================================================");

		// Step:1 - Navigate to Login page and check 'Login into your acccount' is visible
		logger.info("Running, Step:1 - Navigate to Login page and check 'Login into your acccount' is visible");
		ReportManager.logStep("Naviagting to Login page");
		homepage.clickSignupLoginLink();
		homepage.verifyTextMessageDisplayed("Login to your account", false);

		// Step:2 - Entering user credentials
		logger.info("Running, Step:2 - Entering user credentials");
		ReportManager.logStep("Entering user credentials");
		slpage.enterLoginDetails("AEtest@gmail.com", "AEtestCF");
		slpage.clickLogin();

		// Step:3 - Verify user is getting error message
		logger.info("Running, Step:3 - Verify user is getting error message");
		ReportManager.logStep("Verifying whether user is getting error message with incorrect details");
		slpage.verifyTextMessageDisplayed("Your email or password is incorrect!", true);
		ReportManager.attachScreenshot("Your email or password is incorrect!", captureScreenshot());

		logger.info("==============End Case: Login user with incorrect details==============");
	}
}