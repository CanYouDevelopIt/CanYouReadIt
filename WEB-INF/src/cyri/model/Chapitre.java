package cyri.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Chapitre {

	private int id;
	private String nom;
	private String nomChapitre;
	private List<String> pages = new ArrayList<String>();

	public Chapitre(String nomManga, String _idChapitre) {
		id = Integer.parseInt(_idChapitre);
		executer(nomManga);
	}

	public Chapitre(String nom_api, int chapterID, String chapterName) {
		nom = nom_api;
		id = chapterID;
		nomChapitre = chapterName;
	}

	public String getNomChapitre() {
		return nomChapitre;
	}

	public List<String> getPages() {
		return pages;
	}

	public void executer(String nomManga) {

		try {
			HttpResponse<String> response = Unirest
					.get("https://doodle-manga-scraper.p.mashape.com/mangareader.net/manga/"
							+ nomManga + "/" + id)
					.header("X-Mashape-Key",
							"hTe7fsswLymshgTkf5oE5HtSUjzMp1COvL4jsngB1RpZzaloQL")
					.header("Accept", "text/plain").asString();
			JSONObject obj = new JSONObject(response.getBody());

			checkName(obj);
			if (obj.toString().startsWith("{\"pages\"")) {
				JSONArray allPages = obj.getJSONArray("pages");
				for (int i = 0; i < allPages.length(); i++) {
					String pageURL = allPages.getJSONObject(i).optString("url");
					pages.add(pageURL);
				}
			}
		} catch (UnirestException e) {
			e.printStackTrace();
		}
	}

	public void checkName(JSONObject obj) {
		try {
			nom = obj.get("name").toString();
		} catch (JSONException e) {
			nom = "Nom inexistant";
			System.out.println("Cet anime n'a pas de nom : " + e.getMessage());
		}
	}

	public String getNom() {
		return nom;
	}

}
