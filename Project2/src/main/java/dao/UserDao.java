package dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import model.User;
import util.HibernateUtil;

public class UserDao implements IUser {
	
	public static SessionFactory sf = HibernateUtil.getSessionFactory();

	public User getUser(String username) {
		try {
			Session sess = sf.openSession();
			Criteria crit = sess.createCriteria(User.class);
			crit.add(Restrictions.like("username", username));
			User u = (User) crit.uniqueResult();
			sf.close();
			return u;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addUser(User user) {
		try {
			Session sess = sf.openSession();
			sess.beginTransaction();
			sess.persist(user);
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
			Criteria crit = sess.createCriteria(User.class);
			crit.add(Restrictions.like("username", username));
			User u = (User) crit.uniqueResult();
			if(username.equals(u.getUsername()) && password.equals(u.getPassword())) {
				return true;
			}
			else
				return false;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	

}
