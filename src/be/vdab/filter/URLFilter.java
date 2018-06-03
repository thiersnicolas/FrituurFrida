package be.vdab.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class URLFilter
 */
@WebFilter("/*")
public class URLFilter implements Filter {
	private static final String AANTAL_REQUESTS_MAP = "aantalrequestsmap";
	private final static Set<String> UITGESLOTEN_EXTENSIES = new CopyOnWriteArraySet<>(
			Arrays.asList("jpg", "png", "gif", "css", "js", "ico"));
	private final static Set<String> BESTAANDE_PAGINAS = new CopyOnWriteArraySet<>(
			Arrays.asList("frituurfrida", "sauzen", "statistiek", "voorkeursauzen", "sausraden"));
	private FilterConfig fConfig;

    /**
     * Default constructor. 
     */
    public URLFilter() {
        // TODO Auto-generated constructor stub
    	
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		boolean urlCorrect = false;
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			String url = httpRequest.getRequestURI();
			url = url.split("\\;")[0];

			String extensie = "toegelaten";
			if (url.contains(".")) {
				extensie = url.split("\\.")[url.split("\\.").length - 1];
			}
			if (!UITGESLOTEN_EXTENSIES.contains(extensie)) {
				urlCorrect = true;
			}
			if (urlCorrect) {
				//String[] dezePaginaArray = url.split("\\/");
				String dezePagina = url.split("\\/")[url.split("\\/").length - 1];
				@SuppressWarnings("unchecked")
				ConcurrentHashMap<String, AtomicInteger> aantalRequestsMap = (ConcurrentHashMap<String, AtomicInteger>) fConfig.getServletContext().getAttribute(AANTAL_REQUESTS_MAP);
				for (String pagina : BESTAANDE_PAGINAS) {
					if (pagina.equals(dezePagina)) {
						if (aantalRequestsMap.containsKey(pagina)) {
							AtomicInteger aantalRequests = aantalRequestsMap.get(pagina);
							aantalRequests.incrementAndGet();
							aantalRequestsMap.put(pagina, aantalRequests);
						} else {
							AtomicInteger aantalRequests = new AtomicInteger(1);
							aantalRequestsMap.put(pagina, aantalRequests);
						}
						if (pagina.equals("statistiek")) {
							fConfig.getServletContext().setAttribute(AANTAL_REQUESTS_MAP, aantalRequestsMap);
						}
					}
				}
			}
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig = fConfig;
		//new ConcurrentHashMap<String, AtomicInteger> aantalRequestsMap = (ConcurrentHashMap<String, AtomicInteger>)
		fConfig.getServletContext().setAttribute(AANTAL_REQUESTS_MAP, new ConcurrentHashMap<String, AtomicInteger>());
	}

}
