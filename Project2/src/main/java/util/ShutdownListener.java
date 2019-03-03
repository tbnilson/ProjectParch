package util;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ShutdownListener implements ServletContextListener {
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 * Hopefully this runs when the pizza app is rebuilt in jenkins.
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		HibernateUtil.getSessionFactory().close();
	}
}
