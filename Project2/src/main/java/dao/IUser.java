package dao;


import model.ParchUser;

public interface IUser {
	
	public ParchUser getUser(String username);
	public boolean addUser(ParchUser parchUser);
	public boolean verifyUser(String username, String password);
	public boolean deleteUser(String username);

}
