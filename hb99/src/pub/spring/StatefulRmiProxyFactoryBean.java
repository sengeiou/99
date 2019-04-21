package pub.spring;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.support.RemoteInvocation;
 
public class StatefulRmiProxyFactoryBean extends RmiProxyFactoryBean {

	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("!");
		return super.invoke(invocation);
	}

	protected RemoteInvocation createRemoteInvocation(MethodInvocation methodInvocation) {
		RemoteInvocation result = super.createRemoteInvocation(methodInvocation); 
		result.setMethodName(result.getMethodName() + '|' + "HAHAHA!");
		return result;
	}
}
