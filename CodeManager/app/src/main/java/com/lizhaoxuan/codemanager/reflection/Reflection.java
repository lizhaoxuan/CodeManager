package com.lizhaoxuan.codemanager.reflection;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
/**
 * Created by lizhaoxuan on 16/3/7.
 */
public class Reflection {

    private Reflection(){}

    /**
     * 判断是否序列化
     *
     * @param f
     *            类的域/变量
     * @return 是否序列化，f为null时，返回false。
     */
    public static boolean isSerializable(Field f) {
        if (f == null) {
            return false;
        }
        Class<?>[] cls = f.getType().getInterfaces();
        for (Class<?> c : cls) {
            if (Serializable.class == c) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是静态常量或者内部结构属性
     *
     * @param f
     *            类的域/变量
     * @return 是否是静态常量 f为null时，返回false
     */
    public static boolean isInvalid(Field f) {
        if (f == null) {
            return false;
        }
        return (Modifier.isStatic(f.getModifiers()) && Modifier.isFinal(f
                .getModifiers())) || f.isSynthetic();
    }

    /**
     * 判断是否是Long类型
     *
     * @param field
     *            类的域/变量
     * @return 是否是Long类型 f为null时，返回false
     */
    public static boolean isLong(Field field) {
        if (field == null) {
            return false;
        }
        return field.getType() == long.class || field.getType() == Long.class;
    }

    /**
     * 判断是否是Integer类型
     *
     * @param field
     *            类的域/变量
     * @return 是否是Integer类型 f为null时，返回false
     */
    public static boolean isInteger(Field field) {
        if (field == null) {
            return false;
        }
        return field.getType() == int.class || field.getType() == Integer.class;
    }

    /**
     * 设置域的值
     *
     * @param f
     *            类的域/变量
     * @param obj
     *            对象名
     * @return 设置的值
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static Object setFieldValue(Field f, Object obj, Object value)
            throws IllegalArgumentException, IllegalAccessException {
        f.setAccessible(true);
        f.set(obj, value);
        return f.get(obj);
    }

    /**
     * 获取域的值
     *
     * @param f
     *            类的域/变量
     * @param obj
     *            对象名
     * @return 域的值
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static Object getFieldValue(Field f, Object obj)
            throws IllegalArgumentException, IllegalAccessException {
        f.setAccessible(true);
        return f.get(obj);
    }

    /**
     * 获取域的泛型类型，如果不带泛型返回null
     *
     * @param f
     *            类的域/变量
     * @return 域的泛型类型，如果不带泛型返回null，f为null 返回null
     */
    public static Class<?> getGenericType(Field f) {
        if (f == null) {
            return null;
        }
        Type type = f.getGenericType();
        if (type instanceof ParameterizedType) {
            type = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type instanceof Class<?>)
                return (Class<?>) type;
        } else if (type instanceof Class<?>)
            return (Class<?>) type;
        return null;
    }

    /**
     * 获取域的类型
     *
     * @param f
     *            类的域/变量
     * @return 返回域的类型 f为null 返回null
     */
    public static Class<?> getComponentType(Field f) {
        if (f == null) {
            return null;
        }
        return f.getType().getComponentType();
    }

    /**
     * 获取全部Field，包括父类
     *
     * @param claxx
     *            类名
     * @return Field的集合
     */
    public static List<Field> getAllDeclaredFields(Class<?> claxx) {
        // find all field.
        LinkedList<Field> fieldList = new LinkedList<Field>();
        while (claxx != null && claxx != Object.class) {
            Field[] fs = claxx.getDeclaredFields();
            for (int i = 0; i < fs.length; i++) {
                Field f = fs[i];
                if (!isInvalid(f)) {
                    fieldList.addLast(f);
                }
            }
            claxx = claxx.getSuperclass();
        }
        return fieldList;
    }

    /**
     * Bean的Java反射操作 取出Bean属性和值
     *
     * @param obj
     *            javaBean
     * @return Bean属性和值集合 l
     * @throws Exception
     */
    public static Map<Object, Object> getAllFiledValue(Object obj)
            throws Exception {
        Map<Object, Object> map = new HashMap<Object, Object>();
        Class<?> cls = obj.getClass();
        Method methods[] = cls.getDeclaredMethods();
        Field fields[] = cls.getDeclaredFields();
        for (Field field : fields) {
            String getMetName = pareGetName(field.getName());
            if (!checkMethod(methods, getMetName)) {
                continue;
            }
            Method method = cls.getMethod(getMetName);
            Object object = method.invoke(obj, new Object[] {});
            map.put(field.getName(), object);
        }
        return map;
    }

    /**
     * Bean的Java反射操作 解析以及修正对象中的值
     *
     * @param obj
     *            javaBean
     * @throws Exception
     */
    public static void parseNULL(Object obj) throws Exception {
        Map<Object, Object> map = new HashMap<Object, Object>();
        Class<?> cls = obj.getClass();
        Method methods[] = cls.getDeclaredMethods();
        Field fields[] = cls.getDeclaredFields();
        for (Field field : fields) {
            String getMetName = pareGetName(field.getName());
            if (!checkMethod(methods, getMetName)) {
                continue;
            }
            Method method = cls.getMethod(getMetName);
            Object object = correctNULL(field.getType().getSimpleName(),
                    method.invoke(obj, new Object[] {}));
            map.put(field.getName(), object);
        }
        setFieldValue(map, obj);
    }

    /**
     * Bean的Java反射操作 把为NULL的基础类型数据赋值初始值
     *
     * @param object
     */
    private static Object correctNULL(String simpleType, Object object)
            throws Exception {
        if (null == object) {
            if ("String".equals(simpleType)) {
                return "";
            } else if ("Double".equals(simpleType)) {
                return 0.0;
            } else if ("Integer".equals(simpleType)) {
                return 0;
            } else if ("Boolean".equals(simpleType)) {
                return false;
            } else if ("Long".equals(simpleType)) {
                return 0;
            } else if ("Float".equals(simpleType)) {
                return 0.0;
            }
        } else {
            if ("ArrayList".equals(simpleType) || "List".equals(simpleType)) {
                List<Object> list = (List<Object>) object;
                ListIterator<Object> sListIterator = list.listIterator();
                while (sListIterator.hasNext()) {
                    Object obj = sListIterator.next();
                    if (null == obj) {
                        sListIterator.remove();
                    } else {
                        parseNULL(obj);
                    }
                }
            } else if (simpleType.contains("Dto")) {
                parseNULL(object);
            }
        }
        return object;
    }

    /**
     * Bean的Java反射操作 设置bean 属性值
     *
     * @param map
     *            属性 和 值的集合
     * @param bean
     *            javaBean
     * @throws Exception
     */
    public static void setFieldValue(Map<Object, Object> map, Object bean)
            throws Exception {
        Class<?> cls = bean.getClass();
        Method methods[] = cls.getDeclaredMethods();
        Field fields[] = cls.getDeclaredFields();
        for (Field field : fields) {
            String fldSetName = field.getName();
            String setMethod = pareSetName(fldSetName);
            if (!checkMethod(methods, setMethod)) {
                continue;
            }
            Object value = map.get(fldSetName);
            if (null == value) {
                continue;
            }
            Method method = cls.getMethod(setMethod, field.getType());
            if (null != value) {
                method.invoke(bean, value);
            }

        }
    }

    /**
     * Bean的Java反射操作 拼接某属性get 方法
     *
     * @param fldname
     * @return 失败返回null
     */
    private static String pareGetName(String fldname) {
        if (null == fldname || "".equals(fldname)) {
            return null;
        }
        String pro = "get"
                + fldname.substring(0, 1).toUpperCase(Locale.getDefault())
                + fldname.substring(1);
        return pro;
    }

    /**
     * Bean的Java反射操作 拼接某属性set 方法
     *
     * @param fldname
     * @return 失败返回null
     */
    private static String pareSetName(String fldname) {
        if (null == fldname || "".equals(fldname)) {
            return null;
        }
        String pro = "set"
                + fldname.substring(0, 1).toUpperCase(Locale.getDefault())
                + fldname.substring(1);
        return pro;
    }

    /**
     * Bean的Java反射操作 判断该方法是否存在
     *
     * @param methods
     *            方法集合
     * @param met
     *            目标方法名
     * @return 方法不存在返回false
     */
    public static boolean checkMethod(Method methods[], String met) {
        if (null != methods && met != null) {
            for (Method method : methods) {
                if (met.equals(method.getName())) {
                    return true;
                }
            }
        }
        return false;
    }


}
