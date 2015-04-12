package cyri.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.esgi.web.framework.core.interfaces.IFrontController;

import cyri.context.Context;
import cyri.dispatcher.Dispatcher;
import cyri.dispatcher.Rewriter;
import cyri.dispatcher.RewriterRule;

public class FrontController extends HttpServlet implements IFrontController {

	private static final String CONTENT_TYPE = "text/html";

	static String URIroot = "/CanYouReadIt/";

	private Dispatcher dispatcher;
	private Rewriter rewriter;
	private Context c;

	public void init() {
		dispatcher = new Dispatcher();
		rewriter = new Rewriter();

		// New rule -- Pattern is a+ -- Related ActionClass is ActionA
				 rewriter.addRule(new RewriterRule("POST", URIroot + "a+",
				 "cyri.action.Action"));
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			c = new Context(request, response);
			rewriter.rewrite(c);
			dispatcher.dispatch(c);
			c.removeUploadedFiles();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		handle(request, response);

	}

}
