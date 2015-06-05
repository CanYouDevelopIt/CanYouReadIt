package cyri.action;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
		boolean admin = instance.getAdmin();

		String[] arrayManga = (String[]) context.getParameter("nom");
		String nomManga = arrayManga[0];

		Manga m = instance.getManga(nomManga);
		if (m == null) {
			m = new Manga(nomManga);
		}

		VelocityEngine ve = new VelocityEngine();
		Properties p = new Properties();
		 String absolutePath=new File(Thread.currentThread().getContextClassLoader().getResource("").getFile()).getParentFile().getParentFile().getPath();
		 p.put("file.resource.loader.path", absolutePath+"/WEB-INF/template");
		ve.init(p);

		ArrayList chapitreList = new ArrayList();
		for (int i = m.getNbChapitres(); i > 0; i--) {
			Chapitre c = m.getChapitres().get(i-1);
			Map map = new HashMap();
			map.put("urlMangaName", m.getNomURL());
			map.put("numero", c.getId());
			map.put("nomChapitre", c.getNomChapitre());
			map.put("nomChapitreUrl", c.getNomChapitre().replaceAll(" ", "%20"));
			chapitreList.add(map);
		}

		VelocityContext chapterContext = new VelocityContext();
		chapterContext.put("chapitreList", chapitreList);
		chapterContext.put("nomManga", nomManga);
		chapterContext.put("admin", admin);

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
