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

public class Permissiontest {
	
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
	
}