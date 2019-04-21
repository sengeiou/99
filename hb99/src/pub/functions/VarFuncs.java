package pub.functions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;

import pub.types.IdText;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class VarFuncs {

	public static boolean equals(Object v1, Object v2) {
		if (v1 == null && v2 == null) {
			return true;
		}
		if (v1 == null) {
			if (v2 instanceof String) {
				return ((String) v2).isEmpty();
			}
			return false;
		} else if (v2 == null) {
			if (v1 instanceof String) {
				return ((String) v1).isEmpty();
			}
			return false;
		}
		if (v1.equals(v2)) {
			return true;
		}
		if (v1 instanceof Date && v2 instanceof Date) {
			return ((Date) v1).getTime() == ((Date) v2).getTime();
		}
		return false;
	}

	public static Integer toInteger(Object value) {
		return toInteger(value, null);
	}

	public static Integer toInteger(Object value, Integer defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		if (value instanceof Integer) {
			return (Integer) value;
		}
		if (value instanceof BigDecimal) {
			return toInteger((BigDecimal) value);
		}
		if (value instanceof Long) {
			return toInteger((Long) value);
		}
		if (value instanceof Double) {
			return toInteger((Double) value);
		}
		if (value instanceof String) {
			try {
				return Integer.valueOf((String) value);
			} catch (Exception e) {
				return defaultValue;
			}
		}
		return defaultValue;
	}

	public static Integer toInteger(BigDecimal value) {
		if (value == null)
			return null;
		return value.intValue();
	}

	public static Integer toInteger(Long value) {
		if (value == null)
			return null;
		return value.intValue();
	}

	public static Integer toInteger(Double value) {
		if (value == null)
			return null;
		return value.intValue();
	}

	public static <T> T nvl(T value, T nullValue) {
		return value == null ? nullValue : value;
	}

	public static String serializeToXData(Serializable obj) {
		if (obj == null) {
			return null;
		}
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			GZIPOutputStream zos = new GZIPOutputStream(bos);
			ObjectOutputStream oos = new ObjectOutputStream(zos);

			oos.writeObject(obj);
			oos.flush();
			zos.finish();
			zos.flush();

			BASE64Encoder encoder = new BASE64Encoder();
			String result = encoder.encode(bos.toByteArray());

			oos.close();
			zos.close();
			bos.close();

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Serializable deserializeXData(String str) {
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] buf = decoder.decodeBuffer(str);
			ByteArrayInputStream bis = new ByteArrayInputStream(buf);
			GZIPInputStream zis = new GZIPInputStream(bis);
			ObjectInputStream ois = new ObjectInputStream(zis);
			Object result = ois.readObject();
			ois.close();
			zis.close();
			bis.close();
			return (Serializable) result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String translate(Object value, List<IdText> idTexts) {
		String sValue = value == null ? "" : value.toString();
		for (IdText idText : idTexts) {
			if (idText.getId().equals(sValue)) {
				return idText.getText();
			}
		}
		return "";
	}

	public static Double toDouble(String value) {
		if (value == null || value.length() == 0) {
			return null;
		}
		try {
			return Double.parseDouble(value);
		} catch (Exception e) {
			return Double.NaN;
		}
	}

	public static Double toDouble(Object value, Double defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		if (value instanceof Double) {
			return (Double) value;
		}
		if (value instanceof String) {
			try {
				return Double.parseDouble((String) value);
			} catch (Exception e) {
				return defaultValue;
			}
		}
		if (value instanceof Integer) {
			return (double) (int) (Integer) value;
		}
		if (value instanceof BigDecimal) {
			return ((BigDecimal) value).doubleValue();
		}
		if (value instanceof Float) {
			return (double) (float) (Float) value;
		}
		return defaultValue;
	}

	public static Date toDate(Object value, Date defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		if (value instanceof Double) { 
			return HSSFDateUtil.getJavaDate((Double) value);
		}
		if (value instanceof Date) {
			return (Date) value;
		}
		if (value instanceof String) {
			try {
				return DateFuncs.convert((String) value);
			} catch (Exception e) {
				return defaultValue;
			}
		}
		return defaultValue;
	}
}
