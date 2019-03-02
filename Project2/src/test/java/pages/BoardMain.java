package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BoardMain {
	
	public static WebDriver driver;
	
	public BoardMain(WebDriver _driver) {
		driver = _driver;
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}
	
	private void waitUntilBoardListUpdates(int timeout, int initialcount) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {
				List<WebElement> boardSelectors = driver.findElements(By.className("boardSelectionButton"));
				if (boardSelectors.size()>initialcount) {
					return true;
				} else {
					return false;
				}
			}
			
		});
	}
	
	public List<WebElement> getRoomList(boolean doWait) {
		List<WebElement> boardSelectors = driver.findElements(By.className("boardSelectionButton"));
		try {
			
			if (doWait) {
				try {
					waitUntilBoardListUpdates(5, boardSelectors.size());
					boardSelectors = driver.findElements(By.className("boardSelectionButton"));
				} catch (TimeoutException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			return boardSelectors;
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return null;
		}
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
					ExpectedConditions.presenceOfElementLocated(By.id("createBoardButton")));
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
			WebElement userDisplayElem = new WebDriverWait(driver,5).until(
					ExpectedConditions.presenceOfElementLocated(By.id("addNewBoardButton")));
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

	public WebElement getPermissionsDiv() {
		// TODO Auto-generated method stub
		return null;
	}
	
}