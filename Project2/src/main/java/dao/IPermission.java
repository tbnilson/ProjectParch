package dao;

import java.util.List;

import model.Permission;

public interface IPermission {
	/**
	 * @param username
	 * @return A list of detached permissions relating to a specific user 
	 */
	public List<Permission> getUserPermissions(String username);
	/**
	 * @param roomID
	 * @return A list of detached permissions relating to a specific room
	 */
	public List<Permission> getRoomPermissions(int roomID);
	/**
	 * @param username
	 * @param roomID
	 * @return The permission of username in roomID, or null if the user has no permissions in
	 * that room
	 */
	public Permission getPermission(String username, int roomID);
	/**
	 * @param username
	 * @param roomID
	 * @param permissions
	 * @return The updated permission. This method will create a new Permission if one did not already
	 * exist for this username/roomID combination.
	 */
	public Permission setPermission(String username, int roomID, String permissions);
	/**
	 * @param roomID
	 * @param username
	 * @return If username does not have any permissions in roomID, then a new Permission will be
	 * created with permissions="invited", and return true. If a permission already exists between 
	 * the two, then will return false. 
	 */
	public boolean inviteUser(int roomID, String username);
}
