package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BoardMain {
	
	public static WebDriver driver;
	
	public BoardMain(WebDriver _driver) {
		driver = _driver;
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}
	
	public List<WebElement>getRoomList() {
		return driver.findElements(By.className("boardSelectionButton"));
	}

	public WebElement getUserDisplay() {
		// TODO Auto-generated method stub
		
		
		try {
			WebElement userDisplayElem = driver.findElement(By.id("currentUsernameDisplay"));
			return userDisplayElem;
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return null;
		}
	}

	public WebElement getCreateBoardButton() {
		// TODO Auto-generated method stub

		try {
			WebElement userDisplayElem = new WebDriverWait(driver,5).until(
					ExpectedConditions.presenceOfElementLocated(By.id("addNewBoardButton")));
			return userDisplayElem;
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			//					e.printStackTrace();
			return null;
		} catch (TimeoutException te) {
			return null;
		}
	}
	
	public WebElement getAddNewBoardButton() {
		// TODO Auto-generated method stub

		try {
			WebElement userDisplayElem = driver.findElement(By.id("addNewBoardButton"));
			return userDisplayElem;
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			//					e.printStackTrace();
			return null;
		}
	}

	public WebElement getNewBoardNameTextbox() {
		// TODO Auto-generated method stub

		try {
			WebElement userDisplayElem = driver.findElement(By.xpath("//*[@id=\"createBoard\"]/div[1]/input"));
			
			return userDisplayElem;
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			//					e.printStackTrace();
			return null;
		}
	}
	
}