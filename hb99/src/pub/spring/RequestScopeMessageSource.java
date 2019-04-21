package pub.spring;

import java.text.Format;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.support.AbstractMessageSource;
import org.springframework.util.ObjectUtils;
 
public class RequestScopeMessageSource extends AbstractMessageSource {

	private static ThreadLocal<Map<String, String>> _messageMap = new ThreadLocal<Map<String, String>>();

	public static void setMessage(String code, String message) {
		Map<String, String> messageMap = _messageMap.get();
		if (messageMap == null) {
			messageMap = new HashMap<String, String>();
			_messageMap.set(messageMap);
		}
		messageMap.put(code, message);
	}

	public static void clear() {
		_messageMap.set(null);
	}

	@Override
	protected String getMessageInternal(String code, Object[] args, Locale locale) {
		if (code == null) {
			return null;
		}
		if (locale == null) {
			locale = Locale.getDefault();
		}
		Object[] argsToUse = args;

		if (!isAlwaysUseMessageFormat() && ObjectUtils.isEmpty(args)) { 
			String message = resolveCodeWithoutArguments(code, locale);
			if (message != null) {
				return message;
			}
		}

		else { 
			Map<String, String> messageMap = _messageMap.get();
			if (messageMap != null && messageMap.containsKey(code)) {
				String message = messageMap.get(code);
				return formatMessage(message, args, locale);
			}
		} 
		return getMessageFromParent(code, argsToUse, locale);
	}
 
	@Override
	protected String formatMessage(String message, Object[] args, Locale locale) {
		Object[] argsToUse = resolveArguments(args, locale);

		Format messageFormat;
		if (argsToUse.length == 1 && argsToUse[0] instanceof Map) {
			messageFormat = new TemplatedMessageFormat(message);
			return messageFormat.format(argsToUse[0]);
		}
		else {
			messageFormat = new MessageFormat(message);
			return messageFormat.format(argsToUse);
		}
	}

	@Override
	protected MessageFormat resolveCode(String code, Locale locale) {
		return null;
	}
}
