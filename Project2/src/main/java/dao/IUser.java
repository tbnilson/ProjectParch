package dao;


import model.User;

public interface IUser {
	
	public User getUser(String username);
	public boolean addUser(User user);
	public boolean verifyUser(String username, String password);
	

}
