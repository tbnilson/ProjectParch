package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Room implements Jsonable {
	@Id
	@GeneratedValue
	private int id;
	@OneToMany
	private List<Post> posts;
	@OneToMany(mappedBy="room")
	private List<Permission> permissions;
	private String roomname;

	public String getRoomname() {
		return roomname;
	}
	public void setRoomName(String roomname) {
		this.roomname = roomname;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	@Override
	public String toJsonString() {
		return "{" + 
				" \"roomID\": \""+this.id+"\"," + 
				" \"roomname\": \""+this.roomname+"\"" + 
				"}";
	}
	
}
