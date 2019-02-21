package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Permission {
	
	@ManyToOne
	private ParchUser parchUser;
	@ManyToOne
	private Room room;
	private String permissions;
	@Id
	@GeneratedValue
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ParchUser getUser() {
		return parchUser;
	}
	public void setUser(ParchUser parchUser) {
		this.parchUser = parchUser;
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
	public String toJsonString() {
		return null;
	}
}
