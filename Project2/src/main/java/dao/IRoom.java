package dao;

import java.util.List;

import model.ParchUser;
import model.Room;

public interface IRoom {
	public Room makeRoom(String roomname, String username);
	public Room getRoom(int roomID);
	public List<ParchUser> getUsers(int roomID);
	public List<ParchUser> getAdmins(int roomID);
}
