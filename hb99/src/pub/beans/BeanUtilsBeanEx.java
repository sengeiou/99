package pub.beans;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;

import pub.beans.converter.ConvertUtilsBeanEx;
 
public class BeanUtilsBeanEx extends BeanUtilsBean {

	public BeanUtilsBeanEx() {
		super(new ConvertUtilsBeanEx(), new PropertyUtilsBean());
	}

}
