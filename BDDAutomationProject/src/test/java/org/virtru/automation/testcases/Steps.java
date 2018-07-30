package org.virtru.automation.testcases;

import java.util.ResourceBundle;

import org.openqa.selenium.WebDriver;
import org.virtru.automation.assertions.Compare;
import org.virtru.automation.base.DriverFactory;
import org.virtru.automation.pages.GmailInbox;
import org.virtru.automation.pages.LoginPage;

import cucumber.annotation.After;
import cucumber.annotation.Before;
import cucumber.annotation.en.*;


public class Steps {
	
	ResourceBundle config;
	WebDriver driver;
	LoginPage login;
	GmailInbox gmail;
	DriverFactory driverInstance;
	
	@Before
	public void before() {
		config = ResourceBundle.getBundle("config");
		driverInstance = new DriverFactory();
		driver = driverInstance.generateDriverInstance(config);
	}
	
	@After
	public void after() {
		driverInstance.closeDriver(driver);
	}

	@Given("^User is on login page$")
	public void User_is_on_login_page() throws Throwable {
		login = new LoginPage(driver);	
	}
	
	@When("^User enters valid \"([^\"]*)\" and \"([^\"]*)\"$")
	public void User_enters_valid_and(String username, String password) throws Throwable {
		login.loginToAccount(username, password);
	}

	@Then("^User logged in successfully$")
	public void User_logged_in_successfully() throws Throwable {
		Compare.validatePageURL(driver, config.getString("validURL"));
	}
	
	@When("^User open email with subject \"([^\"]*)\"$")
	public void User_open_email_with_subject(String subject) throws Throwable {
		gmail = new GmailInbox(driver);
		gmail.findDesiredEmail(subject);
	}
	
	@And("^User decrypts email$")
	public void User_decrypts_email() throws Throwable {
		gmail.clickUnlockMessage();
		gmail.decryptAndSwitchWindows();
	}

	@Then("^User confirms message is \"([^\"]*)\"$")
	public void User_confirms_message_is_correct(String expectedMessage) throws Throwable {
		gmail.clickVerifyInSecondEmail();
		Compare comp = new Compare(driver);
		comp.validateEmailContents(driver, expectedMessage);
	}
}