package app;

import dao.PermissionDao;
import dao.PostDao;
import dao.RoomDao;
import dao.UserDao;
import model.ParchUser;
import model.Room;
import util.HibernateUtil;

public class Playground {
	public static UserDao ud = new UserDao();
	public static RoomDao rd = new RoomDao();
	public static PermissionDao pd = new PermissionDao();
	public static PostDao pod = new PostDao();
	public static void main(String[] args) {
		
		populateTestDB();
		
		
		
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
		
		Room roomone = rd.makeRoom("Room One", u2.getUsername());
		pd.inviteUser(roomone.getId(), u1.getUsername());
	}
}
