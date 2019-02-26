package dao;

import java.util.List;

import model.ParchUser;
import model.Post;
import model.Room;

public interface IPost {
	public Post makePost(Room room, ParchUser name, String message);
	public Post getPost(int messageID);
	public List<Post> getRoomPosts(int roomID);
	public List<Post> getRoomPosts(int roomID,int startnum,int endnum);
	public List<Post> getUserPosts(String username);
	public boolean deletePost(int messageID);
	public boolean editPost(int messageID, String newmessage);
	public List<Post> getNewPosts(int postID);
}
