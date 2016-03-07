package com.lizhaoxuan.codemanager.bitmap;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * 图片工具类
 */
public class ImageUtil {

	private static final String TAG = ImageUtil.class.getSimpleName();

	/**
	 * 储存bitmap到文件
	 * @param imgFile 保存图片的文件路径
	 * @param imgBm 图片对象
	 * @param compress 压缩值(0-100)
	 * @return 失败返回false
	 */
	public static boolean saveBitmapJPG(File imgFile, Bitmap imgBm, int compress) {
		if (null == imgFile || null == imgBm) {
			return false;
		}
		if (imgFile.exists()) {
			imgFile.delete();
		} else {
			try {
				imgFile.createNewFile();
			} catch (IOException e) {
				Log.e(TAG, "", e);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.e(TAG, "", e);
			} 
		}
		try {
			FileOutputStream out = new FileOutputStream(imgFile);
			imgBm.compress(Bitmap.CompressFormat.JPEG, compress, out);
			out.flush();
			out.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "", e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "", e);
		}
		return false;
	}

	/**
	 * 压缩图片宽高
	 * 
	 * @param file 图片文件
	 * @param reqWidth 要求的最大宽度
	 * @param reqHeight 要求的最大高度
	 * @return 失败返回null
	 */
	public static Bitmap compressBitmapSize(File file, int reqWidth,
			int reqHeight) {
		if (null == file) {
			return null;
		}

		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(file.getAbsolutePath(), options);
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
	}

	/**
	 * 计算缩放的尺寸
	 * 
	 * @param options 
	 * @param reqWidth
	 * @param reqHeight
	 * @return 返回缩放值
	 */
	private static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {
			final int halfHeight = height;
			final int halfWidth = width;
			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}
		return inSampleSize;
	}

	/**
	 * 质量图片压缩 支持JPEG
	 * 
	 * @param image 图片对象
	 * @param maxSize
	 *            最大尺寸
	 * @return 缩小比例,负数代表错误
	 */
	public static int compressImageQuality(Bitmap image, int maxSize) {
		if (null == image) {
			return -1;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 100;
		while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
			baos.reset();// 重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
			options -= 10;// 每次都减少10
		}
		return options;
	}

}
