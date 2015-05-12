package cyri.action;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.AbstractContext;
import org.apache.velocity.runtime.RuntimeConstants;
import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;

import cyri.model.Manga;
import cyri.model.DBManga;

public class ActionAfficherMangas implements IAction {
	
	@Override
	public void proceed(IContext context) {
		
		DBManga instance = DBManga.getInstance();
		

		VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, context._getRequest().getRealPath("/").replace("\\", "/")+"WEB-INF/template");
        ve.init();
        
		ArrayList mangaList = new ArrayList();
		ArrayList<Manga> listMangas = instance.getListManga();
		for(Manga m : listMangas)
		{
			Map map = new HashMap<>();
			map.put("name", m.getNom());
			map.put("urlName", m.getNom().replaceAll(" ", "%20"));
			mangaList.add(map);
		}
        
        VelocityContext mangaContext = new VelocityContext();
        mangaContext.put("mangaList", mangaList);
         
         Template t = ve.getTemplate( "AfficherListMangas.vm" );
         StringWriter writer = new StringWriter();
         t.merge( mangaContext, writer );
         try {
			context._getResponse().getWriter().write(writer.toString());
		} catch (IOException e) {
			System.out.println("Erreur lors de l'écriture sur la Page Web");
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
