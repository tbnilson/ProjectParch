package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Entity
public class ParchUser {
	
	@OneToMany
	private List<Post> posts;
	@Id
	private String username;
	@OneToMany
	private List<Permission> permissions;
	
	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
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
	public String toJsonString() {
		ObjectMapper om = new ObjectMapper();
		ObjectNode node = om.valueToTree(this);
		node.remove("password");
		try {
			return om.writeValueAsString(node);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
