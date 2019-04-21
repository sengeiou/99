package pub.beans.converter;

import org.apache.commons.beanutils.ConvertUtilsBean;

import pub.functions.ColFuncs;
 
@SuppressWarnings("rawtypes")
public class ConvertUtilsBeanEx extends ConvertUtilsBean {
 
	public Object convert(String values[], Class clazz) {
		Object[] arr = (Object[]) super.convert(values, clazz);
		arr = ColFuncs.compactArray(arr);
		return arr;
	}
}
