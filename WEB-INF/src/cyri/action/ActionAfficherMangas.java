package cyri.action;

import java.io.IOException;
import java.util.ArrayList;

import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;

import cyri.model.Manga;
import cyri.model.DBManga;

public class ActionAfficherMangas implements IAction {
	
	@Override
	public void proceed(IContext context) {
		
		DBManga instance = DBManga.getInstance();
		
		try {
			context._getResponse().setContentType("text/html");
			context._getResponse().getOutputStream()
					.println("<html><head></head><body>");
			context._getResponse().getOutputStream()
			.println("<h2>Can You Read It ?</h2>");
			context._getResponse().getOutputStream()
					.println("<h3>Veuillez choisir un manga : </h3>");
			
			context._getResponse().getOutputStream()
			.println("<ul>");
			
			ArrayList<Manga> listMangas = instance.getListManga();
			for(Manga m : listMangas)
			{		
				context._getResponse().getOutputStream()
				.println("<li>");
					context._getResponse().getOutputStream().println("<a href=\"/CanYouReadIt/manga?nom="+ m.getNom() +"\">" + m.getNom() + "</a>");
				context._getResponse().getOutputStream()
				.println("</li>");
			}
			
			context._getResponse().getOutputStream()
			.println("</ul>");
			
			context._getResponse().getOutputStream().println("</body>");
		} catch (IOException e) {
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
