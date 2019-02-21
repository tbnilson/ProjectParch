package app;

import dao.UserDao;
import model.ParchUser;

public class Playground {

	public static void main(String[] args) {
		UserDao ud = new UserDao();
		ParchUser u = new ParchUser();
		u.setUsername("test");
		u.setPassword("testpass");
		ud.addUser(u);
	}
}
