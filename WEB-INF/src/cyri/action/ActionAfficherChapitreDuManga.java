package cyri.action;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
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

public class ActionAfficherChapitreDuManga implements IAction {

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

	@Override
	public void proceed(IContext context) {

		DBManga instance = DBManga.getInstance();

		String[] arrayManga = (String[]) context.getParameter("nom");
		String[] arrayChapitre = (String[]) context.getParameter("chapitre");
		String[] arrayNomChapitre = (String[]) context.getParameter("nomDuChapitre");

		String nomManga = arrayManga[0];
		String chapitre = arrayChapitre[0];
		String nomChapitre = arrayNomChapitre[0];

		if (nomChapitre == null) {
			nomChapitre = "";
		}

		Manga m = instance.getManga(nomManga);
		if (m == null) {
			m = new Manga(nomManga);
		}
		Chapitre c = m.chargerChapitre(chapitre);

		VelocityEngine ve = new VelocityEngine();
		Properties p = new Properties();
		 String absolutePath=new File(Thread.currentThread().getContextClassLoader().getResource("").getFile()).getParentFile().getParentFile().getPath();
		 p.put("file.resource.loader.path", absolutePath+"/WEB-INF/template");
		ve.init(p);

		VelocityContext mangaContext = new VelocityContext();
		mangaContext.put("chapitrePages", c.getPages());
		mangaContext.put("nomManga", nomManga);
		mangaContext.put("nomChapitre", nomChapitre.replaceAll("%20", " "));
		mangaContext.put("numeroChapitre", c.getId());

		Template t = ve.getTemplate("AfficherChapitres.vm");
		StringWriter writer = new StringWriter();
		t.merge(mangaContext, writer);

		try {
			context._getResponse().getWriter().write(writer.toString());
		} catch (IOException e) {
			System.out.println("Erreur lors de l'écriture de la Page");
			e.printStackTrace();
		}

	}

}
