package pub.spring;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.core.type.AnnotationMetadata;

public class BeanNameGenerator extends AnnotationBeanNameGenerator {

	private static final String CONTROLLER_POSTFIX = "Action";
	private static final String UTILS_POSTFIX = "Utils";
	private String BASE_PACKAGE_NAME;


	private String convertJavaNameToUrlName(String name) {
		StringBuilder sb = new StringBuilder();
		for (int n = 0; n < name.length(); n++) {
			char c = name.charAt(n);
			if (Character.isUpperCase(c)) {
				if (n > 0) {
					sb.append('_');
				}
				c = Character.toLowerCase(c);
			}
			sb.append(c);
		}
		return sb.toString();
	}

	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		String name;
		String className = definition.getBeanClassName();

		if (className.endsWith(CONTROLLER_POSTFIX)) {

			String suffix = null;

			AnnotatedBeanDefinition annotatedDef = (AnnotatedBeanDefinition) definition;
			AnnotationMetadata amd = annotatedDef.getMetadata();

			final String controllerAnnotation = "org.springframework.stereotype.Controller";
			String controllerName = (String) amd.getAnnotationAttributes(controllerAnnotation).get("value");
			if (controllerName != null && controllerName.length() > 0) {
				// explicit specified postfix
				if (controllerName.charAt(0) == '.') {
					suffix = controllerName;
				}
				// for backword compatible
				// explicit specified struts uri
				else if (controllerName.indexOf('.') == -1) {
					return controllerName + ".do";
				}
				// explicit specified uri
				else {
					return controllerName;
				}
			}

			if (BASE_PACKAGE_NAME == null) {
				BASE_PACKAGE_NAME = className.substring(0,
					className.indexOf(".web.") + ".web".length());
			}

			int pos = className.lastIndexOf('.');
			String namePart = className.substring(pos + 1,
				className.length() - CONTROLLER_POSTFIX.length());
			namePart = convertJavaNameToUrlName(namePart);

			String packagePart = className.substring(BASE_PACKAGE_NAME.length(), pos);
			if (packagePart.indexOf('_') != -1) {
				packagePart = packagePart.replace("_.", ".");
			}
			if(packagePart.endsWith(".action")){
				packagePart = packagePart.substring(0, packagePart.length() - ".action".length());
			}

			name = packagePart + '.' + namePart;

			if (name.startsWith(".app.")) {
				name = name.substring(".app".length());
			}
		
			// postfix specified
			if (suffix != null) {
				// do nothing
			}
			// common .do actions
			else if (name.endsWith(".operate")) {
				suffix = ".do";
			}
			//accept any postfix
			else {
				suffix = ".*";
			}
			name = name.replace('.', '/') + suffix;
		}
		else if (className.endsWith(UTILS_POSTFIX)) {
			AnnotatedBeanDefinition annotatedDef = (AnnotatedBeanDefinition) definition;
			AnnotationMetadata amd = annotatedDef.getMetadata();

			final String controllerAnnotation = "org.springframework.stereotype.Component";
			String controllerName = (String) amd.getAnnotationAttributes(controllerAnnotation).get("value");
			if (controllerName == null || controllerName.length() == 0) {
				name = className;
			}
			else {
				name = controllerName;
			}
		}
		else {
			name = super.generateBeanName(definition, registry);
		}
		return name;
	}

}
