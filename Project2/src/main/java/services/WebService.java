package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.ParchUser;

public class WebService {

	public static void getAllUsers(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	public static void getAllRooms(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	public static void createRoom(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	public static void registerUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	public static void deleteMessage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	public static void editMessage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	public static void postMessage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	public static void login(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	public static void getUser(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		ParchUser u = MainService.getUser(username);
		ObjectMapper om = new ObjectMapper();
		
		try {
			String json = om.writeValueAsString(u);
			System.out.println(json);
			response.getWriter().append(json).close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void getRoomUsers(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	public static void getUserRooms(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

}
