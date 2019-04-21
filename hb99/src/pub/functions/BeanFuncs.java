package pub.functions;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.BooleanConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.lang.builder.EqualsBuilder;

import pub.types.TypeFuncs;

@SuppressWarnings({"rawtypes", "unchecked"})
public class BeanFuncs {
	private static final BeanUtilsBean beanUtilBean = new BeanUtilsBean();
	static { 
		ConvertUtilsBean convertUtils = beanUtilBean.getConvertUtils();
		convertUtils.register(new Converter(){
			public Object convert(Class type, Object value) {	
				return DateFuncs.valueOf(value);
			}
		}, Date.class);
		convertUtils.register(new BooleanConverter(null), Boolean.class);
		convertUtils.register(new LongConverter(null), Long.class);
		convertUtils.register(new IntegerConverter(null), Integer.class);
		convertUtils.register(new DoubleConverter(null), Double.class);
	}
	
	public static <T> T clone(T obj) {
		try { 
			T result = (T) BeanUtilsBean.getInstance().cloneBean(obj);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T deepClone(T t) {
		assert t instanceof Serializable : t.getClass().getName()
				+ " not serializable!";
		try {
			ByteArrayOutputStream obuf = new ByteArrayOutputStream();
			ObjectOutputStream os = new ObjectOutputStream(obuf);
			os.writeObject(t);

			ByteArrayInputStream ibuf = new ByteArrayInputStream(obuf
					.toByteArray());
			ObjectInputStream is = new ObjectInputStream(ibuf);
			T t1 = (T) is.readObject();
			return t1;
		} catch (Exception e) {
			assert false : "this should not happen";
			return null;
		}
	}

	public static void copyProperties(Object dest, Object orig,
			boolean ignoreException) {
		try {
			// BeanUtils.copyProperties(dest, orig);
			PropertyUtils.copyProperties(dest, orig);
		} catch (Exception e) {
			if (!ignoreException) {
				throw new RuntimeException(e);
			}
		}
	}
 
	public static Object dynamize(Object object) {
		if (object == null) {
			return null; // #
		}
		Class type = object.getClass();
		if (type.isEnum()) {
			return object.toString();
		}
		if (type.isPrimitive()) {
			return object; // #
		}
		if (TypeFuncs.isList(type)) {
			List result = new ArrayList();
			for (Object obj : (List) object) {
				result.add(dynamize(obj));
			}
			return result; // #
		}
		if (TypeFuncs.isJavaType(type)) {
			return object; // #
		}
		//
		Map result = new HashMap();
		PropertyDescriptor[] propDescs = PropertyUtils
				.getPropertyDescriptors(object);
		for (PropertyDescriptor propDesc : propDescs) {
			String propName = propDesc.getName();
			if (propName.equals("class") || propName.equals("declaringClass")) {
				continue;
			}
			Object propValue = null;
			try {
				propValue = propDesc.getReadMethod().invoke(object);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			result.put(propName, dynamize(propValue));
		}
		return result; // #
	}

	public static boolean equals(Object o1, Object o2) {
		return EqualsBuilder.reflectionEquals(o1, o2);
	}

	public static <T> T newInstance(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void setEmptyFieldsAsNull(Object bean) {
		try {
			PropertyDescriptor[] propDescs = PropertyUtils
					.getPropertyDescriptors(bean);
			for (PropertyDescriptor propDesc : propDescs) {
				Object value = propDesc.getReadMethod().invoke(bean);
				if ("".equals(value)) {
					propDesc.getWriteMethod().invoke(bean,
							new Object[] { null });
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void populate(Object targetBean, Map srcObj){
		try{
			beanUtilBean.populate(targetBean, srcObj);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
    public static Map<String, Object> objectToMap(Object obj) throws Exception {    
        if (obj == null) {
        	return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();   
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());    
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
        for (PropertyDescriptor property : propertyDescriptors) {    
            String key = property.getName();    
            if (key.compareToIgnoreCase("class") == 0) {  
                continue;  
            }  
            Method getter = property.getReadMethod();  
            Object value = getter != null ? getter.invoke(obj) : null;  
            map.put(key, value);  
        }
        return map;  
    }
}
