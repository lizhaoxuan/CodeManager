package com.lizhaoxuan.codemanager.sort;

/**
 * 冒泡排序的几种实现
 * Created by lizhaoxuan on 16/2/20.
 */
public class BubbleSort {

    /**
     * 最优冒泡排序
     * 如果有100个数的数组，仅前面10个无序，后面90个都已排好序且都大于前面10个数字，
     * 那么在第一趟遍历后，最后发生交换的位置必定小于10，
     * 且这个位置之后的数据必定已经有序了，记录下这位置，
     * 第二次只要从数组头部遍历到这个位置就可以了。
     */
    void BubbleSort(int a[], int n) {
        int j, k;
        int flag;

        flag = n;
        while (flag > 0) {
            k = flag;
            flag = 0;
            for (j = 1; j < k; j++)
                if (a[j - 1] > a[j]) {
                    Swap.Swap(a, j - 1, j);
                    flag = j;
                }
        }
    }

    /**
     * 传统冒泡
     */
    void BubbleSort1(int a[], int n)
    {
        int i, j;
        for (i = 0; i < n; i++)
            for (j = 1; j < n - i; j++)
                if (a[j - 1] > a[j])
                    Swap.Swap(a,j-1,j);
    }

    /**
     * 初步优化：如果有一趟没有发生交换，说明排序已经完成。
     */
    void BubbleSort2(int a[], int n)
    {
        int j, k;
        boolean flag;

        k = n;
        flag = true;
        while (flag)
        {
            flag = false;
            for (j = 1; j < k; j++)
                if (a[j - 1] > a[j])
                {
                    Swap.Swap(a,j-1,j);
                    flag = true;
                }
            k--;
        }
    }

}
