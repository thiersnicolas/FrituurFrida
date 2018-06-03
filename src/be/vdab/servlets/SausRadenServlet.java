package be.vdab.servlets;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import be.vdab.repositories.SausRepository;

/**
 * Servlet implementation class SausRadenServlet
 */
@WebServlet("/sausraden")
public class SausRadenServlet extends HttpServlet implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/sausraden.jsp";
	//private final SausRepository sausRepository = new SausRepository();
	private int aantalFout;
	private char[] geraden;
	private String saus;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			//String saus = (String) session.getAttribute("saus");
			geraden = (char[]) session.getAttribute("geraden");
			aantalFout = (int) session.getAttribute("aantalFout");
			saus = (String) session.getAttribute("saus");
			request.setAttribute("geraden", geraden);
			request.setAttribute("aantalFout", aantalFout);
			System.out.println(session.getAttribute("saus"));
			System.out.println(session.getAttribute("aantalFout"));
			System.out.println(session.getAttribute("geraden").toString());
			if ((boolean) session.getAttribute("gewonnen")) {
				request.setAttribute("gewonnen", true);
				request.setAttribute("saus", saus);
			} else request.setAttribute("gewonnen", false);
			if (aantalFout >= 10) {
				request.setAttribute("verloren", true);
				request.setAttribute("saus", saus);
			}
		}
		System.out.println(request.getRequestURI().split(";")[0]);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if (request.getParameter("nieuwSpel") != null) {
			aantalFout = 0;
			//saus = sausRepository.sauzenNamen().get((int)(Math.random()*(sausRepository.sauzenNamen().size())));
			saus = "nicolas loves liese";
			geraden = new char[saus.length()];
			for (int i=0; i<saus.length(); i++) {
				if (saus.charAt(i) == ' ') {
					geraden[i]=' ';
				} else {
				geraden[i] = '.';
				}
			}
			session.setAttribute("geraden", geraden);
			session.setAttribute("aantalFout", aantalFout);
			session.setAttribute("saus", saus);
			session.setAttribute("gewonnen", false);
			
		}
		if (request.getParameterValues("raden") != null) {
			char letter = request.getParameterValues("raden")[0].charAt(0);
			System.out.println("getPV: " + request.getParameterValues("raden")[0]);
			if (saus.contains(request.getParameterValues("raden")[0])) {
				for (int i=0; i<saus.length(); i++) {
					if (saus.charAt(i) == letter) {
						geraden[i] = letter;
					}
				}
				System.out.println("Array geraden: " + String.valueOf(geraden));
				session.setAttribute("geraden", geraden);
			} else {
				aantalFout++;
				session.setAttribute("aantalFout", aantalFout);
			}
			if (String.valueOf(geraden).indexOf('.') == -1) {
				session.setAttribute("gewonnen", true);
				System.out.println("gewonnen true gezet");
			}
		}
		response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
		
	}

}
