package cyri.action;

import java.io.IOException;

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
		
		String [] arrayManga = (String[]) context.getParameter("nom");
		String [] arrayChapitre = (String[]) context.getParameter("chapitre");
		
		if(arrayManga != null){
			for(String s : arrayManga)
				nomManga = s;					
		}
		
		if(arrayChapitre != null){
			for(String s : arrayChapitre)
				chapitre = s;					
		}
		
		Manga m = instance.getManga(nomManga);
		Chapitre c = m.chargerChapitre(chapitre);
		
		try {
			context._getResponse().setContentType("text/html");
			context._getResponse().getOutputStream()
					.println("<html><head></head><body>");
			
			if(m != null){
				context._getResponse().getOutputStream()
				.println("<h3>" + m.getNom() + " : " + c.getNom() +" (" + chapitre + ")</h3>");
			}

			context._getResponse().getOutputStream().println("</body>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
