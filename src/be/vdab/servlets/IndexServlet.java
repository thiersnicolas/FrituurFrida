package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Adres;
import be.vdab.entities.Gemeente;
import be.vdab.entities.openGesloten;

/**
 * Servlet implementation class IndexServlet
 */

@WebServlet(urlPatterns = "/index.htm", name="indexservlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String VIEW = "/WEB-INF/JSP/index.jsp";
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
	throws ServletException, IOException {
		request.setAttribute("helpdesk", this.getServletContext().getInitParameter("helpdesk"));
		request.setAttribute("openGesloten", new openGesloten());
		request.setAttribute("adres", new Adres("Putstraat", "2", new Gemeente("Waasmunster", 9250)));
		request.getRequestDispatcher(VIEW).forward(request, response);
		System.out.println(request.getRequestURI());
	}
	

	/**
	  @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("<!doctype html>");
		out.println("<html lang='nl'>");
		out.println("<head>");
		out.println("<title>Frituur Fri</title>");
		out.println("<link rel='stylesheet' href='styles/default.css'/>");
		out.println("</head> <!---test--->");
		out.print("<body><h1>Frituur Frida</h1><h2>");
		int dag = LocalDateTime.now().getDayOfWeek().getValue();
		out.print(dag == (1|4)? "Vandaag zijn we gesloten": "Vandaag zijn we open");
		out.println("</h1></body></html>");
	}
	**/

}
