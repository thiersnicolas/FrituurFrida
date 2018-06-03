package be.vdab.servlets;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.repositories.SausRepository;


@WebServlet("/voorkeursauzen")
public class VoorkeurSauzen extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String VIEW = "/WEB-INF/JSP/voorkeursauzen.jsp";
	private final transient SausRepository sausRepository = new SausRepository();
	private static final String VOORKEUR_REQUESTS = "voorkeurRequests";

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("ingredient") != null) {
			request.setAttribute("sauzenMetIngredient", sausRepository.bevatIngredient(request.getParameter("ingredient")));
			}
		else {
			if (((AtomicInteger) this.getServletContext().getAttribute(VOORKEUR_REQUESTS)).get() != 0) {
				request.setAttribute("fouten", "Verplicht");
			}
		}
		((AtomicInteger) this.getServletContext().getAttribute(VOORKEUR_REQUESTS)).incrementAndGet();
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
	@Override
	public void init() throws ServletException {
		this.getServletContext().setAttribute(VOORKEUR_REQUESTS, new AtomicInteger());
	}
	
	@Resource(name=SausRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		sausRepository.setDataSource(dataSource);
	}
	

}
