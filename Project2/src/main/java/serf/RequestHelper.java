package serf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {

	
	
	public static void Process(HttpServletRequest request, HttpServletResponse response) {
		String uri = request.getRequestURI();
		
		switch (uri) {
		case "/MasterServlet/getUsers.do":{

			break;
		}
		case "/MasterServlet/getRooms.do":{

			break;
		}
		case "/MasterServlet/login.do":{

			break;
		}
		case "/MasterServlet/postMessage.do":{

			break;
		}
		case "/MasterServlet/editMessage.do":{

			break;
		}
		case "/MasterServlet/deleteMessage.do":{

			break;
		}
		case "/MasterServlet/registerUser.do":{

			break;
		}
		case "/MasterServlet/createRoom.do":{

			break;
		}
		}
	
	}
	
	
}
