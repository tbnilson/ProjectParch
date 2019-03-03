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
		Session sess = sf.openSession();
		ParchUser u=null;
		try {
			u = sess.get(ParchUser.class, username);
			sess.close();
		} catch (HibernateException e) {
			u = null;
		} finally {
			sess.close();
		}
		return u;
	}

	public boolean addUser(ParchUser parchUser) {
		if (userExists(parchUser.getUsername())) {
			return false;
		}
		boolean result=false;
		Session sess = sf.openSession();
		try {
			
			sess.beginTransaction();
			sess.persist(parchUser);
			sess.getTransaction().commit();
			
			result = true;
		} catch (HibernateException e) {
			sess.getTransaction().rollback();
			result = false;
		} finally {
			sess.close();
		}
		return result;

	}

	public boolean verifyUser(String username, String password) {
		boolean result=false;
		Session sess = sf.openSession();
		try {
			
			Criteria crit = sess.createCriteria(ParchUser.class);
			crit.add(Restrictions.like("username", username));
			ParchUser u = (ParchUser) crit.uniqueResult();
			sess.close();
			if(u!=null && username.equals(u.getUsername()) && password.equals(u.getPassword())) {
				result = true;
			}
			else {
				result = false;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			result = false;
		} finally {
			sess.close();
		}
		return result;
		
	}

	@Override
	public boolean deleteUser(String username) {
		if (!userExists(username)) {
			return false;
		}
		boolean result=false;
		Session sess = sf.openSession();
		try {
			
			Criteria crit = sess.createCriteria(ParchUser.class);
			crit.add(Restrictions.like("username", username));
			ParchUser u = (ParchUser) crit.uniqueResult();
			
			if (u!=null) {
				sess.beginTransaction();
				sess.delete(u);
				sess.getTransaction().commit();
	
				result = true;
			} else {
				result = false;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			sess.getTransaction().rollback();
			result = false;
		} finally {
			sess.close();
		}
		return result;
	}

	@Override
	public boolean setEmail(String username, String newemail) {
		if (!userExists(username)) {
			return false;
		}
		boolean result=false;
		Session sess = sf.openSession();
		try {
			
			Criteria crit = sess.createCriteria(ParchUser.class);
			crit.add(Restrictions.like("username", username));
			ParchUser u = (ParchUser) crit.uniqueResult();
			
			if (u!=null) {
				sess.beginTransaction();
				u.setEmail(newemail);
				sess.getTransaction().commit();
			}
			
			result = true;
		} catch (HibernateException e) {
			e.printStackTrace();
			result = false;
		} finally {
			sess.close();
		}
		return result;
	}

	@Override
	public boolean setUsername(String username, String newusername) {
		if (!userExists(username)) {
			return false;
		}
		boolean result=false;
		Session sess = sf.openSession();
		try {
			Criteria crit = sess.createCriteria(ParchUser.class);
			crit.add(Restrictions.like("username", username));
			ParchUser u = (ParchUser) crit.uniqueResult();
			
			if (u!=null && !this.userExists(newusername)) {
				sess.beginTransaction();
				u.setUsername(newusername);
				sess.getTransaction().commit();
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			sess.getTransaction().rollback();
			result = false;
		} finally {
			sess.close();
		}
		return result;
	}

	@Override
	public boolean userExists(String username) {
		// TODO Auto-generated method stub
		return this.getUser(username)!=null;
	}

	@Override
	public List<ParchUser> getAllUsers() {
		List<ParchUser> users = null;
		Session sess = sf.openSession();
		try {
			
			String hql = "FROM ParchUser";
			Query q = sess.createQuery(hql);
			
			users = q.getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			users = null;
		} finally {
			sess.close();
		}
		return users;
	}

	

}
