package org.virtru.automation.pages;
import java.util.ResourceBundle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	ResourceBundle config;
	WebDriver driver;
	
	@FindBy(id = "identifierId")
	private WebElement userNameTextBox;
	
	@FindBy(id = "identifierNext")
	private WebElement userNameNextButton;
	
	@FindBy(xpath = "//input[@name='password']")
	private WebElement passwordTextBox;
	
	@FindBy(id = "passwordNext")
	private WebElement passwordNextButton;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		config = ResourceBundle.getBundle("config");
	}
	
	public void loginToAccount(String uname, String pword) {
		enterUsername(uname);
		enterPassword(pword);
	}
		
	public void enterUsername(String uname) {
		userNameTextBox.sendKeys(uname);
		System.out.println("*******************USERNAME ENTERED******************************");
		
		userNameNextButton.click();
		System.out.println("*******************CLICKED USERNAME NEXT******************************");
		
	}
	
	public void enterPassword(String pword) {	
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getString("wait")));
		wait.until(ExpectedConditions.elementToBeClickable(passwordTextBox));
		System.out.println("*******************WAITING FOR PASSWORD VISIBLE******************************");
		
		passwordTextBox.sendKeys(pword);	
		System.out.println("*******************PASSWORD ENTERED******************************");
		
		passwordNextButton.click();
		System.out.println("*******************CLICKED PASSWORD NEXT******************************");
	}
}
