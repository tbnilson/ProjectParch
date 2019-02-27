package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import model.ParchUser;
import model.Permission;
import model.Room;
import util.HibernateUtil;

public class RoomDao implements IRoom {
	
	private static SessionFactory sf = HibernateUtil.getSessionFactory();
	private static UserDao ud = new UserDao();
	private static IPermission permd = new PermissionDao();

	@Override
	public Room makeRoom(String roomname, String username) {
		if (!ud.userExists(username)) {return null;}
		try {
			Session sess = sf.openSession();
			sess.beginTransaction();
			
			Room room = new Room();
			room.setRoomName(roomname);
			
			ParchUser owner = ud.getUser(username);
			Permission p = new Permission();
			p.setUser(owner);
			p.setRoom(room);
			p.setPermissions("admin");
			
			sess.persist(p);
			sess.persist(room);
			
			sess.getTransaction().commit();
			sess.close();
			return room;
		} catch (HibernateException e) {
				e.printStackTrace();
				return null;
		}
	}

	@Override
	public Room getRoom(int roomID) {
		// TODO Auto-generated method stub
		try {
			Session sess = sf.openSession();
			Room room = sess.get(Room.class, roomID);
			sess.close();
			return room;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ParchUser> getUsers(int roomID) {
		// TODO Auto-generated method stub
		try {
			List<ParchUser> users = new ArrayList<ParchUser>();
			Session sess = sf.openSession();
			Room room = sess.get(Room.class, roomID);
			if (room==null) {return null;}
			for (Permission p : room.getPermissions()) {
				users.add(p.getUser());
			}
			sess.close();
			return users;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ParchUser> getAdmins(int roomID) {
		try {
			Session sess = sf.openSession();
			
			Criteria crit = sess.createCriteria(Permission.class);
			crit.add(Restrictions.like("room_id", roomID));
			crit.add(Restrictions.like("permissions", "admin"));
			List<ParchUser> users = crit.list();
			
			sess.close();
			return users;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteRoom(int roomID) {
		// THis won't actually delete the room from the database, just delete the permissions related to that room.
		if (getRoom(roomID)==null) {return false;}
		return permd.deleteRoomPerms(roomID);
	}
}
