package cyri.action;

import java.io.IOException;

import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;

public class Action implements IAction {


	@Override
	public void proceed(IContext context) {
		System.out.println("Action Proceed a+");
		try {
			context._getResponse().setContentType("text/html");
			context._getResponse().getOutputStream()
					.println("<html><head></head><body>");
			context._getResponse().getOutputStream()
					.println("<h1>Regex a+ reconnue</h1>");
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
