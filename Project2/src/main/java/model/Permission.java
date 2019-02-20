package model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Permission {
	
	@ManyToOne
	private User user;
	@ManyToOne
	private Room room;
	private String permissions;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public String getPermissions() {
		return permissions;
	}
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	
}
