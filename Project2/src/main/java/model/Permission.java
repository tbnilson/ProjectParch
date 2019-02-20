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
}
