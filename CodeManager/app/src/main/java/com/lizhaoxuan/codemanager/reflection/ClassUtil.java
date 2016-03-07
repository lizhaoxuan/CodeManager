package com.lizhaoxuan.codemanager.reflection;

import java.util.Collection;
import java.util.Date;

/**
 * Java类型操作工具
 */
public class ClassUtil {

	private static final String TAG = ClassUtil.class.getSimpleName();

	/**
	 * 判断类是否是基础数据类型(目前支持11种)
	 * 
	 * @param clazz
	 *            类型
	 * @return 失败及不满足条件返回false,是基础类型返回true;
	 */
	public static boolean isBaseDataType(Class<?> clazz) {
		if (null == clazz)
			return false;
		return clazz.isPrimitive() || clazz.equals(String.class)
				|| clazz.equals(Boolean.class) || clazz.equals(Integer.class)
				|| clazz.equals(Long.class) || clazz.equals(Float.class)
				|| clazz.equals(Double.class) || clazz.equals(Byte.class)
				|| clazz.equals(Character.class) || clazz.equals(Short.class)
				|| clazz.equals(Date.class) || clazz.equals(byte[].class)
				|| clazz.equals(Byte[].class);
	}

	/**
	 * 生成一个基础类型的默认值
	 * 
	 * @param clazz
	 * @return 失败返回null,成功返回对象;
	 */
	private static Object getDefaultPrimiticeValue(Class clazz) {
		if (clazz.isPrimitive()) {
			return clazz == boolean.class ? false : 0;
		}
		return null;
	}

	/**
	 * 判断类型是否是Collection
	 * 
	 * @param claxx
	 *            类型
	 * @return 失败返回false,成功返回true;
	 */
	public static boolean isCollection(Class claxx) {
		if (null == claxx) {
			return false;
		}
		return Collection.class.isAssignableFrom(claxx);
	}

}
