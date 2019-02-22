package dao;

import java.util.List;

import model.Permission;

public interface IPermission {
	public List<Permission> getUserPermissions(String username);
	public List<Permission> getRoomPermissions(int roomID);
	public Permission getPermission(String username, int roomID);
	public Permission addPermission(String username, int roomID, String permissions);
}
