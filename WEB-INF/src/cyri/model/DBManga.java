package cyri.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBManga {

	public ArrayList<Manga> listManga = new ArrayList<Manga>();
	private static DBManga INSTANCE;
	private String url = "jdbc:mysql://localhost:3306/cyri";
	private String user = "cyri";
	private String motDePasse = "cyri";
	Connection connexion = null;
	
	private DBManga() {
		
//		try {
//			connexion = DriverManager.getConnection(url, user, motDePasse);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		listManga.add(new Manga("Shingeki No Kyojin"));
		listManga.add(new Manga("One Piece"));
		listManga.add(new Manga("Naruto"));
		listManga.add(new Manga("Bleach"));
	}

	public ArrayList<Manga> getListManga() {
		return listManga;
	}

	public Manga getManga(String nomManga) {
		for (Manga m : listManga) {
			if (m.getNom().toLowerCase().equals(nomManga.toLowerCase()))
				return m;
		}
		return null;
	}

	public static DBManga getInstance() {
		if (INSTANCE == null)
			INSTANCE = new DBManga();

		return INSTANCE;
	}

}
