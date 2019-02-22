package app;

import java.util.List;

import dao.PermissionDao;
import dao.PostDao;
import dao.RoomDao;
import dao.UserDao;
import model.ParchUser;
import model.Permission;
import model.Post;
import model.Room;
import util.HibernateUtil;

public class Playground {
	public static UserDao ud = new UserDao();
	public static RoomDao rd = new RoomDao();
	public static PermissionDao pd = new PermissionDao();
	public static PostDao pod = new PostDao();
	public static void main(String[] args) {
		
		populateTestDB();
		
		postMessages();
		
		
		
		HibernateUtil.getSessionFactory().close();
	}
	
	private static void postMessages() {
		// TODO Auto-generated method stub
		List<Permission> test2perms = pd.getUserPermissions("test2");
		for (Permission p : test2perms) {
			System.out.println(p.getUser().getUsername() + " : " + p.getPermissions() + " = " + p.getRoom().getRoomname());
		}
		Post post1 = pod.makePost(test2perms.get(0).getRoom(), test2perms.get(0).getUser(), "First test posting message");
		Post post2 = pod.makePost(test2perms.get(0).getRoom(), test2perms.get(0).getUser(), "Second test posting message");
		Post post3 = pod.makePost(test2perms.get(0).getRoom(), test2perms.get(0).getUser(), "Third test posting message");
		
		pod.editPost(post2.getId(), "Edited second message");
		System.out.println("Post " + post3.getId() + " : " + pod.getPost(post3.getId()).getMessage());
		pod.deletePost(post3.getId());
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
