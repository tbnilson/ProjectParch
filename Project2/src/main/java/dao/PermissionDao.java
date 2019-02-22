package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import model.Permission;
import model.Room;
import util.HibernateUtil;

public class PermissionDao implements IPermission {
	
	private static SessionFactory sf = HibernateUtil.getSessionFactory();
	private static UserDao ud = new UserDao();

	@Override
	public List<Permission> getUserPermissions(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Permission> getRoomPermissions(int roomID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Permission addPermission(String username, int roomID, String permissions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Permission getPermission(String username, int roomID) {
		try {
			Session sess = sf.openSession();
			Criteria crit = sess.createCriteria(Permission.class);
			crit.add(Restrictions.like("room_id", roomID));
			crit.add(Restrictions.like("parchuser_username", username));
			Permission p = 
			sess.close();
			return p;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

}
