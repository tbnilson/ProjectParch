package daotesting;

import org.testng.annotations.Test;

import app.Playground;
import dao.PermissionDao;
import dao.PostDao;
import dao.RoomDao;
import dao.UserDao;
import model.ParchUser;
import model.Room;
import util.HibernateUtil;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class Posttest {
	
	public static UserDao ud = new UserDao();
	public static RoomDao rd = new RoomDao();
	public static PermissionDao pd = new PermissionDao();
	public static PostDao pod = new PostDao();
	public static Room posttestroom;
	public static ParchUser posttestuser;
	
	@BeforeClass
	public void startup() {
		Playground.populateTestDB();
		Playground.postMessages();
		posttestroom = rd.makeRoom("Perm Testing Room", "test1");
		posttestuser = ud.getUser("test1");
	}
	
	@AfterClass
	public void teardown() {
		HibernateUtil.getSessionFactory().close();
	}
	
	@DataProvider(name="messages",parallel=false)
	public static Object[][] messages(){
		//THe convention is to return a 2d array of objects, mostly for flexibility.
		int length = 4004;
		StringBuffer outputBuffer = new StringBuffer(length);
		for (int i = 0; i < length; i++){
		   outputBuffer.append(" ");
		}
		
		return new Object[][] {
			{"Sarah Silverman message test"}, 		//Test 1
			{"George Carlin message test"}, 		//Test 2
			{"Brian Regan message test"},			//Test 3
			{""},			//Test 4
			{"Long string:"}
		};
	}
	
	@Test(priority=1, dataProvider="messages")
	public void postToValidRoomAndUserTest(String messagetxt) {
		Assert.assertNotNull(pod.makePost(posttestroom, posttestuser, messagetxt));
	}
	
	@Test(priority=2, dataProvider="messages")
	public void postToValidRoomAndNullUserTest(String messagetxt) {
		Assert.assertNull(pod.makePost(posttestroom, null, messagetxt));
	}
	
	@Test(priority=3)
	public void checkMessagesValidUsernameTest() {
		Assert.assertEquals(pod.getUserPosts("test1").size(), 5);
	}
	
	@Test(priority=3)
	public void checkMessagesInvalidUsernameTest() {
		Assert.assertNull(pod.getUserPosts("BadPostUsername"));
	}
	
}