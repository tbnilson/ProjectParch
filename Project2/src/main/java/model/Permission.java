package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Permission implements Jsonable {
	
	@ManyToOne
	private ParchUser parchUser;
	@ManyToOne
	private Room room;
	private String permissions;
	@Id
	@GeneratedValue
	private int perm_id;
	public Integer getId() {
		return perm_id;
	}
	public void setId(Integer id) {
		this.perm_id = id;
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
