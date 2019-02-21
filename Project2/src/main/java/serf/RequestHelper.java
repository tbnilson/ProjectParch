package serf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.WebService;

public class RequestHelper {

	
	
	public static void Process(HttpServletRequest request, HttpServletResponse response) {
		String uri = request.getRequestURI();
		
		switch (uri) {
		case "/MasterServlet/getAllUsers.do":{
			WebService.getAllUsers(request, response);
			break;
		}
		case "/MasterServlet/getUser.do":{
			WebService.getUser(request, response);
			break;
		}
		case "/MasterServlet/getRoomUsers.do":{
			WebService.getRoomUsers(request, response);
			break;
		}
		case "/MasterServlet/getAllRooms.do":{
			WebService.getAllRooms(request, response);
			break;
		}
		case "/MasterServlet/getUserRooms.do":{
			WebService.getUserRooms(request, response);
			break;
		}
		case "/MasterServlet/login.do":{
			WebService.login(request, response);
			break;
		}
		case "/MasterServlet/postMessage.do":{
			WebService.postMessage(request, response);
			break;
		}
		case "/MasterServlet/editMessage.do":{
			WebService.editMessage(request, response);
			break;
		}
		case "/MasterServlet/deleteMessage.do":{
			WebService.deleteMessage(request, response);
			break;
		}
		case "/MasterServlet/registerUser.do":{
			WebService.registerUser(request, response);
			break;
		}
		case "/MasterServlet/createRoom.do":{
			WebService.createRoom(request, response);
			break;
		}
		}
	
	}
	
	
}
