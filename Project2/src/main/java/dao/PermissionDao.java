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
			String hql = "select P from Permission as P "
					+ "where P.parchUser.username = ?";
			Query q = sess.createQuery(hql);
			q.setString(0, username);
			
			List<Permission> p = q.list();

			sess.close();
			return p;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Permission> getRoomPermissions(int roomID) {
		try {
			Session sess = sf.openSession();
			String hql = "select P from Permission as P "
					+ "where P.room.id = ?";
			Query q = sess.createQuery(hql);
			q.setInteger(0, roomID);
			
			List<Permission> p = q.list();
			if (p.size()==0) {
				p=null;
			}
			sess.close();
			return p;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Permission setPermission(String username, int roomID, String permissions) {
		if (!ud.userExists(username) || rd.getRoom(roomID)==null) {return null;} //This should maybe throw an exception
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
		if (!ud.userExists(username) || rd.getRoom(roomID)==null) {return null;}//This should maybe throw an exception
		try {
			Session sess = sf.openSession();
			String hql = "select P from Permission as P "
					+ "where P.parchUser.username = ? and P.room.id = ?";
			Query q = sess.createQuery(hql);
			q.setString(0, username);
			q.setInteger(1, roomID);
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
		Permission testperm = getPermission(username, roomID);
		if (testperm==null) {
			return setPermission(username, roomID, "invited")!=null;
		} else {
			return false;
		}
	}

}
