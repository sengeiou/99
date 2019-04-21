package pub.spring;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Locale;
import java.util.Map;

import org.commontemplate.tools.TemplateRenderer;

@SuppressWarnings("rawtypes")
public class TemplatedMessageFormat extends Format {

	private static final long serialVersionUID = 1L;

	private String template;
	@SuppressWarnings("unused")
	private Locale locale;

	public TemplatedMessageFormat(String template) {
		this(template, Locale.getDefault());
	}

	public TemplatedMessageFormat(String template, Locale locale) {
		this.template = template;
		this.locale = locale;
	}
 
	@Override
	public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
		TemplateRenderer templateRenderer = new TemplateRenderer(template);
		String result = templateRenderer.putAll((Map) obj).evaluate();
		toAppendTo.append(result);
		return toAppendTo;
	}

	@Override
	public Object parseObject(String source, ParsePosition pos) {
		return null;
	}
}
