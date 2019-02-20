package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@OneToMany
	private List<Post> posts;
	@Id
	private String username;
	@OneToMany
	private List<Permission> permissions;
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	
}
