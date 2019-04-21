package pub.spring.tags;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.jsp.JspException;

import org.springframework.util.StringUtils;

import pub.types.IdText;

@SuppressWarnings({"rawtypes", "unchecked"})
public class SelectTag extends org.springframework.web.servlet.tags.form.SelectTag {

	private static final long serialVersionUID = 1L;

	private String nullItem;

	public SelectTag() {
		setItemLabel("text");
		setItemValue("id");
	}

	protected String getNullItem() {
		return nullItem;
	}
	public void setNullItem(String nullItem) {
		this.nullItem = nullItem;
	}

	@Override
	protected String getName() throws JspException { 
		return getPropertyPath();
	}

	@Override
	protected String autogenerateId() throws JspException { 
		return StringUtils.deleteAny(getPropertyPath(), "[]");
	}

	@Override
	protected Object getItems() {
		Object items = super.getItems();
		if (this.nullItem != null) {
			if (items == null || items.getClass().equals(Object.class)) {
				items = new ArrayList();
			}
			List itemList = new ArrayList((Collection) items);
			itemList.add(0, new IdText("", nullItem));
			items = itemList;
		}
		return items;
	}

	@Override
	public int doEndTag() throws JspException {
		int result = super.doEndTag();

		if (Boolean.TRUE.equals(pageContext.getAttribute("_FORM_TAG_SHOW_ERRORS"))) {
			ErrorsTag errorsTag = new ErrorsTag();
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
		return result;
	}
}
