package behaviortests;

import org.testng.annotations.AfterSuite;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		
		features = {"src/test/resources/Login.feature"},
		glue = {"behaviortests"}

)

public class CucumberRunner extends AbstractTestNGCucumberTests {
	
	@AfterSuite
	public void after() {
		LoginStepImplementation.driver.quit();
	}

}
