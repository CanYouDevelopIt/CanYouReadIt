package cyri.renderer;

import javax.security.auth.login.Configuration;

import org.esgi.web.framework.context.interfaces.IContext;
import org.esgi.web.framework.renderer.interfaces.IRenderer;
import org.esgi.web.framework.router.interfaces.IDispatcher;

public class Velocity implements IRenderer{
	
	private IDispatcher dispatcher;
	
	public Velocity(IDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}
	
	@Override
	public String render(IContext context) {
		
		return null;
	}

}
