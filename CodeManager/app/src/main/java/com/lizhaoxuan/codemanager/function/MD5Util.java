package com.lizhaoxuan.codemanager.function;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.util.Log;

/**
 * MD5加密算法工具类
 */
public class MD5Util {
	private static final String TAG = MD5Util.class.getSimpleName();

	/**
	 * MD5加密
	 * 
	 * @param string
	 *            需要加密的字符串
	 * @return 失败返回null
	 */
	public static String getMD5(String string) {
		if (null == string) {
			return null;
		}
		try {
			byte[] hash = MessageDigest.getInstance("MD5").digest(
					string.getBytes("UTF-8"));

			StringBuilder hex = new StringBuilder(hash.length * 2);
			for (byte b : hash) {
				if ((b & 0xFF) < 0x10)
					hex.append("0");
				hex.append(Integer.toHexString(b & 0xFF));
			}
			return hex.toString();
		} catch (NoSuchAlgorithmException e) {
			Log.e(TAG, "", e);
		} catch (UnsupportedEncodingException e) {
			Log.e(TAG, "", e);
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(TAG, "", e);
		}
		return null;
	}
}
