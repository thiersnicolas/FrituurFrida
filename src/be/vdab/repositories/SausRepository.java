package be.vdab.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import be.vdab.entities.Saus;

public class SausRepository {
	static private Map<Long, Saus> SAUZEN = new ConcurrentHashMap<>();

	static {
		SAUZEN.put(3L, new Saus(3L, "cocktail", Arrays.asList("mayonaise", "ketchup", "cognac")));
		SAUZEN.put(6L, new Saus(6L, "mayonaise", Arrays.asList("ei", "mosterd")));
		SAUZEN.put(7L, new Saus(7L, "mosterd", Arrays.asList("mosterd", "azijn", "witte wijn")));
		SAUZEN.put(12L, new Saus(12L, "tartare", Arrays.asList("mayonaise", "augurk", "tabasco")));
		SAUZEN.put(44L, new Saus(44L, "vinaigrette", Arrays.asList("olijfolie", "mosterd", "azijn")));
	}

	public List<Saus> findAll() {
		return new ArrayList<>(SAUZEN.values());
	}

	public List<String> findIngredients() {
		List<Saus> sauzen = new ArrayList<>(SAUZEN.values());
		List<String> ingredients = new ArrayList<>();
		for (Saus saus : sauzen) {
			for (String ingredient : saus.getIngredienten()) {
				if (ingredients.indexOf(ingredient) == -1) {
					ingredients.add(ingredient);
				}
			}
		}
		return ingredients;
	}

	public List<Saus> bevatIngredient(String ingredient) {
		List<Saus> sauzen = new ArrayList<>(SAUZEN.values());
		List<Saus> sauzenMetIngredient = new ArrayList<>();
		for (Saus saus : sauzen) {
			if (saus.getIngredienten().indexOf(ingredient) != -1) {
				sauzenMetIngredient.add(saus);
			}
		}
		return sauzenMetIngredient;
	}

	public void deleteSauzen(String[] teVerwijderenIds) {
		List<Saus> sauzen = new ArrayList<>(SAUZEN.values());
		for (int i = 0; i < teVerwijderenIds.length; i++) {
			for (Saus saus : sauzen) {
				if (saus.getId() == Long.parseLong(teVerwijderenIds[i])) {
					SAUZEN.remove(Long.parseLong(teVerwijderenIds[i]));
					System.out.println(saus.getId() + " verwijderd");
				}
			}
		}
	}
	
	public List<String> sauzenNamen(){
		List<Saus> sauzen = new ArrayList<>(SAUZEN.values());
		List<String> sauzenNamen = new ArrayList<>();
		for (Saus saus: sauzen) {
			sauzenNamen.add(saus.getNaam());
		}
		return sauzenNamen;
	}
}
