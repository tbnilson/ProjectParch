package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import model.ParchUser;
import model.Permission;
import model.Room;
import util.HibernateUtil;

public class PermissionDao implements IPermission {
	
	private static SessionFactory sf = HibernateUtil.getSessionFactory();
	private static UserDao ud = new UserDao();
	private static RoomDao rd = new RoomDao();

	@Override
	public List<Permission> getUserPermissions(String username) {
		try {
			Session sess = sf.openSession();
			ParchUser user = sess.get(ParchUser.class, username);
			sess.close();
			return user.getPermissions();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Permission> getRoomPermissions(int roomID) {
		try {
			Session sess = sf.openSession();
			Room room = sess.get(Room.class, roomID);
			sess.close();
			return room.getPermissions();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Permission addPermission(String username, int roomID, String permissions) {
		try {
			Session sess = sf.openSession();
			sess.beginTransaction();
			
			Permission p = getPermission(username, roomID);
			if (p!=null) {
				p.setPermissions(permissions);
				sess.update(p);
			} else {
				p = new Permission();
				p.setPermissions(permissions);
				p.setRoom(rd.getRoom(roomID));
				p.setUser(ud.getUser(username));
				sess.persist(p);
			}
			
			sess.getTransaction().commit();
			sess.close();
			return p;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Permission getPermission(String username, int roomID) {
		try {
			Session sess = sf.openSession();
			String hql = "select P from Permission as P "
					+ "where P.parchUser.username = ?";
			Query q = sess.createQuery(hql);
			q.setString(0, username);
//			q.setParameter(1, roomID);
			
			Permission p = (Permission) q.uniqueResult();
//			q.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//			List aliasToValueMapList=q.getResultList();
			sess.close();
			return p;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean inviteUser(int roomID, String username) {
//		try {
//			Session sess = sf.openSession();
//			sess.beginTransaction();
//			ParchUser user = ud.getUser(username);
//			Room room = rd.getRoom(roomID);
//			if (room!=null && user!=null) {
//				Permission p = new Permission();
//				p.setRoom(room);
//				p.setUser(user);
//				p.setPermissions("invited");
//				sess.persist(p);
//				sess.getTransaction().commit();
//				sess.close();
//				return true;
//			} else {
//				sess.close();
//				return false;
//			}
//		} catch (HibernateException e) {
//			e.printStackTrace();
//			return false;
//		}
		// Make sure the user isn't already a part of the room
		if (getPermission(username, roomID)==null) {
			return addPermission(username, roomID, "invited")!=null;
		} else {
			return false;
		}
	}

}
