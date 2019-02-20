package dao;

import java.util.List;

import org.hibernate.SessionFactory;

import model.Permission;
import model.Room;
import model.User;
import util.HibernateUtil;

public class UserDao implements IUser {
	
	public static SessionFactory sf = HibernateUtil.getSessionFactory();

	public User getUser(String username) {
		
		return null;
	}

	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Room> getRooms(List<Permission> permissions) {
		// TODO Auto-generated method stub
		return null;
	}

}
