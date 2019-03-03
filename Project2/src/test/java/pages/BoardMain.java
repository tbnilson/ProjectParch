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

import util.MyLogger;

public class BoardMain {
	
	public static WebDriver driver;
	
	public BoardMain(WebDriver _driver) {
		driver = _driver;
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}
	
	private void waitUntilClassListUpdates(int timeout, int initialcount, String classname) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {
				List<WebElement> boardSelectors = driver.findElements(By.className(classname));
				if (boardSelectors.size()>initialcount) {
					return true;
				} else {
					return false;
				}
			}
			
		});
	}
	
	public List<WebElement> getRoomList(boolean doWait) {
		String className = "boardSelectionButton";
		List<WebElement> boardSelectors = driver.findElements(By.className(className));
		try {
			
			if (doWait) {
				try {
					waitUntilClassListUpdates(5, boardSelectors.size(),className);
					boardSelectors = driver.findElements(By.className(className));
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

	public WebElement getPermissionsShow() {
		// TODO Auto-generated method stub
		return driver.findElement(By.id("permissionsShow"));
	}

	public WebElement getInviteUserSelector() {
		// TODO Auto-generated method stub
		return driver.findElement(By.id("inviteUserSelector"));
	}

	public WebElement getPermissionsSelector() {
		// TODO Auto-generated method stub
		return driver.findElement(By.id("permSelector"));
	}

	public WebElement getPermissionsButton() {
		// TODO Auto-generated method stub
		return driver.findElement(By.id("permissionButton"));
	}

	public WebElement getBoardNameDisplay() {
		// TODO Auto-generated method stub
		return driver.findElement(By.id("boardNameDisplay"));
	}
	
	public WebElement getMessageSpan(int postID, boolean doWait) {
		String className = "Post";
		int startnum = driver.findElements(By.className(className)).size();

		if (doWait) {
			try {
				waitUntilClassListUpdates(5, startnum,className);
			} catch (TimeoutException e) {

				MyLogger.logger.trace("Error in BoardMain", e);
			} 
		}

		WebElement postactiondiv = driver.findElement(By.id(Integer.toString(postID)));
		return postactiondiv.findElement(By.xpath("..")).findElement(By.tagName("span"));
	}
	
	public WebElement getMessageTextBox() {
		return driver.findElement(By.id("postText"));
	}

	public WebElement getCreatePostButton() {
		// TODO Auto-generated method stub
		return driver.findElement(By.id("newPostButton"));
	}
	
	public List<WebElement> getPostList(boolean doWait) {
		String className = "Post";
		List<WebElement> postlist = driver.findElements(By.className(className).className("AdminOnly"));
		int startnum = postlist.size();
		try {

			if (doWait) {
				try {
					waitUntilClassListUpdates(5, startnum,className);
					postlist = driver.findElements(By.className(className).className("AdminOnly"));
				} catch (TimeoutException e) {

					MyLogger.logger.trace("Error in BoardMain", e);
				} 
			}

		} catch (NoSuchElementException e) {
			MyLogger.logger.trace("Error in BoardMain", e);
			return null;
		}
		return postlist;
	}
	
}