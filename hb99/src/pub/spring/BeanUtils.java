package pub.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

public class BeanUtils {

	public static<T> T getBean(Class<T> clazz) {
		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
		return ac.getBean(clazz);
	}

}
