package services;

import java.util.Iterator;
import java.util.List;

import dao.RoomDao;
import dao.*;
import model.Jsonable;
import model.ParchUser;
import model.Permission;
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
	
	public static boolean addUser(ParchUser u) {
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
	

}