package cyri.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Manga {

	private String nom;
	private String nom_api;
	private int nbChapitres;
	private String image;
	private List<Chapitre> chapitres;

	public Manga(String _nom) {
		nom = _nom;
		nom_api = _nom.toLowerCase().replace(" ", "-");
		chapitres = new ArrayList<Chapitre>();
		executer();
	}

	public String getNom() {
		return nom;
	}

	public int getNbChapitres() {
		return nbChapitres;
	}
	
	public Chapitre chargerChapitre(String idChapitre){
		Chapitre c = new Chapitre(nom_api,idChapitre);
		
		if(c != null)
			chapitres.add(c);
		
		return c;
	}
	
	public void executer() {
		
		try {
			HttpResponse<String> response = Unirest
					.get("https://doodle-manga-scraper.p.mashape.com/mangareader.net/manga/"
							+ nom_api + "/")
					.header("X-Mashape-Key",
							"pJyLx85TXymshheesKCdA5lbQyWFp1ccbrSjsnBhUdxmrrmmeb")
					.header("Accept", "text/plain").asString();
			JSONObject obj = new JSONObject(response.getBody());

			String author = (String) obj.get("author").toString();
			image = (String) obj.get("cover").toString();
			nbChapitres = obj.getJSONArray("chapters").length();			
			
		} catch (UnirestException e) {
			e.printStackTrace();
		}
	}

}
