package pages.ae;

import java.util.regex.Pattern;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import framework.base.BasePage;

public class HomePage extends BasePage {

	// private Page page;
	private final Locator SignupLoginLink;
	private final Locator DeleteAccountLink;
	private final Locator LogoutLink;
	
	public HomePage(Page page) {
		super(page);
		this.page = page;	
		this.SignupLoginLink = page.getByText(Pattern.compile("Signup", Pattern.CASE_INSENSITIVE));
		this.DeleteAccountLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Delete Account"));
		this.LogoutLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(Pattern.compile("Logout")));
	}
	
	// To check whether correct user is logged in 
	
	public void verifyLoggedIn(String userName) {
		Locator Loggedin = page.getByText(Pattern.compile("Logged in as.*" +userName));
		assertThat(Loggedin).isVisible();
	}

	// To click on Signup/ Login link
	
	public void clickSignupLoginLink() 
	{
		SignupLoginLink.click();
	}

	// To click on Delete Account link
	
	public void DeleteAccountLink() {
		DeleteAccountLink.click();
	}	
	
	// To click on Logout link
	
	public void LogoutLink() {
		LogoutLink.click();
	}	
	
}

