package cyri.model;

import java.util.ArrayList;

public class DBManga {
	
	public ArrayList<Manga> listManga = new ArrayList<Manga>();
	
	private DBManga()
	{
		listManga.add(new Manga("One Piece"));
		listManga.add(new Manga("Naruto"));
		listManga.add(new Manga("Bleach"));		
	}
	
	public ArrayList<Manga> getListManga() {
		return listManga;
	}
	
	private static DBManga INSTANCE;
 
	public static DBManga getInstance()
	{	
		if(INSTANCE == null)
			INSTANCE = new DBManga();
		
		return INSTANCE;
	}
	
}
