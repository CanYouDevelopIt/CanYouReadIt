package cyri.context;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.esgi.web.framework.context.interfaces.IContext;
import org.esgi.web.framework.context.interfaces.IHtmlContext;

public class Context implements IContext {

	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String actionClass;

	private Map<String, String[]> properties;
	private Map<String, File> files;

	private static File tmpFolder = new File("../res");
	public static File root = new File("../res");
	
	public Context(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		this.request = request;
		this.response = response;

		init();
	}

	private void init() {
		try {

			// Normal
			properties = new HashMap<String, String[]>(
					request.getParameterMap());
			files = new HashMap<String, File>();

			// Multi-part
			if (ServletFileUpload.isMultipartContent(request)) {
				ServletFileUpload uploader = new ServletFileUpload(
						new DiskFileItemFactory());
				List<FileItem> items = uploader.parseRequest(request);

				for (FileItem item : items) {
					if (item.isFormField()) {
						// Form item
						properties.put(
								item.getFieldName(),
								appendToArray(
										properties.get(item.getFieldName()),
										item.getString()));
					} else {
						// File
						File f = new File(tmpFolder.getAbsolutePath()
								+ item.getName() + new Date());
						item.write(f);
						files.put(item.getName(), f);
					}
				}
			}

		} catch (FileUploadException e) {
			e.printStackTrace();
			} catch (Exception e) {
			e.printStackTrace();
			}
	}
	
	private String[] appendToArray(String[] tab, String toAdd) {

		if (tab == null)
			return new String[] { toAdd };

		String[] newtab = new String[tab.length + 1];
		int i = 0;

		for (; i < tab.length; i++)
			newtab[i] = tab[i];
		newtab[i] = toAdd;

		return newtab;
	}

	@Override
	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}

	@Override
	public String getActionClass() {
		return actionClass;
	}

	@Override
	public HttpServletRequest _getRequest() {
		return request;
	}

	@Override
	public HttpServletResponse _getResponse() {
		return response;
	}

	@Override
	public Object getParameter(String key) {
		return properties.get(key);
	}

	public void setParamater(String key, String[] value) {
		properties.put(key, value);
	}
	
	@Override
	public String[] getParameterKeys() {
		String[] tmp = new String[properties.keySet().size()];
		int i = 0;

		for (String key : properties.keySet()) {
			tmp[i++] = key;
		}

		return tmp;
	}

	@Override
	public File[] getUploadedFiles() {
		File[] tmp = new File[files.size()];
		int i = 0;

		for (File f : files.values()) {
			tmp[i++] = f;
		}
		return tmp;
	}


	@Override
	public void setAttribute(String key, Object value) {
		request.setAttribute(key, value);
	}

	@Override
	public Object getAttribute(String key) {
		return request.getAttribute(key);
	}

	public String getSessionAttribute(String key) {
		return (String) request.getSession().getAttribute(key);
	}
	
	public void setSessionAttribute(String key, Object value) {
		request.getSession().setAttribute(key, value);
	}


	@Override
	public String[] getUserCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean resetSession() {
		request.getSession().invalidate();
		return true;
	}

	@Override
	public IHtmlContext toHtmlContext() {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeUploadedFiles() {
		// TODO Auto-generated method stub
		
	}

}
