package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ParchMain {
	
	public WebDriver driver;
	
	public ParchMain(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getUsernameInput() {
		return driver.findElement(By.name("Username"));
	}
	
	public WebElement getPasswordInput() {
		return driver.findElement(By.name("Password"));
	}
	
	public WebElement getLoginBut() {
		return driver.findElement(By.id("log"));
	}
	
	public WebElement getRegisterBut() {
		return driver.findElement(By.id("reg"));
	}
	
	public WebElement getRegisterUser() {
		return driver.findElement(By.name("NewUsername"));
	}
	
	public WebElement getRegisterPass() {
		return driver.findElement(By.name("NewPassword"));
	}
	
	public WebElement getRegisterEmail() {
		return driver.findElement(By.name("Email"));
	}

}
