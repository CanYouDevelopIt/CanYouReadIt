package cyri.action;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;

public class ActionAfficherCSS implements IAction {

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
		File ressource = new File(this.getClass()
				.getResource("/../stylesheets/styles.css").getFile());

		if (ressource.exists()) {
			Scanner scanner = null;
			try {
				scanner = new Scanner(ressource);
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					context._getResponse().getWriter().write(line+"\n");
				}

				scanner.close();

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				scanner.close();
			}

		}
	}
}
