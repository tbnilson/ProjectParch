package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BoardMain {
	
	public static WebDriver driver;
	
	public BoardMain(WebDriver _driver) {
		driver = _driver;
	}

	public WebElement getUserDisplay() {
		// TODO Auto-generated method stub
		System.out.println("BoardMain title: " + driver.getTitle());
		return driver.findElement(By.id("currentUsernameDisplay"));
	}
	
}