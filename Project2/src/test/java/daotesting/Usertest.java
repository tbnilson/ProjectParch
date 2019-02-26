package daotesting;

import org.testng.annotations.Test;


import dao.UserDao;
import model.ParchUser;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

public class Usertest {
	
	static UserDao ud = new UserDao();
	
  @BeforeSuite
  public void setup() {
	  ParchUser q = new ParchUser();
	  q.setEmail("ben@revature.org");
	  q.setPassword("riot");
	  q.setUsername("ben");
  }
  //addUser testing and setup
  @Test(priority=2, dataProvider="pu")
  public void addUserTest(ParchUser s) {
	Assert.assertEquals(ud.addUser(s),true); 
  }
  //fail test for addUser, 1st test
  
  @Test(priority=3, dataProvider="pu")
  public void addUserFail(ParchUser s) {
	Assert.assertNotEquals(ud.addUser(s),true); 
  }
  @Test(dataProvider="pu",priority=1)
  public void getUserFail(ParchUser s) {
	  Assert.assertNull(ud.getUser(s.getUsername()),null);
  }
  @Test(dataProvider="pu", priority=4)
  public void getUserTest(ParchUser s) {
	  ParchUser d = new ParchUser();
	  
	  Assert.assertEquals(ud.getUser(s.getUsername()).getUsername(),s.getUsername()); 
  }
  //
  @Test(dataProvider="pu",priority=4)
  public void verifyTest(ParchUser s) {
	  Assert.assertEquals(ud.verifyUser(s.getUsername(), s.getPassword()),true); 
  }
  @Test(dataProvider="pu", priority=4)
  public void verifyFail(ParchUser s) {
	  Assert.assertNotEquals(ud.verifyUser(s.getUsername(),s.getPassword()+"1"),true); 
  }
  //set email success and fail
  @Test(dataProvider="pu", priority=4)
  public void setEmailTest(ParchUser s) {
	  Assert.assertEquals(ud.setEmail(s.getUsername(), s.getEmail()+"1"), true);
  }
  @Test(dataProvider="pu", priority=4)
  public void setEmailFail(ParchUser s) {
	  Assert.assertNotEquals(ud.setEmail(s.getUsername(), ""), true);
  }
//set username success and fail
  @Test(dataProvider="pu", priority=4)
  public void setUsernameTest(ParchUser s) {
	  Assert.assertEquals(ud.setUsername(s.getUsername(), s.getUsername()+"1"), s.getUsername()+"1");
  }
  @Test(dataProvider="pu", priority=4)
  public void setUsernameFail(ParchUser s) {
	  Assert.assertNotEquals(ud.setUsername(s.getUsername(),"ben" ),"ben");
  }
  @Test(dataProvider="pu", priority=4)
  public void UserExistsTest(ParchUser s) {
	  Assert.assertNotNull(ud.userExists(s.getUsername()));
  }
  @Test(dataProvider="pu", priority=12)
  public void userExistsFail(ParchUser s) {
	  Assert.assertEquals(ud.userExists(s.getUsername()),false);
  }
//delete user success and fail
  @Test(dataProvider="pu", priority=9)
  public void deleteUserTest(ParchUser s) {
	  Assert.assertEquals(ud.deleteUser(s.getUsername()), true);
  }
  @Test(dataProvider="pu", priority=10)
  public void deleteUserFail(ParchUser s) {
	  Assert.assertNotEquals(ud.deleteUser(s.getUsername()), true);
  }
  
  
  @DataProvider(name="pu")
	public static Object [][] users(){
	  
		ParchUser p = new ParchUser();
		ParchUser q = new ParchUser();
		ParchUser r = new ParchUser();
		p.setEmail("me@meme.com");
		  p.setPassword("elmo");
		  p.setUsername("flaming");
		  q.setEmail("admin@super.com");
		  q.setPassword("world");
		  q.setUsername("hello1");
		  r.setEmail("ken@revature.org");
		  r.setPassword("riot");
		  r.setUsername("ken");
		return new Object[][] {{p},{q},{r}};
	}
  
  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }
  @AfterSuite
  public void cleanup() {
	 ud.deleteUser("ben");
	 ud.deleteUser("hello1");
	 ud.deleteUser("ken");
	 ud.deleteUser("flaming");
  }

}
