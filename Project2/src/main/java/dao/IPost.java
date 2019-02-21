package dao;

import java.util.List;

import model.Post;

public interface IPost {
	public Post makePost(int roomID, String username, String message);
	public Post getPost(int messageID);
	public List<Post> getRoomPosts(int roomID);
	public List<Post> getUserPosts(String username);
	public boolean deletePost(int messageID);
	public boolean editPost(int messageID, String newmessage);
}
