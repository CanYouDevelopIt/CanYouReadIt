package cyri.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;

public class ActionAfficherImage implements IAction {

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
		// ceci retourne un string commençant par "/"
		String url = context._getRequest().getRequestURI();
		url = url.substring(url.lastIndexOf("/"));
		File ressource = new File(this.getClass()
				.getResource("/../stylesheets"+url).getFile());

		if (ressource.exists()) {
			BufferedImage bi = null;
			OutputStream out;
			try {
				bi = ImageIO.read(ressource);
				out = context._getResponse().getOutputStream();
				ImageIO.write(bi, "jpg", out);
			} catch (Exception e) {
				System.out.println("Error printing Error Image");
			}

		}
	}
}
