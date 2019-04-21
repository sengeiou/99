package pub.spring;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import org.springframework.web.servlet.mvc.multiaction.ParameterMethodNameResolver;
 
@SuppressWarnings("deprecation")
public class MethodNameResolver extends ParameterMethodNameResolver {

	private String paramNames[];

	public String[] getParamNames() {
		return paramNames;
	}
	public void setParamNames(String[] paramNames) {
		this.paramNames = paramNames;
	}

	@Override
	public String getHandlerMethodName(HttpServletRequest request) throws NoSuchRequestHandlingMethodException {

		if (paramNames != null) {
			for (String paramName : paramNames) {
				String methodName = request.getParameter(paramName);
				if (methodName != null) {
					return methodName;
				}
			}
		}
		return super.getHandlerMethodName(request);
	}
}
