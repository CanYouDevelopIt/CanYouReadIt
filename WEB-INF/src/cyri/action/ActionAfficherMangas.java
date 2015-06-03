package cyri.action;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;

import cyri.database.MangaData;
import cyri.model.Manga;
import cyri.model.DBManga;

public class ActionAfficherMangas implements IAction {

	@Override
	public void proceed(IContext context) {

		DBManga instance = DBManga.getInstance();
		List<String> listMangaRechercher = null;
		String nomManga = "";
		String[] arrayManga = (String[]) context.getParameter("recherche");
		if (arrayManga != null) {
			nomManga = arrayManga[0];
		}

		if (!nomManga.isEmpty()) {
			listMangaRechercher = Manga.searchManga(nomManga);
		}

		VelocityEngine ve = new VelocityEngine();
		Properties p = new Properties();
		 String absolutePath=new File(Thread.currentThread().getContextClassLoader().getResource("").getFile()).getParentFile().getParentFile().getPath();
		 p.put("file.resource.loader.path", absolutePath+"/WEB-INF/template");
		ve.init(p);

		ArrayList<Map<String, String>> mangaList = new ArrayList<Map<String, String>>();
		ArrayList<Manga> listMangas = instance.getListManga();
		for (Manga m : listMangas) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", m.getNom());
			map.put("urlMangaName", m.getNomURL());
			mangaList.add(map);
		}

		VelocityContext mangaContext = new VelocityContext();
		mangaContext.put("mangaList", mangaList);
		mangaContext.put("mangaRechercher", nomManga);
		mangaContext.put("mangaFound", listMangaRechercher);

		Template t = ve.getTemplate("AfficherListMangas.vm");
		StringWriter writer = new StringWriter();
		t.merge(mangaContext, writer);
		try {
			context._getResponse().getWriter().write(writer.toString());
		} catch (IOException e) {
			System.out
					.println("Erreur lors de l'écriture sur la Page Web Manga");
			e.printStackTrace();
		}
		MangaData mangaData = new MangaData();
	}

	@Override
	public int setPriority(int priority) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addCredential(String role) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean needsCredentials() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasCredential(String[] roles) {
		// TODO Auto-generated method stub
		return false;
	}

}
