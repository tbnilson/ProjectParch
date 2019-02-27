package services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dao.RoomDao;
import dao.*;
import model.Jsonable;
import model.ParchUser;
import model.Permission;
import model.Post;
import model.Room;

public class MainService {
	
	public static UserDao ud = new UserDao();
	public static RoomDao rd = new RoomDao();
	public static PermissionDao permd = new PermissionDao();
	public static PostDao postd = new PostDao();

	/**
	 * @param username
	 * @return a JSON string representing the user information, or false if that uesrname 
	 * doesn't exist
	 */
	public static ParchUser getUser(String username) {
		
		return ud.getUser(username);
	}
	
	public static boolean addUser(String username, String password, String email) {
		ParchUser u = new ParchUser();
		u.setEmail(email);
		u.setPassword(password);
		u.setUsername(username);
		return ud.addUser(u);
	}
	
	public static boolean verifyUser(String username, String password) {
		return ud.verifyUser(username, password);
	}
	
	public static boolean deleteUser(String username) {
		return ud.deleteUser(username);
	}
	
	public static boolean setEmail(String username, String newemail) {
		return ud.setEmail(username, newemail);
	}
	
	public static boolean setUsername(String username, String newusername) {
		return ud.setUsername(username, newusername);
	}

	public static List<Permission> getUserPerms(String username) {
		// TODO Auto-generated method stub
		return permd.getUserPermissions(username);
	}

	public static String toJsonArray(List<? extends Jsonable> models) {
		StringBuilder json = new StringBuilder("[");
		
		for (int i = 0; i < models.size(); i++) {
			json.append(models.get(i).toJsonString());
			if (i < (models.size()-1)) {
				json.append(", ");
			}
		}
		
		json.append("]");
		return json.toString();
	}

	public static List<Permission> getRoomPerms(int roomID) {
		// TODO Auto-generated method stub
		return permd.getRoomPermissions(roomID);
	}

	public static Post makePost(String username, int roomID, String message) {
		if (!ud.userExists(username)) {
			return null;
		}
		if (rd.getRoom(roomID)==null) {
			return null;
		}
		return postd.makePost(rd.getRoom(roomID), ud.getUser(username), message);
	}

	public static Room addRoom(String username, String roomname) {
		// TODO Auto-generated method stub
		return rd.makeRoom(roomname, username);
	}

	public static List<Post> getNewMessages(int postID) {
		return postd.getNewPosts(postID);
	}

	public static boolean editMessage(int postID, String username, String message) {
		Post p = postd.getPost(postID);
		int roomID = p.getRoom().getId();
		String postowner = p.getUser().getUsername();
		Permission perm = permd.getPermission(username, roomID);
		
		//Check if the editor is the one who made the post, or an admin:
		if (postowner.equals(username) || perm.getPermissions().equals("admin") || perm.getPermissions().equals("moderator")) {
			return postd.editPost(postID, message);
		} else {
			return false;
		}
	}

	public static boolean deleteMessage(int postID, String username) {
		Post p = postd.getPost(postID);
		if (p==null) {return false;}
		int roomID = p.getRoom().getId();
		String postowner = p.getUser().getUsername();
		Permission perm = permd.getPermission(username, roomID);
		
		//Check if the editor is the one who made the post, or an admin:
		if (postowner.equals(username) || perm.getPermissions().equals("admin") || perm.getPermissions().equals("moderator")) {
			return postd.deletePost(postID);
		} else {
			return false;
		}
	}

	public static boolean inviteUser(String inviter, String invitee, int roomID) {
		// TODO Auto-generated method stub
		Room room = rd.getRoom(roomID);
		if (room==null) {return false;}
		Permission perm = permd.getPermission(inviter, roomID);
		if (ud.userExists(invitee) && 
				ud.userExists(inviter) && 
				perm!=null && 
				(perm.getPermissions().equals("admin") || perm.getPermissions().equals("moderator"))) {
			return permd.inviteUser(roomID, invitee);
		} else {
			return false;
		}
		
	}

	public static Permission acceptInvite(int roomID, String username) {
		Permission perm = permd.getPermission(username, roomID);
		if (perm==null || !perm.getPermissions().equals("invited")) {
			return null;
		}
		return permd.setPermission(username, roomID, "user");
	}

	public static List<Post> getRoomMessages(int roomID, int startnum, int endnum) {
		return postd.getRoomPosts(roomID,startnum,endnum);
	}

	public static boolean makeModerator(int roomID, String adminname, String username) {
		Permission adminperm = permd.getPermission(adminname, roomID);
		Permission userperm = permd.getPermission(username, roomID);
		
		if (userperm==null) {return false;}
		if (adminperm == null) {return false;}
		if (!adminperm.getPermissions().equals("admin")) {return false;}
		if (!userperm.getPermissions().equals("user")) {return false;}
		
		return permd.setPermission(username, roomID, "moderator")!=null;
		
	}

	public static boolean deleteRoom(int roomID, String adminname) {
		Permission adminperm = permd.getPermission(adminname, roomID);
		if (adminperm!=null && adminperm.getPermissions().equals("admin")) {
			return rd.deleteRoom(roomID);
		} else {
			return false;
		}
	}

	public static boolean banUser(int roomID, String adminname, String bannedusername) {
		Permission adminperm = permd.getPermission(adminname, roomID);
		Permission banneduserperm = permd.getPermission(bannedusername, roomID);
		if (banneduserperm!=null && banneduserperm.getPermissions().equals("banned")) {return true;}
		if (adminperm!=null && adminperm.getPermissions().equals("admin")) {
			return permd.setPermission(bannedusername, roomID, "banned")!=null;
		} else {
			return false;
		}
	}

	public static boolean unBanUser(int roomID, String adminname, String bannedusername) {
		Permission adminperm = permd.getPermission(adminname, roomID);
		Permission banneduserperm = permd.getPermission(bannedusername, roomID);
		if (banneduserperm!=null && !banneduserperm.getPermissions().equals("banned")) {return true;}
		if (adminperm!=null && adminperm.getPermissions().equals("admin")) {
			return permd.setPermission(bannedusername, roomID, "invited")!=null;
		} else {
			return false;
		}
	}
	

}
