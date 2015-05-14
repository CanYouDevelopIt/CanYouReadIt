package cyri.action;

import java.io.IOException;
import java.io.StringWriter;

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

		String nomManga = "";
		String chapitre = "";

		String[] arrayManga = (String[]) context.getParameter("nom");
		String[] arrayChapitre = (String[]) context.getParameter("chapitre");

		if (arrayManga != null) {
			for (String s : arrayManga)
				nomManga = s;
		}

		if (arrayChapitre != null) {
			for (String s : arrayChapitre)
				chapitre = s;
		}

		Manga m = instance.getManga(nomManga);
		if (m == null) {
			m = new Manga(nomManga);
		}
		Chapitre c = m.chargerChapitre(chapitre);

		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, context
				._getRequest().getRealPath("/").replace("\\", "/")
				+ "WEB-INF/template");
		ve.init();

		VelocityContext mangaContext = new VelocityContext();
		mangaContext.put("chapitrePages", c.getPages());

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
