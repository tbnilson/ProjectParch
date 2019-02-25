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

public class Permissiontest {
	
	public static UserDao ud = new UserDao();
	public static RoomDao rd = new RoomDao();
	public static PermissionDao pd = new PermissionDao();
	public static PostDao pod = new PostDao();
	private static Room permtestroom;
	
	@BeforeClass
	public void startup() {
		Playground.populateTestDB();
		Playground.postMessages();
		permtestroom = rd.makeRoom("Perm Testing Room", "test1");
	}
	
	@AfterClass
	public void teardown() {
		HibernateUtil.getSessionFactory().close();
	}
	
	@Test(priority=1)
	public void inviteUserTest() {
		ParchUser u1 = new ParchUser();
		u1.setUsername("PermTesting");
		u1.setPassword("testpass");
		u1.setEmail("u@aol.com");
		ud.addUser(u1);
		
		
		Assert.assertTrue(pd.inviteUser(permtestroom.getId(), "PermTesting"));
		Assert.assertEquals(pd.getPermission("PermTesting", permtestroom.getId()).getPermissions(), "invited");
	}
	
	@Test(priority=2)
	public void inviteInvalidUserTest() {
		Assert.assertFalse(pd.inviteUser(permtestroom.getId(), "PermTestingBadUsername"));
	}
	
	@Test(priority=3)
	public void acceptValidInviteTest() {
		Assert.assertNotNull(pd.setPermission("PermTesting", permtestroom.getId(), "user"));
		Assert.assertEquals(pd.getPermission("PermTesting", permtestroom.getId()).getPermissions(), "user");
	}
	
	@Test(priority=4)
	public void getPermissionsValidUser() {
		Assert.assertEquals(pd.getPermission("PermTesting", permtestroom.getId()).getPermissions(), "user");
	}
	
	@Test(priority=4)
	public void getPermissionsInvalidUser() {
		Assert.assertNull(pd.getPermission("PermTestingBadUsername", permtestroom.getId()));
	}
	
	@Test(priority=4)
	public void getPermissionsInvalidRoom() {
		Assert.assertNull(pd.getPermission("PermTesting", -87));
	}
	
}