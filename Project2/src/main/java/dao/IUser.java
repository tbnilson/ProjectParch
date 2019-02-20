package dao;

import java.util.List;

import model.Permission;
import model.Room;
import model.User;

public interface IUser {
	
	public User getUser(String username);
	public boolean addUser(User user);
	public List<Room> getRooms(List<Permission> permissions);
	

}
