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
		case "/Project2/getMessagesBefore.do":{
			WebService.getRoomMessages(request, response);
			break;
		}
		case "/Project2/inviteUser.do":{
			WebService.inviteUser(request, response);
			break;
		}
		case "/Project2/acceptInvite.do":{
			WebService.acceptInvite(request, response);
			break;
		}
		case "/Project2/makeModerator.do":{
			WebService.makeModerator(request, response);
			break;
		}
		case "/Project2/deleteRoom.do":{
			WebService.deleteRoom(request, response);
			break;
		}
		case "/Project2/banUser.do":{
			WebService.banUser(request, response);
			break;
		}
		case "/Project2/unBanUser.do":{
			WebService.unBanUser(request, response);
			break;
		}
		case "/Project2/getBannedUsers.do":{
			WebService.getBannedUsers(request, response);
			break;
		}
		case "/Project2/populate.do":{
			Playground.populateTestDB();
			Playground.postMessages();
			break;
		}
		
		default:{
			try {
				response.getWriter().append("That is not a valid URL for this server").close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	
	}
	
	
}
