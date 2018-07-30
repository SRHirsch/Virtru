package org.virtru.automation.base;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {
	
	public WebDriver generateDriverInstance(ResourceBundle config) {
		WebDriver driver;
				
		String browser = config.getString("browser");
		
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "./Driver/internetexplorerdriver.exe");
			driver = new InternetExplorerDriver();
		}
		else {
			System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getString("implicitWait")), TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(config.getString("applicationURL"));
		return driver;		
	}
	
	public void closeDriver(WebDriver driver) {
		driver.quit();
		System.out.println("*******************DRIVER HAS BEEN QUIT*************************************");
	}
	
}
