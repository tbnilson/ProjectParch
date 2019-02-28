package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BoardMain {
	
	public static WebDriver driver;
	
	public BoardMain(WebDriver _driver) {
		driver = _driver;
	}

	public static WebElement getUserDisplay() {
		// TODO Auto-generated method stub
		return driver.findElement(By.id(id))
	}
	
}