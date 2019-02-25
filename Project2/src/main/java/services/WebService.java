package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Jsonable;
import model.ParchUser;
import model.Permission;
import model.Post;
import model.Room;

public class WebService {

	public static void getAllUsers(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	public static void getAllRooms(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	public static void createRoom(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");//Maybe get from session
		String roomname = request.getParameter("roomname");
		PrintWriter pr;
		
		try {
			 pr = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		
		Room room = MainService.addRoom(username,roomname);
		pr.append(room.toJsonString()).close();
	}

	/**
	 * @param request
	 * @param response
	 * takes in username, password, and email from the request parameters. Assumes the email has already been
	 * verified as valid by the client side.
	 */
	public static void registerUser(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		PrintWriter pr;
		
		try {
			 pr = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		
		boolean b = MainService.addUser(username, password, email);
		
		pr.append(b ? "true" : "false").close();
	}

	public static void deleteMessage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		PrintWriter pr;
		
		try {
			 pr = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		
		String username = request.getParameter("username");//Maybe get from session
		int postID;
		try {
			postID = Integer.parseInt(request.getParameter("postID"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pr.append("postID is not an integer");
			return;
		}
		
		if (MainService.deleteMessage(postID,username)) {
			pr.append("true").close();
		} else {
			pr.append("false").close();
		}
	}

	public static void editMessage(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter pr;
		
		try {
			 pr = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		
		String username = request.getParameter("username");//Maybe get from session
		int postID;
		try {
			postID = Integer.parseInt(request.getParameter("postID"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pr.append("postID is not an integer");
			return;
		}
		String message = request.getParameter("message");
		
		if (MainService.editMessage(postID,username,message)) {
			pr.append("true").close();
		} else {
			pr.append("false").close();
		}
		
	}

	public static void postMessage(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter pr;
		
		try {
			 pr = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		
		String username = request.getParameter("username");//Maybe get from session
		int roomID;
		try {
			roomID = Integer.parseInt(request.getParameter("roomID"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pr.append("roomID is not an integer");
			return;
		}
		String message = request.getParameter("message");
		Post post = MainService.makePost(username, roomID, message);
		if (post!=null) {
			pr.append(post.toJsonString()).close();
		} else {
			pr.append("not a valid username or roomID").close();
		}
		
	}

	public static void login(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean b = MainService.verifyUser(username, password);
		System.out.println(b);
		try {
			response.getWriter().append( (b ? "true" : "false") ).close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getUser(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");//Maybe get from session
		ParchUser u = MainService.getUser(username);
		ObjectMapper om = new ObjectMapper();

		try {
			if (u!=null) {
				//			String json = om.writeValueAsString(u);//
				System.out.println("JSON: " + u.toJsonString());
				response.getWriter().append(u.toJsonString()).close();
			} else {
				response.getWriter().append("No user of name : " + username).close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void getActiveRoomUsers(HttpServletRequest request, HttpServletResponse response) {
		int roomID;
		PrintWriter pr;
		
		try {
			 pr = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		
		try {
			roomID = Integer.parseInt(request.getParameter("roomID"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pr.append("roomID is not an integer");
			return;
		}
		
		List<Permission> perms = MainService.getRoomPerms(roomID);
		List<ParchUser> users = new ArrayList<ParchUser>();
		for (Permission p : perms) {
			if (!p.getPermissions().equals("invited") && !p.getPermissions().equals("banned")) {
				users.add(p.getUser());
			}
		}
		
		String jsonarray = MainService.toJsonArray(users);
		pr.append(jsonarray).close();
		
	}

	public static void getActiveUserRooms(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");//Maybe get from session
		List<Permission> perms = MainService.getUserPerms(username);
		List<Room> rooms = new ArrayList<Room>();
		for (Permission p : perms) {
			if (!p.getPermissions().equals("invited") && !p.getPermissions().equals("banned")) {
				rooms.add(p.getRoom());
			}
		}
		String jsonarray = MainService.toJsonArray(rooms);
		try {
			response.getWriter().append(jsonarray).close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getUserInvites(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");//Maybe get from session
		List<Permission> perms = MainService.getUserPerms(username);
		List<Room> rooms = new ArrayList<Room>();
		for (Permission p : perms) {
			if (p.getPermissions().equals("invited")) {
				rooms.add(p.getRoom());
			}
		}
		String jsonarray = MainService.toJsonArray(rooms);
		try {
			response.getWriter().append(jsonarray).close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getNewMessages(HttpServletRequest request, HttpServletResponse response) {
		int postID;
		PrintWriter pr;
		
		try {
			 pr = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		
		try {
			postID = Integer.parseInt(request.getParameter("postID"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pr.append("postID is not an integer");
			return;
		}
		
		List<Post> newposts = MainService.getNewMessages(postID);
		if (newposts!=null && newposts.size()>0) {
			pr.append(MainService.toJsonArray(newposts)).close();
		} else {
			pr.append("No new messages in this room").close();
		}
	}

	public static void inviteUser(HttpServletRequest request, HttpServletResponse response) {
		int roomID;
		PrintWriter pr;
		
		try {
			 pr = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		
		try {
			roomID = Integer.parseInt(request.getParameter("roomID"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pr.append("roomID is not an integer");
			return;
		}
		String inviter = request.getParameter("inviter"); //Maybe get from session
		String invitee = request.getParameter("invitee");
		boolean b = MainService.inviteUser(inviter, invitee, roomID);
		pr.append(b ? "true" : "false").close();
	}

	public static void acceptInvite(HttpServletRequest request, HttpServletResponse response) {
		int roomID;
		PrintWriter pr;
		
		try {
			 pr = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		
		try {
			roomID = Integer.parseInt(request.getParameter("roomID"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pr.append("roomID is not an integer");
			return;
		}
		String username = request.getParameter("username");//Maybe get from session
		Permission newperm = MainService.acceptInvite(roomID,username);
		if (newperm!=null) {
			pr.append("true").close();
		} else {
			pr.append("false").close();
		}
	}

}
