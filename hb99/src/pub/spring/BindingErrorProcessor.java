package pub.spring;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.PropertyAccessException;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DefaultBindingErrorProcessor;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import pub.types.Desc;

public class BindingErrorProcessor extends DefaultBindingErrorProcessor {

	protected Object[] getArgumentsForBindError(BindingResult bindingResult, String field, PropertyAccessException ex) {
		@SuppressWarnings("unused")
		String[] codes = new String[] {bindingResult.getObjectName() + Errors.NESTED_PATH_SEPARATOR + field, field};

		String fieldDesc = "";
		try {
			PropertyDescriptor propDesc = PropertyUtils.getPropertyDescriptor(bindingResult.getTarget(), field);
			Desc desc = propDesc.getReadMethod().getAnnotation(Desc.class);
			if (desc != null) {
				fieldDesc = desc.value();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> argumentModel = new HashMap<String, Object>();
		Map<String, Object> fieldInfo = new HashMap<String, Object>();
		fieldInfo.put("desc", fieldDesc);
		fieldInfo.put("value", ex.getValue());
		argumentModel.put("field", fieldInfo);
		Map<String, Object> ruleInfo = new HashMap<String, Object>();
		ruleInfo.put("exception", ex.getCause());
		argumentModel.put("rule", ruleInfo);
		return new Object[] {argumentModel};
	}

	@Override
	public void processPropertyAccessException(PropertyAccessException ex, BindingResult bindingResult) {
//		super.processPropertyAccessException(ex, bindingResult);
		String field = ex.getPropertyName();
		String[] codes = bindingResult.resolveMessageCodes(ex.getErrorCode(), field);
		Object[] arguments = getArgumentsForBindError(bindingResult, field, ex);
		Object rejectedValue = ex.getValue();
		if (rejectedValue != null && rejectedValue.getClass().isArray()) {
			rejectedValue = StringUtils.arrayToCommaDelimitedString(ObjectUtils.toObjectArray(rejectedValue));
		}
		bindingResult.addError(new FieldError(
				bindingResult.getObjectName(), field, rejectedValue, true,
				codes, arguments, ex.getLocalizedMessage()));
	}
}
