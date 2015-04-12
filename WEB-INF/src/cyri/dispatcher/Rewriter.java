package cyri.dispatcher;

import java.util.ArrayList;

import org.esgi.web.framework.context.interfaces.IContext;
import org.esgi.web.framework.router.interfaces.IRewriteRule;
import org.esgi.web.framework.router.interfaces.IRewriter;

public class Rewriter implements IRewriter {

	private ArrayList<IRewriteRule> rules;

	public Rewriter() {
		init();
	}

	public void init() {
		rules = new ArrayList<IRewriteRule>();
	}

	@Override
	public void addRule(IRewriteRule rule) {
		rules.add(rule);
	}

	@Override
	public void rewrite(IContext request) {
		for (IRewriteRule rule : rules) {
			if (rule.matches(request)) {
				rule.rewrite(request);
				break;
			}
		}
	}

}
