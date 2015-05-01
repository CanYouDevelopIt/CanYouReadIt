package cyri.model;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Manga {

	private String nom;
	private String nom_api;
	private int nbChapitres;
	private String auteur;

	public Manga(String _nom) {
		nom = _nom;
		nom_api = _nom.toLowerCase().replace(" ", "-");
		executer();
	}

	public String getNom() {
		return nom;
	}

	public int getNbChapitres() {
		return nbChapitres;
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
			//String cover = (String) obj.getJSONObject("cover").get("cover");
			//String yearOfRelease = obj.getString("yearOfRelease");
			
			System.out.println(author);
			
		} catch (UnirestException e) {
			e.printStackTrace();
		}
	}

}
