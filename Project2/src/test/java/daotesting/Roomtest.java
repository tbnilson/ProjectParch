package daotesting;

import org.testng.annotations.Test;

import app.Playground;
import dao.PermissionDao;
import dao.PostDao;
import dao.RoomDao;
import dao.UserDao;
import model.Room;
import util.HibernateUtil;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class Roomtest {
	
	public static UserDao ud = new UserDao();
	public static RoomDao rd = new RoomDao();
	public static PermissionDao pd = new PermissionDao();
	public static PostDao pod = new PostDao();
	
	@BeforeClass
	public void startup() {
		Playground.populateTestDB();
		Playground.postMessages();
		HibernateUtil.getSessionFactory();
	}
	
	@AfterClass
	public void teardown() {
		HibernateUtil.getSessionFactory().close();
	}
	
	@Test(priority=1)
	public void createRoomTest() {
		Room r = rd.makeRoom("TestRoom1", "test1");
		Assert.assertNotNull(r);
		Assert.assertEquals(r.getRoomname(), "TestRoom1");
	}
	@Test(priority=2)
	public void getRoomTestValidRoomID() {
		Assert.assertNotNull(rd.getRoom(2));
	}
	@Test(priority=3)
	public void getRoomTestInvalidRoomID() {
		Assert.assertNull(rd.getRoom(33));
	}
	@Test(priority=4)
	public void getRoomUsersTest() {
		Assert.assertEquals(rd.getUsers(2).size(), 2);
	}
	@Test(priority=5)
	public void getRoomUsersTestInvalidRoomID() {
		Assert.assertNull(rd.getUsers(72));
	}
	
}
