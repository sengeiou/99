package pub.types;

import java.util.List;

@SuppressWarnings("rawtypes")
public class TypeFuncs {

	public static boolean isList(Class cls) {
		for (Class itf : cls.getInterfaces()) {
			if (itf.equals(List.class)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isJavaType(Class cls) {
		return cls.getName().startsWith("java");
	}

}
