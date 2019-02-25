package serf;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.Playground;
import services.WebService;

public class RequestHelper {

	
	
	public static void Process(HttpServletRequest request, HttpServletResponse response) {
		String uri = request.getRequestURI();
		
		switch (uri) {
		case "/Project2/getAllUsers.do":{
			WebService.getAllUsers(request, response);
			break;
		}
		case "/Project2/getUser.do":{
			WebService.getUser(request, response);
			break;
		}
		case "/Project2/getRoomUsers.do":{
			WebService.getActiveRoomUsers(request, response);
			break;
		}
		case "/Project2/getAllRooms.do":{
			WebService.getAllRooms(request, response);
			break;
		}
		case "/Project2/getUserRooms.do":{
			WebService.getActiveUserRooms(request, response);
			break;
		}
		case "/Project2/login.do":{
			WebService.login(request, response);
			break;
		}
		case "/Project2/postMessage.do":{
			WebService.postMessage(request, response);
			break;
		}
		case "/Project2/editMessage.do":{
			WebService.editMessage(request, response);
			break;
		}
		case "/Project2/deleteMessage.do":{
			WebService.deleteMessage(request, response);
			break;
		}
		case "/Project2/registerUser.do":{
			WebService.registerUser(request, response);
			break;
		}
		case "/Project2/createRoom.do":{
			WebService.createRoom(request, response);
			break;
		}
		case "/Project2/getNewMessages.do":{
			WebService.getNewMessages(request, response);
			break;
		}
		case "/Project2/populate.do":{
			Playground.populateTestDB();
			Playground.postMessages();
			break;
		}
		default:{
			try {
				response.getWriter().append("bad url").close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	
	}
	
	
}
