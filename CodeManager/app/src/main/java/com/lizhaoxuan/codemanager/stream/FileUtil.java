package com.lizhaoxuan.codemanager.stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;

import android.util.Log;

import com.lizhaoxuan.codemanager.lang.StringUtil;

/**
 * 文件操作工具类
 */
public class FileUtil {

	private static final String TAG = FileUtil.class.getSimpleName();

	/**
	 * 按照指定目录删除并创建新文件
	 * @param dir 目录
	 * @param path 文件完整路径
	 * @return 失败返回null
	 */
	public static File createNewFile(String dir, String path) {
		if (StringUtil.isEmpty(dir) || StringUtil.isEmpty(path)) {
			return null;
		}
		File dirFile = new File(dir);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		File myCaptureFile = new File(path);
		if (myCaptureFile.exists()) {
			myCaptureFile.delete();
		}
		try {
			if (myCaptureFile.createNewFile()) {
				return myCaptureFile;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "", e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "", e);
		}
		return null;
	}

	/**
	 * 文件Copy
	 * 
	 * @param s
	 *            输出文件
	 * @param t
	 *            写入文件
	 * @return 失败返回false
	 */
	public static boolean fileChannelCopy(File s, File t) {
		if (null == s || null == t) {
			return false;
		}
		FileInputStream fi = null;
		FileOutputStream fo = null;
		try {
			fi = new FileInputStream(s);
			fo = new FileOutputStream(t);
			FileChannel in = fi.getChannel();// 得到对应的文件通道
			FileChannel out = fo.getChannel();// 得到对应的文件通道
			in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
			return true;
		} catch (IOException e) {
			Log.e(TAG, "", e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "", e);
		} finally {
			try {
				if (fo != null)
					fo.close();
				if (fi != null)
					fi.close();
			} catch (IOException e) {
				Log.e(TAG, "", e);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.e(TAG, "", e);
			}
		}
		return false;
	}

	/**
	 * 输出文件大小的字符串形式 ext."100.00B", "1.00K", "1.00M", "1.00G", "0.00B"
	 * 
	 * @param fileLen
	 * @return 失败返回null
	 */
	public static String formatFileSizeToString(long fileLen) {// 转换文件大小
		String fileSizeString = null;
		if (fileLen >= 0) {
			DecimalFormat df = new DecimalFormat("0.00");
			if (fileLen < 1024) {
				fileSizeString = df.format((double) fileLen) + "B";
			} else if (fileLen < 1048576) {
				fileSizeString = df.format((double) fileLen / 1024) + "K";
			} else if (fileLen < 1073741824) {
				fileSizeString = df.format((double) fileLen / 1048576) + "M";
			} else {
				fileSizeString = df.format((double) fileLen / 1073741824) + "G";
			}
		}
		return fileSizeString;
	}

	/**
	 * 删除文件
	 * 
	 * @param file
	 * @return 失败返回false
	 */
	public static boolean deleteFile(File file) {
		return file != null && file.delete();
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param filename
	 * @return 失败返回null
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			} else {
				return "";
			}
		}
		return filename;
	}

	/**
	 * 读取指定文件的输出String
	 * 
	 * @param path
	 * @return 失败返回null
	 */
	public static String getFileOutputString(String path) {
		if (StringUtil.isEmpty(path)) {
			return null;
		}
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(
					path), 8192);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
			bufferedReader.close();
			return sb.toString();
		} catch (Exception e) {
			Log.d(TAG, "", e);
		}
		return null;
	}

	/**
	 * 写入String到文件中
	 * 
	 * @param path
	 *            文件全路径
	 * @param content
	 *            写入内容
	 * @return 失败返回false
	 */
	public static boolean writeFileOutputString(String path, String content) {
		if (StringUtil.isEmpty(path) || StringUtil.isEmpty(content)) {
			return false;
		}
		try {
			File file = new File(path);
			BufferedWriter bufWriter = new BufferedWriter(new FileWriter(file));
			bufWriter.write(content);
			bufWriter.close();
			return true;
		} catch (IOException e) {
			Log.e(TAG, "", e);
		} catch (Exception e) {
			
		}
		return false;
	}
}
