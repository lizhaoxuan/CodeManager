package com.lizhaoxuan.codemanager.function;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;

/**
 * 图片绘制工具类
 */
public class CanvasUtil {

	private static final String TAG = CanvasUtil.class.getSimpleName();

	/**
	 * 在图片上绘制文字
	 * 
	 * @param bitmap
	 *            图片对象
	 * @param str
	 *            需要绘制的文字
	 * @return 失败返回null,成功返回Bitmap
	 */
	public static Bitmap drawStrInBitmap(Bitmap bitmap, String str) {
		if (null == bitmap || null == str) {
			return null;
		}
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		int widthSize = width / 28;
		int heightSize = height / 28;
		Bitmap mbmpTest = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvasTemp = new Canvas(mbmpTest);
		canvasTemp.drawColor(Color.WHITE);
		Paint p = new Paint();
		String familyName = "宋体";
		Typeface font = Typeface.create(familyName, Typeface.BOLD);
		p.setColor(Color.RED);
		p.setTypeface(font);
		p.setTextSize(widthSize);// 一行最大28个字
		canvasTemp.save(Canvas.ALL_SAVE_FLAG);// 保存
		canvasTemp.restore();// 存储
		canvasTemp.drawBitmap(bitmap, 0, 0, null);// 在 0，0坐标开始画入src
		canvasTemp.drawText(str, 0, height - heightSize, p);
		return mbmpTest;
	}

	/**
	 * 绘制圆角图片
	 * 
	 * @param bitmap
	 *            图片对象
	 * @param roundPx
	 *            角度
	 * @return 失败返回null,成功返回Bitmap
	 */
	public static Bitmap drawRoundedCorner(Bitmap bitmap, float roundPx) {
		if (null == bitmap) {
			return null;
		}
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(
				android.graphics.PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

}
