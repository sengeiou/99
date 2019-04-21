package pub.functions;

import org.apache.commons.lang.StringUtils;

import pub.utils.Inflector;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

@SuppressWarnings({ "rawtypes" })
public class StrFuncs {

	private static Map<String, String> transformCache = new Hashtable<String, String>();

	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0xFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs;
	}

	public static String nvl(Object o) {
		return o == null ? "" : o.toString();
	}

	public static String nvl(String s) {
		return s == null ? "" : s;
	}

	public static <T> String join(T[] arr, String sep) {
		StringBuilder sb = new StringBuilder();
		int nCount = arr.length;
		for (int n = 0; n < nCount; n++) {
			sb.append(arr[n]);
			if (n < nCount - 1) {
				sb.append(sep);
			}
		}
		return sb.toString();
	}

	public static <T> String join(ArrayList<T> arr, String sep) {
		return join(arr.toArray(), sep);
	}

	public static boolean isEmpty(String s) {
		return s == null || s.length() == 0;
	}

	public static boolean notEmpty(String s) {
		return s != null && s.length() > 0;
	}

	public static Double toDouble(String s) {
		if (isEmpty(s))
			return null;
		return Double.valueOf(s);
	}

	public static BigDecimal toBitDecimal(String s) {
		if (isEmpty(s))
			return null;
		return BigDecimal.valueOf(Double.valueOf(s));
	}

	public static String valueOf(Double value) {
		if (value == null)
			return null;
		return String.valueOf(value);
	}

	public static String valueOf(BigDecimal value) {
		if (value == null)
			return null;
		return String.valueOf(value);
	}

	public static String quotedStr(String str) {
		if (str == null)
			return "''";
		return "'" + str.replace("'", "''") + "'";
	}

	public static String join(List strs, String sep) {
		String result = "";
		if (strs == null)
			return result;
		for (int n = 0; n < strs.size(); n++) {
			if (n > 0)
				result += sep;
			result += strs.get(n);
		}
		return result;
	}

	public static Integer toInteger(String s) {
		if (isEmpty(s))
			return null;
		return Integer.valueOf(s);
	}

	public static Integer toInteger(String s, int defValue) {
		try {
			return Integer.valueOf(s);
		} catch (Throwable e) {
			return defValue;
		}
	}

	public static String getCommonPrefix(String s1, String s2) {
		if (s1 == null || s2 == null)
			return "";
		StringBuilder sb = new StringBuilder("");
		for (int n = 0; n < Math.min(s1.length(), s2.length()); n++) {
			char c = s1.charAt(n);
			if (c != s2.charAt(n))
				break;
			sb.append(c);
		}
		return sb.toString();
	}

	public static Long toLong(String s) {
		if (isEmpty(s))
			return null;
		return Long.valueOf(s);
	}

	public static String valueOf(Integer value) {
		if (value == null)
			return null;
		return String.valueOf(value);
	}

	public static String valueOf(Object o) {
		if (o == null)
			return "";
		if (o instanceof Date) {
			return valueOf((Date) o);
		}
		return "" + o;
	}

	public static String valueOf(Date date) {
		if (date == null) {
			return null;
		}
		return DateFuncs.toString(date);
	}

	public static String left(String s, int cCount) {
		return s.substring(0, Math.min(s.length(), cCount));
	}

	public static boolean isAllNumber(String s) {
		return s.matches("\\d+");
	}

	public static int getByteLen(Object oText) {
		if (oText == null) {
			return 0;
		}
		String text = oText.toString();
		try {
			byte[] bts = text.getBytes("gbk");
			return bts.length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static String ellipseText(Object oText, int maxBytes) {
		if (oText == null)
			return "";
		return ellipseText(oText.toString(), maxBytes);
	}

	public static String ellipseText(String text, int maxBytes) {
		if (maxBytes >= text.length() * 2)
			return text;
		String subText = text.substring(0, Math.min(maxBytes, text.length()));
		try {
			byte[] bts = subText.getBytes("gbk");
			if (maxBytes < bts.length) { // found HZ
				String s = new String(bts, 0, maxBytes, "gbk");
				String s1 = new String(bts, 0, maxBytes + 1, "gbk");
				if (s1.length() == s.length())
					s = new String(bts, 0, maxBytes - 1);

				return s + "...";
			}
		} catch (Exception e) {
			// never fail, do nothing
		}
		if (subText.length() < text.length())
			subText += "...";
		return subText;
	}

	public static String emptyIsZero(String s) {
		return s == null ? "0" : (s.length() == 0 ? "0" : s);
	}

	public static String anyLike(String s) {
		return "%" + s + "%";
	}

	public static String rightLike(String s) {
		return s + "%";
	}

	public static String anyLikeIfNotEmpty(String s) {
		if (isEmpty(s))
			return s;
		return anyLike(s);
	}

	public static boolean equalsIgnoreNull(String s1, String s2) {
		if (s1 == null)
			s1 = "";
		if (s2 == null)
			s2 = "";
		return s1.equals(s2);
	}

	public static boolean equalsIgnoreNullAndCase(String s1, String s2) {
		if (s1 == null)
			s1 = "";
		if (s2 == null)
			s2 = "";
		return s1.equalsIgnoreCase(s2);
	}

	/**
	 * 判断字符串是否为NULL或等于某个特定值，在检测下拉框值时十分好用。 空时返回true
	 * 
	 * @param str
	 *            字符串
	 * @param empty
	 *            特定值
	 * @return true/false
	 */
	public static boolean CheckString(String str, String empty) {
		if (str == null || str.equals(empty)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 给字符串前补零
	 * 
	 * @param s
	 *            字符串
	 * @param len
	 *            位数
	 * @return 补零后的字符串
	 */
	public static String appendPreZero(String s, int len) {
		int strLen = 0;
		if (s != null) {
			strLen = s.length();
			if (strLen > 0 && strLen < len) {
				for (int i = strLen; i < len; i++) {
					s = "0" + s;
				}
			}
		}
		return s;
	}

	public static String trimEnd(String textValue, char... charsToTrim) {
		char[] chars = charsToTrim;
		if (chars.length == 0) {
			chars = new char[] { '\r', '\n', '\t', ' ' };
		}
		int len = -1;
		while (len != textValue.length() && (len = textValue.length()) > 0) {
			for (int n = 0; n < chars.length; n++) {
				if (textValue.charAt(len - 1) == chars[n]) {
					textValue = textValue.substring(0, len - 1);
					break;
				}
			}
		}
		return textValue;
	}

	public static String pluralize(String name) {
		return Inflector.getInstance().pluralize(name);
	}

	public static String lstrip(String s, char c) {
		if (s == null || s.length() == 0) {
			return s;
		}
		if (s.charAt(0) == c) {
			return s.substring(1);
		}
		return s;
	}

	public static String unCamelize(String s) {
		int firstPos = -1;
		for (int n = 0; n < s.length(); n++) {
			char c = s.charAt(n);
			if (Character.isUpperCase(c)) {
				firstPos = n;
				break;
			}
		}
		if (firstPos == -1) {
			return s;
		}
		String result = transformCache.get(s);
		if (result != null) {
			return result;
		}
		StringBuilder sb = new StringBuilder(s.substring(0, firstPos));
		for (int n = firstPos; n < s.length(); n++) {
			char c = s.charAt(n);
			if (Character.isUpperCase(c)) {
				sb.append('_');
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		result = sb.toString();
		transformCache.put(s, result);
		return result;
	}

	public static String camelize(String s) {
		int firstPos = s.indexOf('_');
		if (firstPos == -1) {
			return s;
		}
		String result = transformCache.get(s);
		if (result != null) {
			return result;
		}
		StringBuilder sb = new StringBuilder(s.substring(0, firstPos));
		boolean needUpcase = true;
		for (int n = firstPos + 1; n < s.length(); n++) {
			char c = s.charAt(n);
			if (c == '_') {
				needUpcase = true;
			} else if (needUpcase) {
				sb.append(Character.toUpperCase(c));
				needUpcase = false;
			} else {
				sb.append(c);
			}
		}
		result = sb.toString();
		transformCache.put(s, result);
		return result;
	}

	public static String trimStart(String textValue, char... charsToTrim) {
		char[] chars = charsToTrim;
		if (chars.length == 0) {
			chars = new char[] { '\r', '\n', '\t', ' ' };
		}
		int len = -1;
		while (len != textValue.length() && (len = textValue.length()) > 0) {
			for (int n = 0; n < chars.length; n++) {
				if (textValue.charAt(0) == chars[n]) {
					textValue = textValue.substring(1);
					break;
				}
			}
		}
		return textValue;
	}

	// /

	public static String getFullIndexKeyword(String str) {
		if (null == str) {
			return null;
		}

		// 去掉相应的符号
		String newStr = null;
		newStr = StringUtils.remove(str, "!");
		newStr = StringUtils.remove(newStr, "$");
		newStr = StringUtils.remove(newStr, "%");
		newStr = StringUtils.remove(newStr, "&");
		newStr = StringUtils.remove(newStr, "*");
		newStr = StringUtils.remove(newStr, "(");
		newStr = StringUtils.remove(newStr, ")");
		newStr = StringUtils.remove(newStr, "-");
		newStr = StringUtils.remove(newStr, "=");
		newStr = StringUtils.remove(newStr, "[");
		newStr = StringUtils.remove(newStr, "]");
		newStr = StringUtils.remove(newStr, "{");
		newStr = StringUtils.remove(newStr, "}");
		newStr = StringUtils.remove(newStr, ";");
		newStr = StringUtils.remove(newStr, ",");
		newStr = StringUtils.remove(newStr, ">");
		newStr = StringUtils.remove(newStr, "?");
		newStr = StringUtils.remove(newStr, "~");

		if (newStr.trim().length() == 0) {
			return null;
		}

		String[] s = newStr.toString().trim().split(" ");
		StringBuilder sb = new StringBuilder();
		for (String ss : s) {
			sb.append("/" + ss + " and ");
		}
		return sb.substring(0, sb.length() - 5).toString();
	}

	public static String idcardNumber(Object oText) {
		String subText = "";
		if (oText == null) {
			return "";
		}
		String text = oText.toString();
		if (StrFuncs.notEmpty(text)) {
			if (text.length() > 15) {
				subText = text.substring(0, 6);
				subText += "******";
				subText += text.substring(12);
			} else {
				subText = text.substring(0, text.length() - 6);
				subText += "******";
			}
		}
		return subText;
	}

	public static String toBinaryString(String n) {
		if (StrFuncs.notEmpty(n)) {
			try {
				Integer num = Integer.valueOf(n);
				String result = Integer.toBinaryString(num); 
				return result;
			} catch (Exception e) {
				return "";
			}
		}
		return "";
	}

}
