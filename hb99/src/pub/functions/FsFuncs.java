package pub.functions;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
 
public class FsFuncs {

	public static String getFileName(String fullFileName) {
		int pos = fullFileName.lastIndexOf('\\');
		if (pos == -1) {
			return fullFileName;
		}
		return fullFileName.substring(pos + 1);
	}

	public static void delete(File file) {
		assert file.exists();
		if (file.isDirectory()) {
			for (File child : file.listFiles()) {
				delete(child);
			}
		}
		boolean deleted = file.delete();
		assert deleted;
	}

	private static void tryClose(Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void copyFile(File fromFile, File toFile) throws Exception {
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			FileInputStream fis = new FileInputStream(fromFile);
			in = new BufferedInputStream(fis);
			FileOutputStream fos = new FileOutputStream(toFile);
			out = new BufferedOutputStream(fos);
			byte[] buf = new byte[4096];
			int cb = -1;
			while ((cb = fis.read(buf)) != -1) {
				out.write(buf, 0, cb);
			}
			out.flush();
		}
		finally {
			tryClose(out);
			tryClose(in);
		}
	}
}
