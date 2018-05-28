package be.vdab.listener;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class URLListener
 *
 */
@WebListener
public class URLListener implements ServletContextListener, ServletRequestListener {
	private static final String AANTAL_INDEX_REQUESTS = "aantalRequests";
	private static final String AANTAL_SAUSRADEN_REQUESTS = "aantalRequests";
	private static final String AANTAL_STATISTIEK_REQUESTS = "aantalRequests";
	private static final String AANTAL_VOORKEURSAUZEN_REQUESTS = "aantalRequests";

    /**
     * Default constructor. 
     */
    public URLListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent event)  { 
    	if (event.getServletContext().getAttribute(AANTAL_REQUESTS) == null) {
    		event.getServletContext().setAttribute(AANTAL_REQUESTS, new AtomicInteger());
    		((AtomicInteger) (event.getServletContext().getAttribute(AANTAL_REQUESTS))).incrementAndGet();
    	} else {
    		((AtomicInteger) (event.getServletContext().getAttribute(AANTAL_REQUESTS))).incrementAndGet();
    	}
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
    	event.getServletContext().setAttribute(AANTAL_INDEX_REQUESTS, new AtomicInteger());
    	event.getServletContext().setAttribute(AANTAL_SAUSRADEN_REQUESTS, new AtomicInteger());
    	event.getServletContext().setAttribute(AANTAL_STATISTIEK_REQUESTS, new AtomicInteger());
    	event.getServletContext().setAttribute(AANTAL_VOORKEURSAUZEN_REQUESTS, new AtomicInteger());
    }
	
}
