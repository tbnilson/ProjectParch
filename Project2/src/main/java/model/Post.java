package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class Post implements Jsonable {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "message", columnDefinition = "CLOB")
	private String message;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ParchUser getUser() {
		return parchUser;
	}

	public void setUser(ParchUser parchUser) {
		this.parchUser = parchUser;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@ManyToOne(optional=false)
	private ParchUser parchUser;
	
	private Date timestamp;

	@ManyToOne
	private Room room;

	@Override
	public String toJsonString() {
		return "{" + 
				" \"postID\": \""+this.id+"\"," + 
				" \"roomID\": \""+this.room.getId()+"\"," + 
				" \"username\": \""+this.parchUser.getUsername()+"\"," + 
				" \"message\": \""+this.message+"\"," + 
				" \"timestamp\": \""+this.timestamp.getTime()+"\"" + 
				"}";
	}

}