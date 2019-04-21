package pub.beans;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.converters.BooleanConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;

import pub.beans.converter.DateConverter;
 
public class Binder {

	public static final BeanUtilsBean beanUtilBean;

	static {
		beanUtilBean = new BeanUtilsBeanEx();
		ConvertUtilsBean convertUtils = beanUtilBean.getConvertUtils();
		convertUtils.register(new DateConverter(), Date.class);
		convertUtils.register(new BooleanConverter(null), Boolean.class);
		convertUtils.register(new LongConverter(null), Long.class);
		convertUtils.register(new IntegerConverter(null), Integer.class);
		convertUtils.register(new DoubleConverter(null), Double.class);
	}

	@SuppressWarnings("rawtypes")
	public static void bind(Object bean, Map map)
			throws InvocationTargetException, IllegalAccessException {

		beanUtilBean.populate(bean, map); 
	}

}
