package dao;

import model.Post;

public interface IPost {
	public Post makePost(int roomID, String username, String message);
	public Post getPost(int roomID, String username, int messageID);
	public boolean deletePost(int messageID, String username);
	public boolean editPost(int messageID, String username);
}
