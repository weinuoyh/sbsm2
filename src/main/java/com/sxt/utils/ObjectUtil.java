package com.sxt.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class ObjectUtil {

    public static Long toLong(Object o) {

        try {

            if (o == null || "".equals(o)) {
                return 0l;
            }

            if (o instanceof Long) {
                return (Long) o;
            }

            if (o instanceof String) {
                return Long.parseLong(o.toString());
            }

            if (o instanceof Double) {
                long l = Math.round((Double) o);
                return l;
            }

            if (o instanceof Float) {
                long l = Math.round((Float) o);
                return l;
            }

            if (o instanceof Integer) {
                long l = Math.round((Integer) o);
                return l;
            }

            if (o instanceof BigDecimal) {
                return Long.parseLong(o.toString());
            }

            String classname = o.getClass().getName();
            if (classname.equals("freemarker.template.SimpleScalar")) {

                if ("".equals(o.toString())) {
                    return 0l;
                } else {
                    return Long.parseLong(o.toString().replace(",", ""));
                }
            }

            return Long.parseLong(o.toString());

        } catch (Exception e) {
            System.err.println("转换为long失败：" + e.getMessage());
            return 0l;
        }
    }

    public static Double toDouble(Object o) {

        try {

            if (o == null || "".equals(o)) {
                return 0d;
            }

            if (o instanceof Double) {
                return (Double) o;
            }

            if (o instanceof String) {
                return Double.parseDouble(o.toString());
            }

            if (o instanceof Long) {
                return Double.parseDouble(o.toString());
            }

            if (o instanceof Float) {
                return Double.parseDouble(o.toString());
            }

            if (o instanceof Integer) {
                return Double.parseDouble(o.toString());
            }

            if (o instanceof BigDecimal) {
                return Double.parseDouble(o.toString());
            }

            String classname = o.getClass().getName();
            if (classname.equals("freemarker.template.SimpleScalar")) {

                if ("".equals(o.toString())) {
                    return 0d;
                } else {

                    return Double.parseDouble(o.toString().replace(",", ""));
                }
            }

            return Double.parseDouble(o.toString());

        } catch (Exception e) {
            System.err.println("转换为double失败：" + e.getMessage());
            return 0d;
        }
    }

    public static Float toFloat(Object o) {

        try {

            if (o == null || "".equals(o)) {
                return 0f;
            }

            if (o instanceof Double) {
                return ((Double) o).floatValue();
            }

            if (o instanceof String) {
                return Float.parseFloat(o.toString());
            }

            if (o instanceof Long) {
                return ((Long) o).floatValue();
            }

            if (o instanceof Float) {
                return (Float) o;
            }

            if (o instanceof Integer) {
                return ((Integer) o).floatValue();
            }

            if (o instanceof BigDecimal) {
                return Float.parseFloat(o.toString());
            }

            String classname = o.getClass().getName();
            if (classname.equals("freemarker.template.SimpleScalar")) {

                if ("".equals(o.toString())) {
                    return 0f;
                } else {
                    return Float.parseFloat(o.toString().replace(",", ""));
                }
            }

            return Float.parseFloat(o.toString());

        } catch (Exception e) {
            System.err.println("转换为Float失败：" + e.getMessage());
            return 0f;
        }
    }

    /**
     * double float 不进行四舍五入
     *
     * @param o
     * @return
     */
    public static Integer toInteger(Object o) {

        try {

            if (o == null || "".equals(o)) {
                return 0;
            }

            if (o instanceof Double) {
                return ((Double) o).intValue();
            }

            if (o instanceof String) {
                return Integer.parseInt(o.toString());
            }

            if (o instanceof Long) {
                return ((Long) o).intValue();
            }

            if (o instanceof Float) {
                return ((Float) o).intValue();
            }

            if (o instanceof Integer) {
                return (Integer) o;
            }

            if (o instanceof BigDecimal) {
                return Integer.parseInt(o.toString());
            }

            String classname = o.getClass().getName();
            if (classname.equals("freemarker.template.SimpleScalar")) {
                if ("".equals(o.toString())) {
                    return 0;
                } else {
                    return Integer.parseInt(o.toString().replace(",", ""));
                }
            }

            return Integer.parseInt(o.toString());

        } catch (Exception e) {
            System.err.println("转换为Integer失败：" + e.getMessage());
            return 0;
        }
    }

    public static String tostring(Object o) {

        try {

            if (o == null) {
                return "";
            }

            if (o instanceof Double) {
                return Double.toString((Double) o);
            }

            if (o instanceof String) {
                return o.toString();
            }

            if (o instanceof Long) {
                return Long.toString((Long) o);
            }

            if (o instanceof Float) {
                return Float.toString((Float) o);
            }

            if (o instanceof Map) {
                return o.toString();
            }

            if (o instanceof List) {
                return o.toString();
            }

            if (o instanceof Integer) {
                return Integer.toString((Integer) o);
            }

            String classname = o.getClass().getName();
            if (classname.equals("freemarker.template.SimpleScalar")) {
                return o.toString();
            }

            if (o instanceof BigDecimal) {
                return o.toString();
            }

            if (o instanceof Boolean) {

                if (Boolean.valueOf((Boolean) o)) {
                    return "true";
                } else {
                    return "false";
                }
            }

            if (o instanceof Date) {

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
                String formatStr =formatter.format((Date)o);
                return formatStr;
            }

            System.out.println(o + " 无法正常转换为String(默认调用toString（）):" + o.getClass());

            return o.toString();

        } catch (Exception e) {
            System.err.println("转换为String失败：" + e.getMessage());
            return "";
        }
    }

    /**
     * 获取属性名数组
     */
    public static List<Map<String, String>> getFiledName(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        List<Map<String, String>> fieldNames = new ArrayList<Map<String, String>>();
        for (int i = 0; i < fields.length; i++) {
            boolean isStatic = Modifier.isStatic(fields[i].getModifiers());
            if (!isStatic) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("type", fields[i].getType().getName());
                map.put("name", fields[i].getName());
                fieldNames.add(map);
            }
        }
        if (o.getClass().getSuperclass() != null && o.getClass() != Object.class) {
            getParentClassFields(fieldNames, o.getClass().getSuperclass());
        }
        return fieldNames;
    }

    /**
     * 获取类实例的父类的属性值
     *
     * @param fieldNames   类实例的属性值Map
     * @param superclass 类名
     * @return 类名.属性名=属性类型
     */
    private static void getParentClassFields(List<Map<String, String>> fieldNames, Class<?> superclass) {
        Field[] fields = superclass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            boolean isStatic = Modifier.isStatic(fields[i].getModifiers());
            if (!isStatic) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("type", fields[i].getType().getName());
                map.put("name", fields[i].getName());
                fieldNames.add(map);
            }
        }
    }

    public static Comparator<String> ASCStringComparator = new Comparator<String>() {
        public int compare(String s1, String s2) {
            if (s1 == null) {
                if (s2 == null) {
                    return 0;
                }
                return -1;
            }

            if (s2 == null) {
                return 1;
            }
            return s1.compareTo(s2);
        }
    };

    public static Comparator<String> DESCStringComparator = new Comparator<String>() {
        public int compare(String o1, String o2) {
            return ObjectUtil.ASCStringComparator.compare(o1, o2);
        }
    };

    public static boolean in(Object[] args) {
        if ((args == null) || (args.length < 2)) {
            return false;
        }
        Object arg1 = args[0];
        for (int i = 1; i < args.length; i++) {
            if (arg1 == null) {
                if (args[i] == null)
                    return true;
            } else {
                if (arg1.equals(args[i])) {
                    return true;
                }

                if ((!arg1.getClass().isArray()) && (args[i].getClass().isArray())) {
                    for (Object obj : (Object[]) args[i]) {
                        if (arg1.equals(obj)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public static boolean notIn(Object[] args) {
        return !in(args);
    }

    public static boolean empty(Object obj) {
        if (obj == null) {
            return true;
        }
        if ((obj instanceof String)) {
            return obj.equals("");
        }
        if ((obj instanceof Number)) {
            return ((Number) obj).doubleValue() == 0.0D;
        }
        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        if ((obj instanceof Collection)) {
            return ((Collection) obj).size() == 0;
        }
        return false;
    }

    public static boolean notEmpty(Object obj) {
        return !empty(obj);
    }

    public static boolean equal(Object obj1, Object obj2) {
        if (obj1 == obj2) {
            return true;
        }
        if (obj1 == null) {
            return obj2 == null;
        }

        return obj1.equals(obj2);
    }

    public static boolean notEqual(Object obj1, Object obj2) {
        return !equal(obj1, obj2);
    }

    public static Number minNumber(double[] args) {
        if ((args == null) || (args.length == 0)) {
            return null;
        }
        Number minus = null;
        double[] arrayOfDouble = args;
        int j = args.length;
        for (int i = 0; i < j; i++) {
            double t = arrayOfDouble[i];
            if (minus == null) {
                minus = Double.valueOf(t);
            } else if (minus.doubleValue() > t) {
                minus = Double.valueOf(t);
            }
        }
        return minus;
    }

    public static Number maxNumber(double[] args) {
        if ((args == null) || (args.length == 0)) {
            return null;
        }
        Number max = null;
        double[] arrayOfDouble = args;
        int j = args.length;
        for (int i = 0; i < j; i++) {
            double t = arrayOfDouble[i];
            if (max == null) {
                max = Double.valueOf(t);
            } else if (max.doubleValue() < t) {
                max = Double.valueOf(t);
            }
        }
        return max;
    }

    // 尽管Comparable是接口，但依然要用extends
    public static <T extends Comparable> T min(T[] arr) {
        T min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(min) < 0) {
                min = arr[i];
            }
        }
        return min;
    }

    public static <T extends Comparable<T>> T max(T... args) {
        if ((args == null) || (args.length == 0)) {
            return null;
        }
        T max = null;
        Comparable<T>[] arrayOfComparable = args;
        int j = args.length;
        for (int i = 0; i < j; i++) {
            T t = (T) arrayOfComparable[i];
            if ((max == null) && (t != null)) {
                max = t;
            } else if (max.compareTo(t) < 1) {
                max = t;
            }
        }
        return max;
    }

    public static <T> T ifEmpty(T obj1, T obj2) {
        return empty(obj1) ? obj2 : obj1;
    }

    public static String toString(Object obj) {
        return obj == null ? null : obj.toString();
    }

    public static <T> List<T> toList(T[] args) {
        ArrayList list = new ArrayList();
        Object[] arrayOfObject = args;
        int j = args.length;
        for (int i = 0; i < j; i++) {
            Object t = arrayOfObject[i];
            list.add(t);
        }
        return list;
    }

    public static List<String> toStringList(Collection<?> list) {
        List<String> r = new ArrayList<String>();
        for (Iterator<?> localIterator = list.iterator(); localIterator.hasNext(); ) {
            Object obj = localIterator.next();
            r.add(obj == null ? null : obj.toString());
        }
        return r;
    }

    public static List<String> toStringList(Object[] arr) {
        List<String> r = new ArrayList<String>();
        Object[] arrayOfObject = arr;
        int j = arr.length;
        for (int i = 0; i < j; i++) {
            Object obj = arrayOfObject[i];
            r.add(obj == null ? null : obj.toString());
        }
        return r;
    }

    public static Map<String, Object> toStringObjectMap(Map<?, ?> map) {
        Map<String, Object> r = new HashMap<String, Object>();
        for (Map.Entry e : map.entrySet()) {
            r.put(e.getKey() == null ? null : e.getKey().toString(), e.getValue());
        }
        return r;
    }

    public static Map<String, String> toStringStringMap(Map<?, ?> map) {
        Map<String, String> r = new HashMap<String, String>();
        for (Map.Entry e : map.entrySet()) {
            r.put(e.getKey() == null ? null : e.getKey().toString(),
                    e.getValue() == null ? null : e.getValue().toString());
        }
        return r;
    }

    public static int[] toIntArray(Object[] arr) {
        int[] r = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            r[i] = Integer.parseInt(String.valueOf(arr[i]));
        }
        return r;
    }

    public static long[] toLongArray(Object[] arr) {
        long[] r = new long[arr.length];
        for (int i = 0; i < arr.length; i++) {
            r[i] = Long.parseLong(String.valueOf(arr[i]));
        }
        return r;
    }

    public static float[] toFloatArray(Object[] arr) {
        float[] r = new float[arr.length];
        for (int i = 0; i < arr.length; i++) {
            r[i] = Float.parseFloat(String.valueOf(arr[i]));
        }
        return r;
    }

    public static double[] toDoubleArray(Object[] arr) {
        double[] r = new double[arr.length];
        for (int i = 0; i < arr.length; i++) {
            r[i] = Double.parseDouble(String.valueOf(arr[i]));
        }
        return r;
    }

    public static boolean[] toBooleanArray(Object[] arr) {
        boolean[] r = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            r[i] = Boolean.valueOf(String.valueOf(arr[i])).booleanValue();
        }
        return r;
    }

    public static String[] toStringArray(Collection<?> list) {
        String[] r = new String[list.size()];
        int i = 0;
        for (Iterator<?> localIterator = list.iterator(); localIterator.hasNext(); ) {
            Object obj = localIterator.next();
            r[(i++)] = (obj == null ? null : obj.toString());
        }
        return r;
    }

    public static String[] toStringArray(Object[] arr) {
        String[] r = new String[arr.length];
        Object[] arrayOfObject = arr;
        int j = arr.length;
        for (int i = 0; i < j; i++) {
            Object obj = arrayOfObject[i];
            r[i] = (obj == null ? null : obj.toString());
        }
        return r;
    }

    public static <T> Object[] toObjectArray(T[] arr) {
        Object[] r = new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            r[i] = arr[i];
        }
        return r;
    }

    public static Object[] toObjectArray(Object arrayObject) {
        if (arrayObject == null) {
            return null;
        }
        if (!arrayObject.getClass().isArray()) {
            throw new RuntimeException("Not an array!");
        }
        if ((arrayObject instanceof byte[])) {
            return toObjectArray((byte[]) arrayObject);
        }
        if ((arrayObject instanceof short[])) {
            return toObjectArray((short[]) arrayObject);
        }
        if ((arrayObject instanceof int[])) {
            return toObjectArray((int[]) arrayObject);
        }
        if ((arrayObject instanceof long[])) {
            return toObjectArray((long[]) arrayObject);
        }
        if ((arrayObject instanceof float[])) {
            return toObjectArray((float[]) arrayObject);
        }
        if ((arrayObject instanceof double[])) {
            return toObjectArray((double[]) arrayObject);
        }
        if ((arrayObject instanceof boolean[])) {
            return toObjectArray((boolean[]) arrayObject);
        }
        return (Object[]) arrayObject;
    }

    public static <T> T[] sort(T[] arr, Comparator<T> c) {
        if ((arr == null) || (arr.length == 0)) {
            return arr;
        }
        T[] a = (T[]) Array.newInstance(arr.getClass().getComponentType(), arr.length);
        T[] arrayOfObject1 = arr;
        int j = arr.length;
        for (int i = 0; i < j; i++) {
            T t = arrayOfObject1[i];
            a[(i++)] = t;
        }
        Arrays.sort(a, c);
        return a;
    }

    public static <T> List<T> sort(List<T> arr, Comparator<T> c) {
        if ((arr == null) || (arr.size() == 0)) {
            return arr;
        }
        try {
            T[] a = (T[]) Array.newInstance(arr.toArray().getClass().getComponentType(), arr.size());
            a = arr.toArray(a);
            Arrays.sort(a, c);

            List<T> list = arr.getClass().newInstance();
            for (T t : a) {
                list.add(t);
            }
            return list;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <K, V> Map<K, V> sort(Map<K, V> map, Comparator<Map.Entry<K, V>> c) {
        if ((map == null) || (map.size() == 0)) {
            return map;
        }
        try {
            Map.Entry<K, V>[] a = (Map.Entry[]) Array.newInstance(Map.Entry.class.getComponentType(), map.size());

            Map<K, V> map2 = map.getClass().newInstance();
            a = (Map.Entry[]) map.entrySet().toArray(a);
            Arrays.sort(a, c);
            for (Map.Entry<K, V> t : a) {
                map2.put(t.getKey(), t.getValue());
            }
            return map2;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCurrentStack() {
        StackTraceElement[] stack = new Throwable().getStackTrace();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stack.length; i++) {
            StackTraceElement ste = stack[i];
            if (ste.getClassName().indexOf("ObjectUtil.getCurrentStack") == -1) {
                sb.append("\tat ");
                sb.append(ste.getClassName());
                sb.append(".");
                sb.append(ste.getMethodName());
                sb.append("(");
                sb.append(ste.getFileName());
                sb.append(":");
                sb.append(ste.getLineNumber());
                sb.append(")\n");
            }
        }
        return sb.toString();
    }

    public static <T> T[] copyOf(T[] arr, int length) {
        T[] copy = (T[]) Array.newInstance(arr.getClass().getComponentType(), length);
        System.arraycopy(arr, 0, copy, 0, Math.min(arr.length, length));
        return copy;
    }

    public static byte[] copyOf(byte[] arr, int length) {
        byte[] copy = new byte[length];
        System.arraycopy(arr, 0, copy, 0, Math.min(arr.length, length));
        return copy;
    }

    public static boolean[] copyOf(boolean[] arr, int length) {
        boolean[] copy = new boolean[length];
        System.arraycopy(arr, 0, copy, 0, Math.min(arr.length, length));
        return copy;
    }

    public static float[] copyOf(float[] arr, int length) {
        float[] copy = new float[length];
        System.arraycopy(arr, 0, copy, 0, Math.min(arr.length, length));
        return copy;
    }

    public static int[] copyOf(int[] arr, int length) {
        int[] copy = new int[length];
        System.arraycopy(arr, 0, copy, 0, Math.min(arr.length, length));
        return copy;
    }

    public static boolean isBoolean(String v) {
        return ("true".equals(v)) || ("false".equals(v));
    }


    public static Object getValueByKey(Object obj, String key) {
        // 得到类对象
        Class userCla = (Class) obj.getClass();
        /* 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            try {

                if (f.getName().endsWith(key)) {
                    //System.out.println("单个对象的某个键的值==反射==" + f.get(obj));
                    return f.get(obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        // 没有查到时返回空字符串
        return "";
    }
}
