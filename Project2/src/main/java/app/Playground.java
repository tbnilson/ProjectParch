package app;

import dao.UserDao;
import model.ParchUser;
import util.HibernateUtil;

public class Playground {
	public static UserDao ud = new UserDao();
	public static void main(String[] args) {
		
		populateTestDB();
		
		System.out.println(ud.verifyUser("test1", "testpass"));
		System.out.println(ud.verifyUser("test2", "testpass"));
		System.out.println(ud.verifyUser("baduser", "testpass"));
		System.out.println(ud.getUser("test1").toJsonString());
		
		System.out.println(ud.deleteUser("test1"));
		System.out.println(ud.deleteUser("test1"));
		
		HibernateUtil.getSessionFactory().close();
	}
	
	public static void populateTestDB() {
		ParchUser u1 = new ParchUser();
		u1.setUsername("test1");
		u1.setPassword("testpass");
		u1.setEmail("u@aol.com");
		ud.addUser(u1);
		
		ParchUser u2 = new ParchUser();
		u2.setUsername("test2");
		u2.setPassword("testpass");
		u2.setEmail("v@aol.com");
		ud.addUser(u2);
	}
}
