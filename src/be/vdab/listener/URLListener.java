/*package be.vdab.listener;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Application Lifecycle Listener implementation class URLListener
 *
 */
/*
public class URLListener implements ServletContextListener, ServletRequestListener {
	private static final String AANTAL_REQUESTS_MAP = "aantalrequestsmap";
	private final static Set<String> UITGESLOTEN_EXTENSIES = new CopyOnWriteArraySet<>(
			Arrays.asList("jpg", "png", "gif", "css", "js", "ico"));
	private final static Set<String> BESTAANDE_PAGINAS = new CopyOnWriteArraySet<>(
			Arrays.asList("frituurfrida", "sauzen", "statistiek", "voorkeursauzen", "sausraden"));

	/**
	 * Default constructor.
	 */
	/*public URLListener() {

		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
	 */
	/*public void requestDestroyed(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
	 */
	/*public void requestInitialized(ServletRequestEvent event) {
		boolean urlCorrect = false;
		if (event.getServletRequest() instanceof HttpServletRequest) {
			HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
			String url = request.getRequestURI().split(";")[0];

			String extensie = "toegelaten";
			if (url.contains(".")) {
				extensie = url.split("\\.")[url.split("\\.").length - 1];
			}
			if (!UITGESLOTEN_EXTENSIES.contains(extensie)) {
				urlCorrect = true;
			}
			if (urlCorrect) {
				String[] dezePaginaArray = url.split("/");
				String dezePagina = dezePaginaArray[dezePaginaArray.length - 1];
				for (String pagina : BESTAANDE_PAGINAS) {
					@SuppressWarnings("unchecked")
					ConcurrentHashMap<String, AtomicInteger> aantalRequestsMap = (ConcurrentHashMap<String, AtomicInteger>) event.getServletContext().getAttribute(AANTAL_REQUESTS_MAP);
					if (pagina.equals(dezePagina)) {
						if (aantalRequestsMap.containsKey(pagina)) {
							AtomicInteger aantalRequests = aantalRequestsMap.get(pagina);
							aantalRequests.incrementAndGet();
							aantalRequestsMap.put(pagina, aantalRequests);
						} else {
							System.out.println("De map bevat nog geen " + pagina);
							AtomicInteger aantalRequests = new AtomicInteger(1);
							aantalRequestsMap.put(pagina, aantalRequests);
						}
						if (pagina.equals("statistiek")) {
							event.getServletContext().setAttribute(AANTAL_REQUESTS_MAP, aantalRequestsMap);
						}
					}
				}
			}
		}
	}
	
	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	/*public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	/*public void contextInitialized(ServletContextEvent event) {
		event.getServletContext().setAttribute(AANTAL_REQUESTS_MAP, new ConcurrentHashMap<String, AtomicInteger>());
	}

}*/
