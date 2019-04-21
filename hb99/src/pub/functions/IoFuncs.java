package pub.functions;

import java.io.Closeable;
import java.io.IOException;
 
public class IoFuncs {

	public static void tryClose(Closeable closeable) {
		if (closeable == null) {
			return;
		}
		try {
			closeable.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
