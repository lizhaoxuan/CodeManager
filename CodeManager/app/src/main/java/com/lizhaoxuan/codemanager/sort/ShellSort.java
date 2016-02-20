package com.lizhaoxuan.codemanager.sort;

/**
 * 希尔排序
 * 先将整个待排元素序列分割成若干个子序列（由相隔某个“增量”的元素组成的）分别进行直接插入排序，
 * 然后依次缩减增量再进行排序，待整个序列中的元素基本有序（增量足够小）时，
 * 再对全体元素进行一次直接插入排序。
 * Created by lizhaoxuan on 16/2/20.
 */
public class ShellSort {

    /**
     * 引文最优插入排序的最优写法
     */
    void shellsort(int a[], int n) {
        int i, j, gap;

        for (gap = n / 2; gap > 0; gap /= 2)
            for (i = gap; i < n; i++)
                for (j = i - gap; j >= 0 && a[j] > a[j + gap]; j -= gap)
                    Swap.Swap(a, j, j + gap);
    }

    /**
     * 按定义实现
     */
    void shellsort1(int a[], int n) {
        int i, j, gap;

        for (gap = n / 2; gap > 0; gap /= 2) //步长
            for (i = 0; i < gap; i++)        //直接插入排序
            {
                for (j = i + gap; j < n; j += gap)
                    if (a[j] < a[j - gap]) {
                        int temp = a[j];
                        int k = j - gap;
                        while (k >= 0 && a[k] > temp) {
                            a[k + gap] = a[k];
                            k -= gap;
                        }
                        a[k + gap] = temp;
                    }
            }
    }

    /**
     * 初步优化
     */
    void shellsort2(int a[], int n) {
        int j, gap;

        for (gap = n / 2; gap > 0; gap /= 2)
            for (j = gap; j < n; j++)//从数组第gap个元素开始
                if (a[j] < a[j - gap])//每个元素与自己组内的数据进行直接插入排序
                {
                    int temp = a[j];
                    int k = j - gap;
                    while (k >= 0 && a[k] > temp) {
                        a[k + gap] = a[k];
                        k -= gap;
                    }
                    a[k + gap] = temp;
                }
    }

}
