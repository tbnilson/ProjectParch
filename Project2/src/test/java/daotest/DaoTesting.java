package daotest;


import org.testng.annotations.BeforeClass;

import dao.PermissionDao;
import dao.PostDao;
import dao.RoomDao;
import dao.UserDao;

import org.testng.annotations.AfterClass;

public class DaoTesting {

	public static UserDao ud = new UserDao();
	public static RoomDao rd = new RoomDao();
	public static PermissionDao pd = new PermissionDao();
	public static PostDao pod = new PostDao();

	@BeforeClass
	public void beforeClass() {
	}

	@AfterClass
	public void afterClass() {
	}

}
