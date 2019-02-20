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
}
