package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.repositories.SausRepository;

/**
 * Servlet implementation class SauzenServlet
 */
@WebServlet("/sauzen")
public class SauzenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String VIEW="WEB-INF/JSP/sauzen.jsp";
	private final SausRepository sausRepository = new SausRepository();
	private final static String REDIRECT_URL="/sauzen";   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//List<Saus> sauzen = Arrays.asList(
		//		new Saus(3L, "cocktail", Arrays.asList("mayonaise", "ketchup", "cognac")),
		//		new Saus(6L, "mayonaise", Arrays.asList("ei", "mosterd")),
		//		new Saus(7L, "mosterd", Arrays.asList("mosterd", "azijn", "witte wijn")),
		//		new Saus(12L, "tartare", Arrays.asList("mayonaise", "augurk", "tabasco")),
		//		new Saus(44L,"vinaigrette",Arrays.asList("olijfolie","mosterd","azijn")));
		//request.setAttribute("sauzen", sauzen);
		
		request.setAttribute("sauzen", sausRepository.findAll());
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] arrayIds = request.getParameterValues("verwijderen");
		for (int i=0; i< arrayIds.length; i++) {
			System.out.println(arrayIds[i]);
		}
		
		sausRepository.deleteSauzen(arrayIds);
		
		//request.setAttribute("sauzen", nieuweSauzenLijst);
		response.sendRedirect(request.getContextPath() + REDIRECT_URL);
		
		/*
		Map<String, String> fouten = new HashMap<>();
		String naam = request.getParameter("naam");
		if (!Pizza.isNaamValid(naam)) {
			fouten.put("naam", "verplicht");
		}
		String prijsString = request.getParameter("prijs");
		BigDecimal prijs = null;
		if (StringUtils.isBigDecimal(prijsString)) {
			prijs = new BigDecimal(prijsString);
			if (!Pizza.isPrijsValid(prijs)) {
				fouten.put("prijs", "tik een positief getal");
			}
		} else {
			fouten.put("prijs", "tik een getal");
		}
		Part fotoPart = request.getPart("foto");
		boolean fotoIsOpgeladen = fotoPart != null && fotoPart.getSize() !=0;
		if (fotoIsOpgeladen && ! fotoPart.getContentType().contains("jpeg")) {
			fouten.put("foto", "geen JPEG foto");
		}
		if (fouten.isEmpty()) {
			boolean pikant = "pikant".equals(request.getParameter("pikant"));
			Pizza pizza = new Pizza(naam, prijs, pikant);
			pizzaRepository.create(pizza);
			if (fotoIsOpgeladen) {
				String pizzaFotoPad = this.getServletContext().getRealPath("/pizzafotos");
				fotoPart.write(pizzaFotoPad + '/' + pizza.getId() + ".jpg");
			}
			request.setAttribute("pizzas", pizzaRepository.findAll());
			//request.getRequestDispatcher(SUCCES_VIEW).forward(request, response);
			response.sendRedirect(request.getContextPath() + REDIRECT_URL);
		} else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}*/
	}

}
