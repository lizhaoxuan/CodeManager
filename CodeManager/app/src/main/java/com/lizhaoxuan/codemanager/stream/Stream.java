package com.lizhaoxuan.codemanager.stream;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.util.Log;
/**
 * 流操作工具类
 * Created by lizhaoxuan on 16/3/7.
 */
public class Stream {
    private static final String TAG = Stream.class.getSimpleName();


    private Stream() {
    }
    /**
     * 读取指定输入流中的数据, 返回一个字节数组
     *
     * @param in 包含数据的输入流
     * @return 所有数据组成的字节数组
     */
    public static byte[] loadToBytes(InputStream in) throws IOException {
        byte[] data = null;
        if (in == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        in.close();
        baos.close();
        data = baos.toByteArray();
        return data;
    }

    /**
     * 读取指定输入流中的数据, 返回字符串
     *
     * @param in 包含数据的输入流
     * @return 字符串
     */
    public static String loadToString(InputStream in) throws IOException {
        byte[] data = loadToBytes(in);
        return new String(data, 0, data.length, "utf-8");
    }

    /**
     * byte[] 转为 对象
     *
     * @param bytes
     * @return 转换后的对象
     */
    public static Object byteToObject(byte[] bytes) throws Exception {
        if (null == bytes) {
            return null;
        }
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException e) {
            Log.e(TAG, "", e);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "", e);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e(TAG, "", e);
        }
        return obj;
    }

    /**
     * 对象 转为 byte[]
     *
     * @param obj
     * @return 转换后的byte[]
     */
    public static byte[] objectToByte(Object obj) throws Exception {
        if (null == obj) {
            return null;
        }
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException e) {
            Log.e(TAG, "", e);
        } catch (Exception e) {
            Log.e(TAG, "", e);
        }
        return bytes;
    }

    /**
     * byte[] 添加到 StringBuilder 0或1的组合
     *
     * @param bytes
     * @param sb
     */
    public static void byteToBit(byte[] bytes, StringBuilder sb) {
        if (bytes == null)
            return;
        if (sb == null)
            sb = new StringBuilder();
        for (int i = 0; i < Byte.SIZE * bytes.length; i++)
            sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0'
                    : '1');
    }

    /**
     * byte[] 转成 0和1 组成的String
     *
     * @param bytes
     * @return 转换后的字符串 bytes为null 返回 ""
     */
    public static String byteToBit(byte[] bytes) {
        if (bytes == null)
            return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Byte.SIZE * bytes.length; i++)
            sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0'
                    : '1');
        return sb.toString();
    }

}
