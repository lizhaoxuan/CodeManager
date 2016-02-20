package com.lizhaoxuan.codemanager.sort;

/**
 * 直接插入排序的几种实现
 * 直接插入排序(Insertion Sort)的基本思想是：
 * 每次将一个待排序的记录，按其关键字大小插入到前面已经排好序的子序列中的适当位置，
 * 直到全部记录插入完成为止。
 * Created by lizhaoxuan on 16/2/20.
 */
public class InsertSort {

    /**
     * 最优写法
     */
    void Insertsort(int a[], int n) {
        int i, j;
        for (i = 1; i < n; i++)
            for (j = i - 1; j >= 0 && a[j] > a[j + 1]; j--)
                Swap.Swap(a, j, j + 1);
    }

    /**
     * 初步优化
     * 将搜索和数据后移这二个步骤合并。
     */
    void Insertsort2(int a[], int n) {
        int i, j;
        for (i = 1; i < n; i++)
            if (a[i] < a[i - 1]) {
                int temp = a[i];
                for (j = i - 1; j >= 0 && a[j] > temp; j--)
                    a[j + 1] = a[j];
                a[j + 1] = temp;
            }
    }

    /**
     * 按定义实现
     */
    void Insertsort1(int a[], int n) {
        int i, j, k;
        for (i = 1; i < n; i++) {
            //为a[i]在前面的a[0...i-1]有序区间中找一个合适的位置
            for (j = i - 1; j >= 0; j--)
                if (a[j] < a[i])
                    break;

            //如找到了一个合适的位置
            if (j != i - 1) {
                //将比a[i]大的数据向后移
                int temp = a[i];
                for (k = i - 1; k > j; k--)
                    a[k + 1] = a[k];
                //将a[i]放到正确位置上
                a[k + 1] = temp;
            }
        }
    }

}
