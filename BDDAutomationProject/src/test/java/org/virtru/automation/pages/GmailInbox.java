package org.virtru.automation.pages;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailInbox {
	
	ResourceBundle config;
	WebDriver driver;
	
	@FindBy(id = "tdf-body")
	private WebElement email_body;
	
	@FindBy(xpath = "//*[@class='bqe']")
	private List<WebElement> email_subject;
	
	@FindBy(partialLinkText = "Unlock Message")
	private WebElement Unlock_Message;
	
	@FindBy(xpath = "//a[@class='btn btn-lg auth-choice-btn sendEmailButton']")
	private WebElement Send_me_an_Email_Link;
	
	@FindBy(partialLinkText = "Inbox")
	private WebElement inbox;
	
	@FindBy(partialLinkText = "Inbox (")
	private WebElement inboxNewEmail;
	
	@FindBy(partialLinkText = "VERIFY ME")
	private WebElement verifyMe;
	
	@FindBy(className = "userEmail")
	private WebElement usernameEmail;
	
	public GmailInbox(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		config = ResourceBundle.getBundle("config");
	}
	
	public void findDesiredEmail(String subject) {	
		List<WebElement> unreademail = email_subject;
		
		boolean found = false;
		int counter = 0;
		while(!found && counter < 300) {
			for(int i=0;i<unreademail.size();i++){
				if(unreademail.get(i).isDisplayed()){
					if(unreademail.get(i).getText().contains(subject)){
						System.out.println("************************GOT EMAIL FROM " + subject + "************************");
						unreademail.get(i).click();
						found = true;
						break;
					}else{
						System.out.println("************************NO EMAIL FROM " + subject + " YET************************");
					}
				}
			}
			unreademail = email_subject;
			counter++;
		}

	}
	
	public void clickUnlockMessage() {
		Unlock_Message.click();
		System.out.println("**************************DECRYPTED****************************************");
	}
	
	
	public void decryptAndSwitchWindows() {

		System.out.println("**************************In SWITCH WINDOWS****************************************");
		
		 String originalWindow= driver.getWindowHandle();
		 Set<String> allWindows = driver.getWindowHandles();
		 
		 for(String child:allWindows) {
			 if(!child.equalsIgnoreCase(originalWindow)) {
				 driver.switchTo().window(child);
			 }
		 }
		 
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getString("wait")));
		wait.until(ExpectedConditions.elementToBeClickable(usernameEmail));
		usernameEmail.click();
		System.out.println("**************************CLICKED ON EMAIL LINK****************************************");

		int count = 0;
		while(!Send_me_an_Email_Link.isDisplayed() && count<500) {
			System.out.println("It is not displayed!");
			count++;
		}

		Send_me_an_Email_Link.click();
		System.out.println("**************************CLICKED SEND EMAIL************************************");
		
		driver.close();
		driver.switchTo().window(originalWindow);
		
		wait.until(ExpectedConditions.elementToBeClickable(inbox));
		inbox.click();
		wait.until(ExpectedConditions.elementToBeClickable(inboxNewEmail));
		inboxNewEmail.click();
		System.out.println("**************************CLICKED ON INBOX************************************");

	}
	
	public void clickVerifyInSecondEmail() {
		verifyMe.click();
		System.out.println("**************************VERIFIED**************************************");		 
	}
}
