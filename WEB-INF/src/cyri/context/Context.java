package cyri.context;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.esgi.web.framework.context.interfaces.IContext;
import org.esgi.web.framework.context.interfaces.IHtmlContext;

public class Context implements IContext {

	@Override
	public void setActionClass(String actionClass) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getActionClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpServletRequest _getRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpServletResponse _getResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getParameter(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getParameterKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File[] getUploadedFiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttribute(String key, Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getAttribute(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSessionAttribute(String key, Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getSessionAttribute(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getUserCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean resetSession() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IHtmlContext toHtmlContext() {
		// TODO Auto-generated method stub
		return null;
	}

}
