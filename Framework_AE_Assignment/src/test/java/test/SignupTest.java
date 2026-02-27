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

public class SignupTest extends BaseTest {

	// Case:1 - Register New User

	@Test(description = "AE01_TC01_Verify whether new user is able to register")
	@Epic("Signup")
	@Feature("User Registration")
	@Story("Signup using valid creadentials")
	public void Test_AE01_TC01_Verify_NewUser_SignUp() {

		logger.info("=============================================================");
		logger.info("Start, Case:1 - Verify whether new user is able to register");
		logger.info("=============================================================");

		HomePage homepage = new HomePage(page);
		SignupLoginPage slpage = new SignupLoginPage(page);
		SignupDetailPage sdpage = new SignupDetailPage(page);
		
		// Step:1 - Navigate to SignUp page
		logger.info("Running, Step:1 - Navigate to SignUp page");
		ReportManager.logStep("Naviagting to Signup page");
		homepage.clickSignupLoginLink();
		slpage.verifyPageLoaded("/login", "Signup");
		slpage.verifyPageHeader("signup", "New User Signup!");

		// Step:2 - Enter SignUp details
		logger.info("Running, Step: 2 - Entering Signup credentials");
		ReportManager.logStep("Entering signup credentials on successful navigation");
		// constants
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
		ReportManager.attachScreenshot("Account address info Entered", captureScreenshot());
		sdpage.clickCreateAcc();

		// Step: 4 - verify Account created
		logger.info("Running, Step: 4 - Verify account is created");
		ReportManager.logStep("Verifying whether account is created");
		sdpage.verifyPageLoaded("/account_created", "Account Created");
		sdpage.verifyTextMessageDisplayed("Account Created!", false);
		sdpage.clickContinueButton();

		// Step:5 - verify Logged in as username is visible and able to logout
		logger.info("Running, Step: 5 - Logged in as username is visible and able to logout");
		ReportManager.logStep("Verifying whether logged in as username is visible");
		homepage.verifyLoggedIn(userName);

		// Step:6 - Delete Account
		logger.info("Running, Step: 6 - Delete Account");
		ReportManager.logStep("Verifying whether account is deleted");
		homepage.DeleteAccountLink();
		homepage.verifyPageLoaded("/delete_account", "Account Created");
		homepage.verifyTextMessageDisplayed("Account Deleted!", true);
		homepage.clickContinueButton();

		logger.info("=====================End Case: User Registration=========================");
	}

	// Case:5 - Register User with existing mail

	@Test(description = "AE01_TC05_Verify whether user getting error message using registered mail while Signup")
	@Epic("Signup")
	@Feature("User Registration")
	@Story("Signup with existing mail")
	public void Test_AE01_TC05_Signup_ExistingMail() {

		logger.info("==================================================================================");
		logger.info("Start, Case:5 - verify signup using already registered mail display error message");
		logger.info("==================================================================================");

		HomePage homepage = new HomePage(page);
		SignupLoginPage slpage = new SignupLoginPage(page);
		
		// Step:1 - Navigate to SignUp page
		logger.info("Running, Step:1 - Navigate to SignUp page");
		ReportManager.logStep("Naviagting to Signup page");
		homepage.clickSignupLoginLink();
		slpage.verifyPageLoaded("/login", "Signup");
		slpage.verifyTextMessageDisplayed("New User Signup!", true);

		// Step:2 - Enter new name and already registered email for SignUp
		logger.info("Running, Step:2 - Entering new name and already registered email for SignUp");
		ReportManager.logStep("Entering new name and already registered email for SignUp");
		slpage.enterSignupDetails("AEtestCF06", "CFTestUser1770978867421@gmail.com");
		slpage.clickSignup();

		// Step:3 - See error message
		logger.info("Running, Step:3 - Verify error message is getting displayed");
		ReportManager.logStep("Verify whether error message is getting displayed");
		slpage.verifyTextMessageDisplayed("Email Address already exist!", true);
		ReportManager.attachScreenshot("Email Address already exist!", captureScreenshot());

		logger.info("=====================End Case: Register User with existing mail====================");
	}
}
