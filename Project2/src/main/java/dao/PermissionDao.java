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
import org.hibernate.Transaction;
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
		List<Permission> p=null;
		Session sess=sf.openSession();
		try {
			String hql = "select P from Permission as P "
					+ "where P.parchUser.username = ?";
			Query q = sess.createQuery(hql);
			q.setString(0, username);
			
			p = q.list();
			if (p.size()==0) {
				p=null;
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			p=null;
		} finally {
			sess.close();
		}
		return p;
	}

	@Override
	public List<Permission> getRoomPermissions(int roomID) {
		List<Permission> p=null;
		Session sess=sf.openSession();
		try {
			String hql = "select P from Permission as P "
					+ "where P.room.id = ?";
			Query q = sess.createQuery(hql);
			q.setInteger(0, roomID);
			
			p = q.list();
			if (p.size()==0) {
				p=null;
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			p = null;
		} finally {
			sess.close();
		}
		return p;
	}

	@Override
	public Permission setPermission(String username, int roomID, String permissions) {
		if (!ud.userExists(username) || rd.getRoom(roomID)==null) {return null;} //This should maybe throw an exception
		Permission p=null;
		Session sess = sf.openSession();
		try {
			
			sess.beginTransaction();
			
			p = getPermission(username, roomID);
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
			
		} catch (HibernateException e) {
			e.printStackTrace();
			p = null;
		} finally {
			sess.close();
		}
		
		return p;
	}

	@Override
	public Permission getPermission(String username, int roomID) {
		if (!ud.userExists(username) || rd.getRoom(roomID)==null) {return null;}//This should maybe throw an exception
		Session sess = sf.openSession();
		Permission p=null;
		try {
			
			String hql = "select P from Permission as P "
					+ "where P.parchUser.username = ? and P.room.id = ?";
			Query q = sess.createQuery(hql);
			q.setString(0, username);
			q.setInteger(1, roomID);
//			q.setParameter(1, roomID);
			
			p = (Permission) q.uniqueResult();
//			q.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//			List aliasToValueMapList=q.getResultList();
			
			return p;
		} catch (HibernateException e) {
			e.printStackTrace();
			p = null;
		} finally {
			sess.close();
		}
		return p;
	}

	@Override
	public boolean inviteUser(int roomID, String username) {
		Permission testperm = getPermission(username, roomID);
		if (testperm==null) {
			return setPermission(username, roomID, "invited")!=null;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteRoomPerms(int roomID) {
		Session sess = sf.openSession();
		boolean returnval = false;
		try {
			
			Transaction transaction = sess.beginTransaction();
			try {
				
				
				// your code
				String hql = "delete from Permission where roomID=?";
				Query query = sess.createQuery(hql);
				query.setParameter(0, roomID);
				System.out.println(query.executeUpdate());
				// your code end

				transaction.commit();
				sess.close();
				returnval = true;
			} catch (Throwable t) {
				transaction.rollback();
				
				throw t;
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnval=false;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnval=false;
		} finally {
			sess.close();
		}
		return returnval;
	}

}
