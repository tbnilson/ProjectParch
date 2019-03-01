package dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import model.ParchUser;
import util.HibernateUtil;

public class UserDao implements IUser {
	
	private static SessionFactory sf = HibernateUtil.getSessionFactory();

	public ParchUser getUser(String username) {
		try {
			Session sess = sf.openSession();
			ParchUser u = sess.get(ParchUser.class, username);
			sess.close();
			return u;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addUser(ParchUser parchUser) {
		if (userExists(parchUser.getUsername())) {
			return false;
		}
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
			sess.close();
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

	@Override
	public boolean deleteUser(String username) {
		try {
			Session sess = sf.openSession();
			
			Criteria crit = sess.createCriteria(ParchUser.class);
			crit.add(Restrictions.like("username", username));
			ParchUser u = (ParchUser) crit.uniqueResult();
			
			if (u!=null) {
				sess.beginTransaction();
				sess.delete(u);
				sess.getTransaction().commit();
				sess.close();
				return true;
			} else {
				sess.close();
				return false;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean setEmail(String username, String newemail) {
		try {
			Session sess = sf.openSession();
			Criteria crit = sess.createCriteria(ParchUser.class);
			crit.add(Restrictions.like("username", username));
			ParchUser u = (ParchUser) crit.uniqueResult();
			
			if (u!=null) {
				sess.beginTransaction();
				u.setEmail(newemail);
				sess.getTransaction().commit();
			}
			
			sess.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean setUsername(String username, String newusername) {
		try {
			Session sess = sf.openSession();
			Criteria crit = sess.createCriteria(ParchUser.class);
			crit.add(Restrictions.like("username", username));
			ParchUser u = (ParchUser) crit.uniqueResult();
			
			if (u!=null && !this.userExists(newusername)) {
				sess.beginTransaction();
				u.setUsername(newusername);
				sess.getTransaction().commit();
			}
			
			sess.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean userExists(String username) {
		// TODO Auto-generated method stub
		return this.getUser(username)!=null;
	}

	@Override
	public List<ParchUser> getAllUsers() {
		try {
			Session sess = sf.openSession();
			String hql = "FROM ParchUser";
			Query q = sess.createQuery(hql);
			
			return q.getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	

}
