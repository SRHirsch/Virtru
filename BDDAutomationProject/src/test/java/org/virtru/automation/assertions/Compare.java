package org.virtru.automation.assertions;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Compare {

	WebDriver driver;
	
	@FindBy(id = "tdf-body")
	private WebElement email_body;
	
	public Compare(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public static void validatePageURL(WebDriver driver, String expectedURL) {
		
		System.out.println("********************ACTUAL URL " + driver.getCurrentUrl() + "*************************");
		System.out.println("********************EXPECTED URL " + expectedURL + "*************************");
		assertTrue(driver.getCurrentUrl().equals(expectedURL));
	}
	
	
	public void validateEmailContents(WebDriver driver, String expectedMessage) {
		
		 String originalWindow= driver.getWindowHandle();
		 Set<String> allWindows = driver.getWindowHandles();
		 
		 for(String child:allWindows) {
			 if(!child.equalsIgnoreCase(originalWindow)) {
				 driver.switchTo().window(child);
			 }
		 }
		 
		int count = 0;
		while(!email_body.isDisplayed() && count<10) {
			System.out.println("It is not displayed!");
			count++;
		}
		
		System.out.println("*********************EXPECTED: " + expectedMessage + "*******************************************");
		System.out.println("*********************ACTUAL: "   + email_body.getText() + "*******************************************");
		assertTrue(email_body.getText().equals(expectedMessage));	
	}
}
