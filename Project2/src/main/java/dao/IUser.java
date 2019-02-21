package dao;


import model.ParchUser;

public interface IUser {
	
	public ParchUser getUser(String username);
	public boolean addUser(ParchUser parchUser);
	public boolean verifyUser(String username, String password);
	public boolean deleteUser(String username);
	public boolean setEmail(String username,String newemail);
	public boolean setUsername(String username, String newusername);
	public boolean userExists(String username);
}
