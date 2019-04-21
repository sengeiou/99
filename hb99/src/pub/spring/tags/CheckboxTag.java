package pub.spring.tags;

import javax.servlet.jsp.JspException;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.servlet.tags.form.TagWriter;
 
public class CheckboxTag extends org.springframework.web.servlet.tags.form.CheckboxTag {

	private static final long serialVersionUID = 1L;

	private Object uncheckedValue;

	public Object getUncheckedValue() {
		return uncheckedValue;
	}
	public void setUncheckedValue(Object uncheckedValue) {
		this.uncheckedValue = uncheckedValue;
	}

	@Override
	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		if (uncheckedValue != null) {
			tagWriter.startTag("input");
			tagWriter.writeAttribute("type", "hidden");
			tagWriter.writeAttribute("name", WebDataBinder.DEFAULT_FIELD_DEFAULT_PREFIX + getName());
			tagWriter.writeAttribute("value", uncheckedValue.toString());
			tagWriter.endTag();
		}
		return super.writeTagContent(tagWriter);
	}

}
