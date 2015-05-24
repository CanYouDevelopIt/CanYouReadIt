package cyri.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;

public class ActionAfficherHeaderImage implements IAction {

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
		System.out.println("Je suis quelquepart içi");
		File ressource = new File(this.getClass()
				.getResource("/../stylesheets/Ace.jpg").getFile());

		if (ressource.exists()) {
			System.out.println("Image exists");
			BufferedImage bi = null;
			OutputStream out;
			try {
				bi = ImageIO.read(ressource);
				out = context._getResponse().getOutputStream();
				ImageIO.write(bi, "jpg", out);
			} catch (Exception e) {
				System.out.println("Error printing Image");
			}

		} else {
			System.out.println("Image doesn't exists");
		}
	}

}
