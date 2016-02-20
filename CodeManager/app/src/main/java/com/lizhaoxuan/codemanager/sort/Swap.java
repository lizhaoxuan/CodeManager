package com.lizhaoxuan.codemanager.sort;

/**
 * 两个数交换的几种实现
 * Created by lizhaoxuan on 16/2/20.
 */
public class Swap {

    /**
     * 数组中两个数交换
     */
    public static void Swap(int[] a, int i, int j) {
        if (a[i] != a[j]) {
            a[i] ^= a[j];
            a[j] ^= a[i];
            a[i] ^= a[j];
        }
    }

    /**
     * 简单交换
     */
    public int[] swap(int[] a) {
        int c = a[0];
        a[0] = a[1];
        a[1] = c;
        return a;
    }

    /**
     * 不使用临时变量交换
     */
    public int[] swap1(int[] a) {
        if (a[0] != a[1]) {
            a[0] ^= a[1];
            a[1] ^= a[0];
            a[0] ^= a[1];
        }
        return a;
    }

    /**
     * 不使用临时变量交换的第二种思路
     */
    public int[] swap2(int[] a) {
        a[0] = a[0] + a[1];
        a[1] = a[0] - a[1];
        a[0] = a[0] - a[1];
        return a;
    }

}
