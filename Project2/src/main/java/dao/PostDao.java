package dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
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
		try {
			Session sess = sf.openSession();
			sess.beginTransaction();
			
			Post post = sess.get(Post.class, messageID);
			
			sess.getTransaction().commit();
			sess.close();
			return post;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Post> getRoomPosts(int roomID) {
		try {
			Session sess = sf.openSession();
			
			Criteria crit = sess.createCriteria(Post.class);
			crit.add(Restrictions.like("ROOM_ID", roomID));
			List<Post> posts = crit.list();
			
			if (posts!=null) {
				sess.close();
				return posts;
			} else {
				sess.close();
				return null;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Post> getUserPosts(String username) {
		try {
			Session sess = sf.openSession();
			
			Criteria crit = sess.createCriteria(Post.class);
			crit.add(Restrictions.like("PARCHUSER_USERNAME", username));
			List<Post> posts = crit.list();
			
			if (posts!=null) {
				sess.close();
				return posts;
			} else {
				sess.close();
				return null;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deletePost(int messageID) {
		try {
			Session sess = sf.openSession();
			sess.beginTransaction();
			
			Post post = sess.get(Post.class, messageID);
			
			sess.delete(post);
			
			sess.getTransaction().commit();
			sess.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean editPost(int messageID, String newmessage) {
		try {
			Session sess = sf.openSession();
			sess.beginTransaction();
			
			Post post = sess.get(Post.class, messageID);
			
			post.setMessage(newmessage);
			sess.update(post);
			
			sess.getTransaction().commit();
			sess.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Post makePost(Room room, ParchUser user, String message) {
		
		try {
			Session sess = sf.openSession();
			sess.beginTransaction();
			
			Post post = new Post();
			sess.persist(post);
			post.setUser(user);
			post.setRoom(room);
			post.setMessage(message);
			post.setTimestamp(new Date());
			
			
			sess.getTransaction().commit();
			sess.close();
			return post;
		} catch (HibernateException e) {
				e.printStackTrace();
				return null;
		}
	}

	@Override
	public List<Post> getNewPosts(int postID) {
		Post latestpost = getPost(postID);
		if (latestpost==null) {return null;}
		try {
			Session sess = sf.openSession();

			String hql = "select P from Post as P "
					+ "where P.room.id = ? and P.timestamp>? ORDER BY P.timestamp DESC";
			Query q = sess.createQuery(hql);
			q.setParameter(0, latestpost.getRoom().getId());
			q.setParameter(1, latestpost.getTimestamp());
			
			List<Post> posts = q.getResultList();
			
			if (posts!=null) {
				sess.close();
				return posts;
			} else {
				sess.close();
				return null;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

}
