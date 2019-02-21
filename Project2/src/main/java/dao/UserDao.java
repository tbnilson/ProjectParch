package dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import model.ParchUser;
import util.HibernateUtil;

public class UserDao implements IUser {
	
	public static SessionFactory sf = HibernateUtil.getSessionFactory();

	public ParchUser getUser(String username) {
		try {
			Session sess = sf.openSession();
			Criteria crit = sess.createCriteria(ParchUser.class);
			crit.add(Restrictions.like("username", username));
			ParchUser u = (ParchUser) crit.uniqueResult();
			sf.close();
			return u;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addUser(ParchUser parchUser) {
		try {
			Session sess = sf.openSession();
			sess.beginTransaction();
			sess.persist(parchUser);
			sess.getTransaction().commit();
			sess.close();
			return true;
		} catch (HibernateException e) {
				e.printStackTrace();
				return false;
		}

	}

	public boolean verifyUser(String username, String password) {
		try {
			Session sess = sf.openSession();
			Criteria crit = sess.createCriteria(ParchUser.class);
			crit.add(Restrictions.like("username", username));
			ParchUser u = (ParchUser) crit.uniqueResult();
			if(u!=null && username.equals(u.getUsername()) && password.equals(u.getPassword())) {
				return true;
			}
			else {
				return false;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	

}
