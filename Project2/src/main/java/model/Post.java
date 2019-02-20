package model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Post {
	
	@Id
	@GeneratedValue
	private int id;
	private String message;
	
	@ManyToOne
	private User user;
	private Date timestamp;
	
	@ManyToOne
	private Room room;

}
