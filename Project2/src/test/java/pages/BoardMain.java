package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BoardMain {
	
	public static WebDriver driver;
	
	public BoardMain(WebDriver _driver) {
		driver = _driver;
	}

	public WebElement getUserDisplay() {
		// TODO Auto-generated method stub
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		try {
			WebElement userDisplayElem = driver.findElement(By.id("currentUsernameDisplay"));
			return userDisplayElem;
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return null;
		}
	}
	
}