package behaviortests;

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.BoardMain;
import pages.ParchMain;

public class RoomActionsStepImplementations {
	static {
		
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
	}
	
	static ChromeDriver driver = new ChromeDriver();
	static ParchMain parchmain = new ParchMain(driver);
	static BoardMain boardmain = new BoardMain(driver);
	
	@Given("^: The User logs in as \"([^\"]*)\", \"([^\"]*)\"$")
	public void the_User_logs_in_as(String arg1, String arg2) throws Throwable {
		driver.get("http://ec2-18-207-247-77.compute-1.amazonaws.com:4200/");
		parchmain.getUsernameInput().sendKeys(arg1);
		parchmain.getPasswordInput().sendKeys(arg2);
		parchmain.getLoginBut().click();
	}

	@When("^: The user creates a room called \"([^\"]*)\"$")
	public void the_user_creates_a_room_called(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    WebElement createBoardButton = boardmain.getCreateBoardButton();
	    createBoardButton.click();
	    WebElement addBoardButton = boardmain.getAddNewBoardButton();
	    WebElement newBoardNameTextbox = boardmain.getNewBoardNameTextbox();
	    System.out.println(newBoardNameTextbox.getAttribute("class"));
	    newBoardNameTextbox.sendKeys(arg1);
	    addBoardButton.click();
	}

	@Then("^: \"([^\"]*)\" is \"([^\"]*)\" created$")
	public void is_created(String arg1, String arg2) throws Throwable {
	    List<WebElement> roomSelectors = boardmain.getRoomList();
	    Assert.assertTrue(roomSelectors.size()>0);
	    for (WebElement webElement : roomSelectors) {
			System.out.println(webElement.getText() + "------------\n");
		}
	}

	@Given("^: The user selects the room \"([^\"]*)\"$")
	public void the_user_selects_the_room(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^: The user invites user \"([^\"]*)\"$")
	public void the_user_invites_user(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^: \"([^\"]*)\" is \"([^\"]*)\" invited to \"([^\"]*)\"$")
	public void is_invited_to(String arg1, String arg2, String arg3) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^: \"([^\"]*)\" is \"([^\"]*)\" displayed$")
	public void is_displayed(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^: The user types \"([^\"]*)\"Hi\"([^\"]*)\" in room$")
	public void the_user_types_Hi_in_room(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^: The user \"([^\"]*)\"$")
	public void the_user(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^: The message is \"([^\"]*)\" displayed$")
	public void the_message_is_displayed(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^: The user attempts to delete \"([^\"]*)\"$")
	public void the_user_attempts_to_delete(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^: The message \"([^\"]*)\" is \"([^\"]*)\" deleted$")
	public void the_message_is_deleted(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^: The user attempts to edit \"([^\"]*)\" with new text \"([^\"]*)\"Hi!\"([^\"]*)\"$")
	public void the_user_attempts_to_edit_with_new_text_Hi(String arg1, String arg2, String arg3) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^: The message \"([^\"]*)\" is \"([^\"]*)\" edited\\.$")
	public void the_message_is_edited(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^: The user attempts to ban \"([^\"]*)\" from the room$")
	public void the_user_attempts_to_ban_from_the_room(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^: The user \"([^\"]*)\" is \"([^\"]*)\" banned$")
	public void the_user_is_banned(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^: The room \"([^\"]*)\" is \"([^\"]*)\" deleted$")
	public void the_room_is_deleted(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
}
