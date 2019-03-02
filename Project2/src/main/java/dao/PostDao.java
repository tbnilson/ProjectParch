package dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.PropertyValueException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import model.ParchUser;
import model.Post;
import model.Room;
import util.HibernateUtil;

public class PostDao implements IPost {
	
	private static SessionFactory sf = HibernateUtil.getSessionFactory();

	@Override
	public Post getPost(int messageID) {
		Post post=null;
		Session sess = sf.openSession();
		try {
			
			sess.beginTransaction();
			
			post = sess.get(Post.class, messageID);
			
			sess.getTransaction().commit();
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
			post = null;
		} finally {
			sess.close();
		}
		return post;
	}

	@Override
	public List<Post> getRoomPosts(int roomID) {
		List<Post> posts = null;
		Session sess = sf.openSession();
		try {
			Criteria crit = sess.createCriteria(Post.class);
			crit.add(Restrictions.like("ROOM_ID", roomID));
			posts = crit.list();
			
			if (posts.size()==0) {
				posts=null;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			posts = null;
		} finally {
			sess.close();
		}
		return posts;
	}

	@Override
	public List<Post> getUserPosts(String username) {
		List<Post> posts = null;
		Session sess = sf.openSession();
		try {
			
			String hql = "select P from Post as P "
					+ "where P.parchUser.username=? ORDER BY P.timestamp DESC";
			Query q = sess.createQuery(hql);
			q.setParameter(0, username);
			
			posts = q.getResultList();
			
			if (posts.size()==0) {
				posts=null;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			posts = null;
		} finally {
			sess.close();
		}
		return posts;
	}

	@Override
	public boolean deletePost(int messageID) {
		boolean result=false;
		Session sess = sf.openSession();
		try {
			sess.beginTransaction();
			
			Post post = sess.get(Post.class, messageID);
			
			sess.delete(post);
			
			sess.getTransaction().commit();
			sess.close();
			result = true;
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
	public boolean editPost(int messageID, String newmessage) {
		boolean result=false;
		Session sess = sf.openSession();
		try {
			
			sess.beginTransaction();
			
			Post post = sess.get(Post.class, messageID);
			
			post.setMessage(newmessage);
			sess.update(post);
			
			sess.getTransaction().commit();
			
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
	public Post makePost(Room room, ParchUser user, String message) {
		if (room==null || user==null) {return null;}
		Post post = new Post();
		Session sess = sf.openSession();
		try {
			
			sess.beginTransaction();
			
			
			
			post.setUser(user);
			post.setRoom(room);
			post.setMessage(message);
			post.setTimestamp(new Date());
			sess.persist(post);
			
			sess.getTransaction().commit();
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
			sess.getTransaction().rollback();
			post = null;
		} finally {
			sess.close();
		}
		return post;
	}

	@Override
	public List<Post> getNewPosts(int postID) {
		Post latestpost = getPost(postID);
		if (latestpost==null) {
			return null;
		}

		Session sess = sf.openSession();
		List<Post> posts = null;
		
		try {
			

			String hql = "select P from Post as P "
					+ "where P.room.id = ? and P.timestamp>? ORDER BY P.timestamp DESC";
			Query q = sess.createQuery(hql);
			q.setParameter(0, latestpost.getRoom().getId());
			q.setParameter(1, latestpost.getTimestamp());
			
			posts = q.getResultList();
			
			if (posts.size()==0) {
				posts=null;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			posts = null;
		} finally {
			sess.close();
		}
		return posts;
	}

	@Override
	public List<Post> getRoomPosts(int roomID, int startnum, int endnum) {
		List<Post> posts = null;
		Session sess = sf.openSession();
		try {

			String hql = "select P from Post as P "
					+ "where P.room.id = ? ORDER BY P.timestamp DESC";
			Query q = sess.createQuery(hql);
			q.setParameter(0, roomID);
			q.setFirstResult(startnum);
			q.setMaxResults(endnum);
			
			posts = q.getResultList();
			
			if (posts.size()==0) {
				posts=null;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			posts = null;
		} finally {
			sess.close();
		}
		return posts;
	}

}
