package pub.spring.tags;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.tags.form.TagWriter;

public class InputTag extends org.springframework.web.servlet.tags.form.InputTag {

	private static final long serialVersionUID = 1L;

	private ErrorsTag errorsTag;

	private Object format;

	public Object getFormat() {
		return format;
	}
	public void setFormat(Object format) {
		this.format = format;
	}

	@Override
	protected String getName() throws JspException {
		//return super.getName();
		return getPropertyPath();
	}

	@Override
	protected String autogenerateId() throws JspException {
		//return super.autogenerateId();
		return StringUtils.deleteAny(getPropertyPath(), "[]");
	}

	@Override
	protected void writeValue(TagWriter tagWriter) throws JspException {
		Object value = getBindStatus().getActualValue();
		Format fmt = null;
		if (value instanceof String || value == null) {
			// nothing to do
		}
		else if (format == null) {
			if (value instanceof Number) {
				DecimalFormat numberFmt = new DecimalFormat();
				numberFmt.setMinimumFractionDigits(0);
				numberFmt.setGroupingUsed(false);
				fmt = numberFmt;
			}
		}
		else if (format instanceof Format) {
			fmt = (Format) format;
		}
		else if (format instanceof String) {
			if (value instanceof Date) {
				fmt = new SimpleDateFormat((String) format);
			}
			else if (value instanceof Number) {
				DecimalFormat numberFmt = new DecimalFormat((String) format);
				fmt = numberFmt;
			}
			else {
				//???
			}
		}
		if (fmt == null) {
			value = getBoundValue();
		}
		else {
			value = fmt.format(value);
		}
		tagWriter.writeAttribute("value", getDisplayString(value, getPropertyEditor()));
	}

	@Override
	public int doEndTag() throws JspException {
		int result = super.doEndTag();

		if (Boolean.TRUE.equals(pageContext.getAttribute("_FORM_TAG_SHOW_ERRORS"))) {
			if (errorsTag == null) {
				errorsTag = new ErrorsTag();
			}
			errorsTag.setPageContext(this.pageContext);
			errorsTag.setParent(getParent());
			errorsTag.setPath(getPath());
			try {
				errorsTag.doStartTag();
				errorsTag.doEndTag();
			}
			finally {
				errorsTag.doFinally();
			}
		}
		//
		return result;
	}
}
