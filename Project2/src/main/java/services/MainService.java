package services;

import dao.UserDao;
import model.ParchUser;

public class MainService {
	
	public static UserDao ud = new UserDao();

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
	

}
