package test;

import java.util.UUID;

import org.testng.annotations.Test;

import base.BaseTest;
import framework.reporting.ReportManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import pages.ae.HomePage;
import pages.ae.SignupDetailPage;
import pages.ae.SignupLoginPage;

public class LoginTest extends BaseTest {

	// Case:2 - Login user with correct details

	@Test(description = "AE01_TC02_Verify whether user be able to Login using correct details")
	@Epic("Login")
	@Feature("User Login")
	@Story("Login with correct user credentials")
	public void Test_AE01_TC02_Verify_Login_CorrectDetails() {

		logger.info("=======================================================");
		logger.info("Start, Case:2 - Login with correct email and password");
		logger.info("=======================================================");

		HomePage homepage = new HomePage(page);
		SignupLoginPage slpage = new SignupLoginPage(page);
		SignupDetailPage sdpage = new SignupDetailPage(page);
		
		// Step:1 - Navigate to SignUp page
		logger.info("Running, Step:1 - Navigate to SignUp page");
		ReportManager.logStep("Naviagting to Signup page");
		homepage.clickSignupLoginLink();
		slpage.verifyPageLoaded("/login", "Signup");

		// Step:2 - Enter SignUp details
		logger.info("Running, Step: 2 - Entering Signup credentials");
		ReportManager.logStep("Entering signup credentials on successful navigation");
		String userName = "CFTestUser" + System.currentTimeMillis();
		String email = userName + "@gmail.com";
		String pwd = "CF@pwd" + UUID.randomUUID().toString().substring(0, 3);
		slpage.enterSignupDetails(userName, email);
		slpage.clickSignup();
		sdpage.verifyPageLoaded("/signup", "Signup");
		sdpage.verifyTextMessageDisplayed("Enter Account Information", false);
		sdpage.verifyTextMessageDisplayed("Address Information", true);
		sdpage.verifyAutoPopNameandEmail(userName, email);

		// Step:3 - Enter user account details
		logger.info("Running, Step: 3 - Enter user account details");
		ReportManager.logStep("Entering new user account details");
		sdpage.selectGender("male");
		sdpage.enterPassword(pwd);
		sdpage.selectDOB("10", "March", "1996");
		sdpage.optNewsLetter(true);
		sdpage.optSpecialOffer(false);
		ReportManager.attachScreenshot("Account Info Entered", captureScreenshot());
		sdpage.enterNames(userName, "CFTest");
		sdpage.enterCompanyDetails("CF");
		sdpage.enterAddressDetails("584 Main RD", "D Colony", "India", "TN", "CBE", "600001");
		sdpage.enterMobileNum("9517423000");
		sdpage.clickCreateAcc();

		// Step: 4 - verify Account created
		logger.info("Running, Step: 4 - Verify account is created");
		ReportManager.logStep("Verifying whether account is created");
		sdpage.verifyPageLoaded("/account_created", "Account Created");
		sdpage.verifyTextMessageDisplayed("Account Created!", false);
		sdpage.clickContinueButton();

		// Step: 5 - verify user is able to logout and navigate to login page
		logger.info("Running, Step: 5 - verify user is able to logout and navigate to login page");
		ReportManager.logStep("Verify whether user is able to logout and navigate to login page back");
		homepage.LogoutLink();
		slpage.verifyPageLoaded("/login", "Signup");

		// Step: 6 - verify Login to your account is visible and enter Email and
		// Password
		logger.info("Running, Step: 6 - verify Login to your account is visible and enter Email and Password");
		ReportManager.logStep("Verify whether Login to your account is visible and enter Email and Password");
		homepage.verifyTextMessageDisplayed("Login to your account", false);
		slpage.enterLoginDetails(email, pwd);
		slpage.clickLogin();

		// Step: 7 - verify Logged in as username is visible
		logger.info("Running, Step: 7 - Logged in as username is visible");
		ReportManager.logStep("Verifying whether logged in as username is visible");
		homepage.verifyLoggedIn(userName);

		// Step:3 - Delete Account
		logger.info("Running, Step:8 - Delete Account");
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

		HomePage homepage = new HomePage(page);
		SignupLoginPage slpage = new SignupLoginPage(page);
		
		// Step:1 - Navigate to Login page and check 'Login into your acccount' is
		// visible
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