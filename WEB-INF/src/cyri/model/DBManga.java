package cyri.model;

import java.util.ArrayList;

public class DBManga {
	
	public ArrayList<Manga> listManga = new ArrayList<Manga>();
	
	private DBManga()
	{
		listManga.add(new Manga("one-piece"));
		listManga.add(new Manga("naruto"));
		listManga.add(new Manga("bleach"));		
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
