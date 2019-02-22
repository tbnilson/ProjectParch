package dao;

import java.util.List;

import model.Permission;

public interface IPermission {
	public List<Permission> getUserPermissions(String username);
	public List<Permission> getRoomPermissions(int roomID);
	public Permission getPermission(String username, int roomID);
	public Permission setPermission(String username, int roomID, String permissions);
	public boolean inviteUser(int roomID, String username);
}
