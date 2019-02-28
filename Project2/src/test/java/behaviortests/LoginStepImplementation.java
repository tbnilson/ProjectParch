package behaviortests;

import java.io.File;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.BoardMain;
import pages.ParchMain;


public class LoginStepImplementation {
	
	static {
		
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
	}
	
	static ChromeDriver driver = new ChromeDriver();
	static ParchMain parchmain = new ParchMain(driver);
	static BoardMain boardmain = new BoardMain(driver);
	
	@Given("^: The User is on the Parch Home Page$")
	public void the_User_is_on_the_Parch_Home_Page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.get("http://ec2-18-207-247-77.compute-1.amazonaws.com:4200/");
	}

	@Given("^: The User types in \"([^\"]*)\" and \"([^\"]*)\" in login field$")
	public void the_User_types_in_and_in_login_field(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		parchmain.getUsernameInput().sendKeys(arg1);
		parchmain.getPasswordInput().sendKeys(arg2);
	}

	@When("^: The User \"([^\"]*)\"$")
	public void the_User(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		if(arg1.equals("clicks login")) {
			parchmain.getLoginBut().click();
		}
		if(arg1.equals("clicks register")) {
			parchmain.getRegisterBut().click();
		}
		if(arg1.equals("presses enter")) {
			parchmain.getPasswordInput().sendKeys(Keys.ENTER);
		}

	}
	
	@Then("^: The User \"([^\"]*)\" \"([^\"]*)\" logs in$")
	public void the_User_logs_in(String username, String successString) throws Throwable {
		boolean success=false;
		if (successString.equals("successfully")) {
			success=true;
		} else if (successString.equals("unsuccessfully")) {
			success=false;
		}
		WebElement usernamedisplay = boardmain.getUserDisplay();
		System.out.println("Login: " + username + " : " + successString);
		if (success==true) {
			Assert.assertEquals(usernamedisplay.getText(), username);
		} else {
			Assert.assertNull(usernamedisplay);
		}
	}

	@Given("^: The User types in \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" in register field$")
	public void the_User_types_in_and_and_in_register_field(String arg1, String arg2, String arg3) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		parchmain.getRegisterUser().sendKeys(arg1);
		parchmain.getRegisterPass().sendKeys(arg2);
		parchmain.getRegisterEmail().sendKeys(arg3);
		
	}

	@Then("^: The User \"([^\"]*)\" \"([^\"]*)\" registers$")
	public void the_User_registers(String username, String successString) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Register: " + username + " : " + successString);
	}

}
