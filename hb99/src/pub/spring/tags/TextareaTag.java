package pub.spring.tags;

import javax.servlet.jsp.JspException;

import org.springframework.util.StringUtils;
 
public class TextareaTag extends org.springframework.web.servlet.tags.form.TextareaTag {
 
	private static final long serialVersionUID = -7480522947637890083L;
	@SuppressWarnings("unused")
	private ErrorsTag errorsTag;

	@Override
	protected String getName() throws JspException {
		return getPropertyPath();
	}

	@Override
	protected String autogenerateId() throws JspException {
		//return super.autogenerateId();
		return StringUtils.deleteAny(getPropertyPath(), "[]");
	}

}
