package cyri.velocity;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


public class ActionVelocity implements IAction {

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

	@SuppressWarnings("deprecation")
	@Override
	public void proceed(IContext context) {
		System.out.println("Inside the Velocity");

		VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, context._getRequest().getRealPath("/").replace("\\", "/")+"WEB-INF/template");
        
        ve.init();
		
		VelocityContext vcontext = new VelocityContext();
		List<String> cities = new ArrayList<String>();
		cities.add("paris");
		cities.add("londres");
		cities.add("chicago");
		cities.add("tokyo");

		vcontext.put("name", "Velocityyy");
		vcontext.put("cities", cities);
		
		Template t = null;

		try {
			t = ve.getTemplate("body.vm");
						
			StringWriter sw = new StringWriter();
			t.merge(vcontext, sw);
			
			context._getResponse().getWriter().write(sw.toString());
		} catch(Exception e) {
			System.out.println("There is shit over here ==> "+e.getMessage());
			e.printStackTrace();
		}
		
	}

}
