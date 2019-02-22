package dao;

import java.util.List;

import model.ParchUser;
import model.Room;

public interface IRoom {
	/**
	 * @param roomname
	 * @param username
	 * @return A new Room with name roomname, and creates a new Permission establishing username
	 * as an admin 
	 */
	public Room makeRoom(String roomname, String username);
	/**
	 * @param roomID
	 * @return The Room with id roomID
	 */
	public Room getRoom(int roomID);
	/**
	 * @param roomID
	 * @return A list of all users with any level of Permission in the Room of roomID
	 */
	public List<ParchUser> getUsers(int roomID);
	/**
	 * @param roomID
	 * @return A list of all users with Permission of "admin" in the Room of roomID
	 */
	public List<ParchUser> getAdmins(int roomID);
	
}
