package cyri.dispatcher;

import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;
import org.esgi.web.framework.router.interfaces.IDispatcher;

public class Dispatcher implements IDispatcher {

	@Override
	public void dispatch(IContext context) {

		String className = context.getActionClass();

		try {
			IAction actionClass = (IAction) Class.forName(className)
					.newInstance();
			// If a class exists for the context -> proceed
			if (actionClass != null)
				actionClass.proceed(context);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NullPointerException e) {
			// Erreur de null Pointer Exception detecter dans Action Delete User
		}
	}

}
