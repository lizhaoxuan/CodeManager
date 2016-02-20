package com.lizhaoxuan.codemanager.sort;

/**
 * 归并排序
 * 其的基本思路就是将数组分成二组A，B，
 * 如果这二组组内的数据都是有序的，
 * 那么就可以很方便的将这二组数据进行排序。
 * Created by lizhaoxuan on 16/2/20.
 */
public class MergeSort {

    boolean MergeSort(int a[], int n) {
        int[] p = new int[n];
        mergeSort(a, 0, n - 1, p);
        return true;
    }

    void mergeSort(int a[], int first, int last, int temp[]) {
        if (first < last) {
            int mid = (first + last) / 2;
            mergeSort(a, first, mid, temp);    //左边有序
            mergeSort(a, mid + 1, last, temp); //右边有序
            mergeArray(a, first, mid, last, temp); //再将二个有序数列合并
        }
    }

    /**
     * 将有二个有序数列a[first...mid]和a[mid...last]合并。
     */
    void mergeArray(int a[], int first, int mid, int last, int temp[]) {
        int i = first, j = mid + 1;
        int m = mid, n = last;
        int k = 0;

        while (i <= m && j <= n) {
            if (a[i] <= a[j])
                temp[k++] = a[i++];
            else
                temp[k++] = a[j++];
        }

        while (i <= m)
            temp[k++] = a[i++];

        while (j <= n)
            temp[k++] = a[j++];

        for (i = 0; i < k; i++)
            a[first + i] = temp[i];
    }

}
