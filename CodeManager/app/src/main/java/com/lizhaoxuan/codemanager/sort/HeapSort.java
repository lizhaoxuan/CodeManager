package com.lizhaoxuan.codemanager.sort;

/**
 * 堆排序
 * Created by lizhaoxuan on 16/2/20.
 */
public class HeapSort {

    public void heapSort(int[] a, int n) {
        for (int i = n - 1; i >= 1; i--) {
            Swap.Swap(a, i, 0);
            MinHeapFixdown(a, 0, i);
        }
    }

    //建立最小堆
    void MakeMinHeap(int a[], int n) {
        for (int i = n / 2 - 1; i >= 0; i--)
            MinHeapFixdown(a, i, n);
    }

    //  从i节点开始调整,n为节点总数 从0开始计算 i节点的子节点为 2*i+1, 2*i+2
    void MinHeapFixdown(int a[], int i, int n) {
        int j, temp;

        temp = a[i];
        j = 2 * i + 1;
        while (j < n) {
            if (j + 1 < n && a[j + 1] < a[j]) //在左右孩子中找最小的
                j++;

            if (a[j] >= temp)
                break;

            a[i] = a[j];     //把较小的子结点往上移动,替换它的父结点
            i = j;
            j = 2 * i + 1;
        }
        a[i] = temp;
    }

    //在最小堆中删除数
    void MinHeapDeleteNumber(int a[], int n) {
        Swap.Swap(a, 0, n - 1);
        MinHeapFixdown(a, 0, n - 1);
    }

}
