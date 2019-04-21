package pub.spring;

import java.lang.reflect.InvocationTargetException;

import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.remoting.support.RemoteInvocation;
 
public class StatefulRmiServiceExporter extends RmiServiceExporter {

	protected Object invoke(RemoteInvocation invocation, Object targetObject) throws
																			  NoSuchMethodException,
																			  IllegalAccessException,
																			  InvocationTargetException {
		String methodName = invocation.getMethodName();
		int pos = methodName.indexOf('|'); 
		methodName = methodName.substring(0, pos);
		invocation.setMethodName(methodName);
		return super.invoke(invocation, targetObject);
	}
}
