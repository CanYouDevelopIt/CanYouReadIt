package cyri.model;

import java.util.ArrayList;

import cyri.database.MangaData;

public class DBManga {

	public ArrayList<Manga> listManga = new ArrayList<Manga>();
	private static DBManga INSTANCE;
	MangaData mangaData;

	private DBManga() {
		mangaData = new MangaData();
		listManga = mangaData.getMangaList();
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
