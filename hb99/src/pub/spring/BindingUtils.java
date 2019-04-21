package pub.spring;

import java.lang.reflect.Field;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
 
public class BindingUtils {

	public static void fixListErrorsForDeletion(String listPath, int index, Errors errors) {
		try {
			for (FieldError error : errors.getFieldErrors()) {
				Field field = FieldError.class.getDeclaredField("field");
				field.setAccessible(true);
				String fieldName = (String) field.get(error);
				String prefix = listPath + "[";
				if (fieldName.startsWith(prefix)) {
					int indexStart = prefix.length();
					int pos = fieldName.indexOf(']', indexStart);
					String sIndex = fieldName.substring(indexStart, pos);
					Integer tIndex = Integer.valueOf(sIndex);
					if (tIndex == index) {
						fieldName = fieldName.substring(0, indexStart) + "_" + fieldName.substring(pos);
						field.set(error, fieldName);
					}
					if (tIndex > index) {
						--tIndex;
						fieldName = fieldName.substring(0, indexStart) + tIndex + fieldName.substring(pos);
						field.set(error, fieldName);
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
