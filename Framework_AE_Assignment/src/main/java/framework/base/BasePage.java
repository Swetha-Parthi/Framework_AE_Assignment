package framework.base;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.regex.Pattern;

import org.slf4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import framework.logging.LogManager;

public abstract class BasePage {

	protected Page page;
	protected Logger logger;
	protected Locator ContinueButton;

	// Parameterized constructor
	public BasePage(Page page) {
		this.page = page;
		this.logger = LogManager.getLogger(this.getClass());
		this.ContinueButton = page.getByText(Pattern.compile("Continue", Pattern.CASE_INSENSITIVE));		
	}

	// Check the title and URL
	public void verifyPageLoaded(String expURL, String expTitle) {
		assertThat(page).hasURL(Pattern.compile(expURL, Pattern.CASE_INSENSITIVE));
		assertThat(page).hasTitle(Pattern.compile("Automation Exercise", Pattern.CASE_INSENSITIVE)); // check
	}

	// CheckBox
	public void setCheckBox(Locator checkbox, boolean shouldBeChecked, String checkboxName) {
		boolean isChecked = checkbox.isChecked();
		if (isChecked != shouldBeChecked) {
			checkbox.click();
		}
		// final verification to ensure the checkbox is in expected state
		if (shouldBeChecked) {
			assertThat(checkbox).isChecked();
		} else {
			assertThat(checkbox).not().isChecked();
		}
		logger.info("Checkbox: " + checkboxName + " is set to: " + shouldBeChecked);
	}

	public void verifyTextMessageDisplayed(String expMsg, Boolean exactMatch) {
		if (exactMatch) {
			assertThat(page.getByText(expMsg)).isVisible();
		} else {
			assertThat(page.locator("body")).hasText(Pattern.compile(expMsg, Pattern.CASE_INSENSITIVE));
		}
	}
	
	public void clickContinueButton() {
		ContinueButton.click();
	}
}
