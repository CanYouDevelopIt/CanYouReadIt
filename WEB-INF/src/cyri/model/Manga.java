package cyri.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Manga {

	private String nom;
	private String nomURL;
	private String nom_api;
	private int nbChapitres;
	private String image;
	private List<Chapitre> chapitres;

	public Manga(String _nom) {
		nom = _nom;
		nom_api = _nom.toLowerCase().replace(" ", "-");
		nomURL = _nom.replaceAll(" ", "%20");
		chapitres = new ArrayList<Chapitre>();
		executer();
	}

	public String getNom() {
		return nom;
	}

	public String getNomURL() {
		return nomURL;
	}

	public int getNbChapitres() {
		return nbChapitres;
	}

	public List<Chapitre> getChapitres() {
		return chapitres;
	}

	public Chapitre chargerChapitre(String idChapitre) {
		Chapitre c = new Chapitre(nom_api, idChapitre);

		if (c != null)
			chapitres.add(c);

		return c;
	}

	public void executer() {

		try {
			HttpResponse<String> response = Unirest
					.get("https://doodle-manga-scraper.p.mashape.com/mangareader.net/manga/"
							+ nom_api + "/")
					.header("X-Mashape-Key",
							"hTe7fsswLymshgTkf5oE5HtSUjzMp1COvL4jsngB1RpZzaloQL")
					.header("Accept", "text/plain").asString();
			JSONObject obj = new JSONObject(response.getBody());
			String author = (String) obj.get("author").toString();
			image = (String) obj.get("cover").toString();
			nbChapitres = obj.getJSONArray("chapters").length();
			JSONArray allChaptres = obj.getJSONArray("chapters");

			for (int i = 0; i < allChaptres.length(); i++) {
				int chapterID = allChaptres.getJSONObject(i)
						.getInt("chapterId");
				String chapterName = allChaptres.getJSONObject(i).optString(
						"name");

				Chapitre c = new Chapitre(nom_api, chapterID, chapterName);
				chapitres.add(c);
			}
		} catch (UnirestException e) {
			e.printStackTrace();
		}
	}

	public static List<String> searchManga(String nomManga) {
		nomManga = nomManga.toLowerCase().replace(" ", "%20");
		List<String> mangas = new ArrayList<String>();
		try {
			HttpResponse<String> response = Unirest
					.get("https://doodle-manga-scraper.p.mashape.com/mangareader.net/search?q="
							+ nomManga)
					.header("X-Mashape-Key",
							"hTe7fsswLymshgTkf5oE5HtSUjzMp1COvL4jsngB1RpZzaloQL")
					.header("Accept", "text/plain").asString();

			if (!response.getBody().startsWith("{")) {
				JSONArray mangaFound = new JSONArray(response.getBody());
				for (int i = 0; i < mangaFound.length(); i++) {
					String mangaName = mangaFound.getJSONObject(i).optString(
							"mangaId");
					mangas.add(mangaName);
				}
			}

		} catch (UnirestException e) {
			e.printStackTrace();
		}
		if (mangas.size() == 0) {
			return null;
		}
		return mangas;
	}
}
