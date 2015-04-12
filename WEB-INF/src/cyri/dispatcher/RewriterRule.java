package cyri.dispatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.esgi.web.framework.context.interfaces.IContext;
import org.esgi.web.framework.router.interfaces.IRewriteRule;

import cyri.context.Context;

public class RewriterRule implements IRewriteRule {

	private Pattern regex;
	private String className;
	private String[] substitutions;
	private Matcher matcher;
	private String method;

	public RewriterRule(String _method, String regex, String className,
			String[] substitutions) {
		this.method = _method;
		this.regex = Pattern.compile(regex);
		this.className = className;
		this.substitutions = substitutions;
	}

	public RewriterRule(String _method, String regex, String className) {
		this.regex = Pattern.compile(regex);
		this.className = className;
		this.substitutions = new String[0];
		this.method = _method;
	}

	@Override
	public boolean matches(IContext context) {
		matcher = regex.matcher(context._getRequest().getRequestURI());

		// return
		// matcher.find()&&context._getRequest().getMethod().equals(this.method);
		return matcher.find();
	}

	@Override
	public void rewrite(IContext context) {
		context.setActionClass(className);

		for (int i = 0; i < substitutions.length && i < matcher.groupCount(); i++) {
			((Context) context).setParamater(substitutions[i],
					new String[] { matcher.group(i + 1) });
		}
	}

	protected boolean checkContext(IContext context) {
		return true;
	}

}
