package pub.spring.tags;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pub.types.IdText;

@SuppressWarnings({"rawtypes", "unchecked"})
public class CheckboxesTag extends org.springframework.web.servlet.tags.form.CheckboxesTag {

	private static final long serialVersionUID = 1L;

	private String nullItem;

	public CheckboxesTag() {
		setItemLabel("text");
		setItemValue("id");
		setDelimiter("&nbsp;");
	}

	protected String getNullItem() {
		return nullItem;
	}
	public void setNullItem(String nullItem) {
		this.nullItem = nullItem;
	}

	@Override
	protected Object getItems() {
		Object items = super.getItems();
		if (this.nullItem != null) {
			if (items == null) {
				items = new ArrayList();
			}
			List itemList = new ArrayList((Collection) items);
			itemList.add(0, new IdText("", nullItem));
			items = itemList;
		}
		return items;
	}

}
