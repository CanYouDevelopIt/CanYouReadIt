package cyri.action;

import java.io.IOException;
import java.util.ArrayList;

import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;

import cyri.model.DBManga;
import cyri.model.Manga;

public class ActionAfficherChapitres implements IAction {
	
	@Override
	public void proceed(IContext context) {
		
		DBManga instance = DBManga.getInstance();
		
		String nomManga = "";
		String [] arrayManga = (String[]) context.getParameter("nom");
		if(arrayManga != null){
			for(String s : arrayManga)
				nomManga = s;					
		}
		
		Manga m = instance.getManga(nomManga);
		
		try {
			context._getResponse().setContentType("text/html");
			context._getResponse().getOutputStream()
					.println("<html><head></head><body>");
			
			if(m != null){
				context._getResponse().getOutputStream()
				.println("<h2>" + m.getNom() + "</h2>");
				context._getResponse().getOutputStream()
						.println("<h3>Veuillez choisir un chapitre : </h3>");
				context._getResponse().getOutputStream()
				.println("<ul>");
					
					for(int i = m.getNbChapitres(); i > 0; i--){
						context._getResponse().getOutputStream()
						.println("<li>");
						context._getResponse().getOutputStream().println("<a href=\"/CanYouReadIt/chapitre?nom="+ m.getNom() + "&chapitre=" + i + "\">" + "Chapitre " + i + "</a>");
						context._getResponse().getOutputStream()
						.println("</li>");
					}
				
				context._getResponse().getOutputStream()
				.println("</ul>");
			}

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
