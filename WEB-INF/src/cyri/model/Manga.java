package cyri.model;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Manga {

	private String nom;
	private int nbChapitres;
	private String auteur;

	public Manga(String _nom) {
		nom = _nom;
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
							+ nom + "/")
					.header("X-Mashape-Key",
							"pJyLx85TXymshheesKCdA5lbQyWFp1ccbrSjsnBhUdxmrrmmeb")
					.header("Accept", "text/plain").asString();
			JSONObject obj = new JSONObject(response.getBody());

			String author = obj.getString("author");
			String cover = obj.getString("cover");
			String yearOfRelease = obj.getString("yearOfRelease");
			
			System.out.println(author +" -- "+ cover +" -- "+yearOfRelease);
			
		} catch (UnirestException e) {
			e.printStackTrace();
		}
	}

}
