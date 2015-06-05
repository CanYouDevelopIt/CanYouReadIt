package cyri.action;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;

import cyri.model.DBManga;

public class ActionConnexion implements IAction {

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

		DBManga dbm = DBManga.getInstance();
		String[] arrayLogin = (String[]) context.getParameter("login");
		String[] arrayPwd = (String[]) context.getParameter("password");
		boolean admin = false;
		if (arrayLogin != null && arrayPwd != null) {
			admin = dbm.getMangaData().estAdmin(arrayLogin[0], arrayPwd[0]);
			if(admin) {
				try {
					context._getResponse().sendRedirect("./accueil");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		String[] arrayDeco = (String[]) context.getParameter("deconnexion");
		if(arrayDeco != null) {
			if(arrayDeco[0].equals("true")){
				dbm.setAdmin(false);
				try {
					context._getResponse().sendRedirect("./accueil");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		VelocityEngine ve = new VelocityEngine();
		Properties p = new Properties();
		 String absolutePath=new File(Thread.currentThread().getContextClassLoader().getResource("").getFile()).getParentFile().getParentFile().getPath();
		 p.put("file.resource.loader.path", absolutePath+"/WEB-INF/template");
		ve.init(p);
		
		VelocityContext mangaContext = new VelocityContext();

		Template t = ve.getTemplate("Connexion.vm");
		StringWriter writer = new StringWriter();
		t.merge(mangaContext, writer);
		try {
			context._getResponse().getWriter().write(writer.toString());
		} catch (IOException e) {
			System.out
					.println("Erreur lors de l'écriture sur la Page Web Connexion");
			e.printStackTrace();
		}
		
	}

}
