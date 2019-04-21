package pub.spring;

import java.text.Format;
import java.util.Locale;
import java.util.Map;

import org.commontemplate.tools.TemplateRenderer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SuppressWarnings("rawtypes")
public class TemplatedMessageSource extends ReloadableResourceBundleMessageSource {
 
	@Override
	protected String getMessageInternal(String code, Object[] args, Locale locale) {
		if (args != null && args.length == 1 && args[0] instanceof Map) {
			if (code == null) {
				return null;
			}
			if (locale == null) {
				locale = Locale.getDefault();
			}
			String messageTemplate = resolveCodeWithoutArguments(code, locale);
			if (messageTemplate != null) {
				Map model = (Map) args[0];
				TemplateRenderer templateRenderer = new TemplateRenderer(messageTemplate);
				String result = templateRenderer.putAll(model).evaluate();
				return result;
			}
			return null;
		}
		return super.getMessageInternal(code, args, locale);
	}

	@Override
	protected String formatMessage(String msg, Object[] args, Locale locale) {
		if (msg == null || ((args == null || args.length == 0))) {
			return msg;
		}
		Format format = new TemplatedMessageFormat(msg, locale);
		return format.format(resolveArguments(args, locale)[0]);
	}
	
}
