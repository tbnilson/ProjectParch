package dao;

import java.util.List;

import model.Permission;

public interface IPermission {
	public List<Permission> getUserPermissions(String username);
	public List<Permission> getRoomPermissions(int roomID);
	public boolean isUser(String username, int roomID);
	public boolean isAdmin(String username, int roomID);
}
