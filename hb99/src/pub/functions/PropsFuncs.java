package pub.functions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.oty.util.Log4jUtil;

public class PropsFuncs {

	public static final String sysPropertiesPath = new String(
			PropsFuncs.class.getResource("/").getFile() + "/sys.properties").replace("%20", " ");
	public static final String ossPropertiesPath = new String(
			PropsFuncs.class.getResource("/").getFile() + "/OSSKey.properties").replace("%20", " ");
	/** 如果获取的值不会在运行期间被修改，请使用此常量的getProperty方法获取，避免频繁IO */
	public static final Properties sysProps = new Properties();
	public static final Properties ossProps = new Properties();

	static {
		try {
			sysProps.load(new FileInputStream(sysPropertiesPath));
			ossProps.load(new FileInputStream(ossPropertiesPath));
		} catch (FileNotFoundException e) {
			Log4jUtil.error("异常信息：", e);
		} catch (IOException e) {
			Log4jUtil.error("异常信息：", e);
		}
	}

	public static String getProperty(String propertiesFileCpPath, String key) {
		InputStream is = null;
		try {
			is = PropsFuncs.class.getResource(propertiesFileCpPath).openStream();
			try {
				Properties properties = new Properties();
				properties.load(is);
				return properties.getProperty(key);
			} finally {
				is.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取本地properties文件中的实时值，适用于获取的值可能在运行期间被修改的场景
	 */
	public static String getLocalProperty(String propertiesFilePath, String key) {
		InputStream is = null;
		try {
			is = new FileInputStream(propertiesFilePath);
			try {
				Properties properties = new Properties();
				properties.load(is);
				return properties.getProperty(key);
			} finally {
				is.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 修改properties值
	 */
	public static void updateProperty(String propertiesFilePath, String key, String value) {
		InputStream is = null;
		FileOutputStream fos = null;
		try {
			is = new FileInputStream(propertiesFilePath);
			try {
				Properties properties = new Properties();
				properties.load(is);
				is.close();
				fos = new FileOutputStream(propertiesFilePath);
				properties.setProperty(key, value);
				properties.store(fos, "Update '" + key + "' value");
			} finally {
				if (fos != null) {
					fos.close();
				}
			}
		} catch (Exception e) {
			Log4jUtil.error("异常信息：", e);
		}
	}

}
