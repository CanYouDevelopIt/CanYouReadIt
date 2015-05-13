package cyri.action;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;

import cyri.model.Chapitre;
import cyri.model.DBManga;
import cyri.model.Manga;

public class ActionAfficherChapitres implements IAction {

	@Override
	public void proceed(IContext context) {

		DBManga instance = DBManga.getInstance();

		String nomManga = "";
		String[] arrayManga = (String[]) context.getParameter("nom");
		if (arrayManga != null) {
			for (String s : arrayManga)
				nomManga = s;
		}

		Manga m = instance.getManga(nomManga);

		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, context
				._getRequest().getRealPath("/").replace("\\", "/")
				+ "WEB-INF/template");
		ve.init();

		ArrayList chapitreList = new ArrayList();
		for (int i = m.getNbChapitres(); i > 0; i--) {
			Chapitre c = m.getChapitres().get(i-1);
			Map map = new HashMap();
			map.put("urlMangaName", m.getNomURL());
			map.put("numero", i);
			map.put("nomChapitre", c.getNomChapitre());
			chapitreList.add(map);
		}

		VelocityContext chapterContext = new VelocityContext();
		chapterContext.put("chapitreList", chapitreList);

		Template t = ve.getTemplate("AfficherListChapitres.vm");
		StringWriter writer = new StringWriter();
		t.merge(chapterContext, writer);

		try {
			context._getResponse().getWriter().write(writer.toString());
		} catch (IOException e) {
			System.out
					.println("Erreur lors de l'écriture sur la Page Web Chapitre");
			e.printStackTrace();
		}
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
