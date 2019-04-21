package pub.functions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ColFuncs {

	public static <T> boolean notEmpty(List<T> list) {
		return list != null && list.size() > 0;
	}

	public static <T> boolean notEmpty(T[] arr) {
		return arr != null && arr.length > 0;
	}

	public static <T> List<T> toList(T[] arr) {
		return Arrays.asList(arr);
	}

	public static Integer[] StringArrayToIntegerArray(String[] arr) {
		if (arr == null) {
			return new Integer[0];
		}
		Integer[] result = new Integer[arr.length];
		for (int n = 0; n < arr.length; n++) {
			result[n] = Integer.valueOf(arr[n]);
		}
		return result;
	}

	public static <T> T[] compactArray(T[] arr) {
		List<T> list = new ArrayList<T>();
		for (T t : arr) {
			if (t != null) {
				list.add(t);
			}
		}
		if (list.size() == arr.length) {
			return arr;
		}
		arr = (T[]) Array.newInstance(arr.getClass().getComponentType(), 0);
		return list.toArray(arr);
	}

	public static <T> boolean isLast(T item, T[] items) {
		return items[items.length - 1] == item;
	}

	public static <T> boolean isEmpty(T[] arr) {
		return !notEmpty(arr);
	}

	public static interface Distinctor<T> {
		boolean isDistinct(T prevItem, T thisItem);
	}

	public static <T> void distinct(List<T> data, Distinctor<T> distinctor) {
		List<Integer> indicesToRemove = new ArrayList<Integer>();
		T prevItem = null;
		for (int n = 0; n < data.size(); n++) {
			if (n > 0 && !distinctor.isDistinct(prevItem, data.get(n))) {
				indicesToRemove.add(n);
			}
			prevItem = data.get(n);
		}
		for (int n = indicesToRemove.size() - 1; n >= 0; n--) {
			data.remove((int) indicesToRemove.get(n));
		}
	}
 
	public static boolean contains(Collection c, Object item) {
		return c.contains(item);
	}
	
}
