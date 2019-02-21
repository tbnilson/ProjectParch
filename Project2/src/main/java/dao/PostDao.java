package dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.ParchUser;
import model.Post;
import model.Room;
import util.HibernateUtil;

public class PostDao implements IPost {
	
	private static SessionFactory sf = HibernateUtil.getSessionFactory();

	@Override
	public Post getPost(int messageID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getRoomPosts(int roomID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getUserPosts(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletePost(int messageID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editPost(int messageID, String newmessage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Post makePost(Room room, ParchUser user, String message) {
		try {
			Session sess = sf.openSession();
			sess.beginTransaction();
			
			Post post = new Post();
			post.setUser(user);
			post.setRoom(room);
			post.setMessage(message);
			sess.persist(post);
			
			sess.getTransaction().commit();
			sess.close();
			return post;
		} catch (HibernateException e) {
				e.printStackTrace();
				return null;
		}
	}

}
