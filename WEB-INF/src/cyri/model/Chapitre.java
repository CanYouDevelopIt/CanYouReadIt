package cyri.model;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Chapitre {
	
	private int id;
	private String nom;
	private String nomChapitre;
	private List<String> pages;
	
	public Chapitre(String nomManga, String _idChapitre){
		id = Integer.parseInt(_idChapitre);
		executer(nomManga);
	}
	
	public Chapitre(String nom_api, int chapterID, String chapterName) {
		nom = nom_api;
		id = chapterID;
		nomChapitre = chapterName;
		//faire plus tard
		//executer(nom_api);
	}
	
	public String getNomChapitre(){
		return nomChapitre;
	}

	public void executer(String nomManga) {
		
		try {
			HttpResponse<String> response = Unirest
					.get("https://doodle-manga-scraper.p.mashape.com/mangareader.net/manga/"
							+ nomManga + "/" + id)
					.header("X-Mashape-Key", "hTe7fsswLymshgTkf5oE5HtSUjzMp1COvL4jsngB1RpZzaloQL")
					.header("Accept", "text/plain")
					.asString();
			JSONObject obj = new JSONObject(response.getBody());
			
			//nom = obj.get("name").toString();
			
			//JSONObject P = obj.getJSONObject("pageId");
			//System.out.println(obj.get("pages").toString());
			
		} catch (UnirestException e) {
			e.printStackTrace();
		}
	}
	
	public String getNom(){
		return nom;
	}
	
}
