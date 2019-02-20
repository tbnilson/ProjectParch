package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Room {
	@Id
	@GeneratedValue
	private int id;
	@OneToMany
	private List<Post> posts;
	@OneToMany
	private List<Permission> permissions;
}
