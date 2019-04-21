package pub.spring.utils;

import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;

import java.util.List;
 
@SuppressWarnings({"unchecked"})
public class ModelUtils { 

	public static <T> T get(Model model, Class<T> clazz) {
		String attributeName = ClassUtils.getShortNameAsProperty(clazz);
		return (T) model.asMap().get(attributeName);
	} 

	public static <T> List<T> getList(Model model, Class<T> clazz) {
		String attributeName = ClassUtils.getShortNameAsProperty(clazz) + "List";
		return (List<T>) model.asMap().get(attributeName);
	}

	public static void put(Model model, Object attributeValue) {
		model.addAttribute(attributeValue);
	}

}
