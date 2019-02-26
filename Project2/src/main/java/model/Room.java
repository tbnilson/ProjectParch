package model;

import java.net.URI;
import java.net.URISyntaxException;
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
		try {
			return "{" + 
					" \"roomID\": \""+this.id+"\"," + 
					" \"roomname\": \""+ new URI(null,null,this.roomname,null).getRawPath() +"\"" + 
					"}";
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
}
