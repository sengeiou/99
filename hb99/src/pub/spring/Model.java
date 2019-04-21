package pub.spring;

import java.util.List;

import org.springframework.util.ClassUtils;
import org.springframework.validation.support.BindingAwareModelMap;

@SuppressWarnings("unchecked")
public class Model extends BindingAwareModelMap {

	private static final long serialVersionUID = 1L;

	public <T> T get(Class<T> clazz) {
		String attributeName = ClassUtils.getShortNameAsProperty(clazz);
		return (T) super.get(attributeName);
	} 

	public <T> List<T> getList(Class<T> clazz) {
		String attributeName = ClassUtils.getShortNameAsProperty(clazz) + "List";
		return (List<T>) super.get(attributeName);
	}

	public Model put(Object attributeValue) {
		addAttribute(attributeValue);
		return this;
	}

}
