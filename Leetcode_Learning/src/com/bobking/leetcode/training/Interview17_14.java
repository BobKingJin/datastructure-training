package com.bobking.leetcode.training;

import java.util.Random;

/**
 * @Date: 2024/11/11 10:51
 * @Author: BobKing
 * @Description:
 */
public class Interview17_14 {

    int k;

    public int[] smallestK(int[] arr, int k) {
        this.k = k;
        int n = arr.length;
        int[] ans = new int[k];
        if (arr == null || arr.length == 0 || k == 0)
            return ans;
        quickSort(arr, 0, n - 1);
        for (int i = 0; i < k; i++)
            ans[i] = arr[i];
        return ans;
    }

    private void quickSort(int[] arr, int l, int r) {

        if (l >= r)
            return;

        int i = l;
        int j = r;
        int ridx = new Random().nextInt(r - l + 1) + l;
        swap(arr, ridx, l);
        int x = arr[l];

        while (i < j) {
            while (i < j && arr[j] >= x)
                j--;
            while (i < j && arr[i] <= x)
                i++;
            swap(arr, i, j);
        }
        swap(arr, i, l);
        // 集中答疑：因为题解是使用「基准点左侧」来进行描述（不包含基准点的意思），所以这里用的 k 写成 k - 1 也可以
        if (i > k)
            quickSort(arr, l, i - 1);
        if (i < k)
            quickSort(arr, i + 1, r);
    }

    private void swap(int[] arr, int l, int r) {
        int tmp = arr[l];
        arr[l] = arr[r];
        arr[r] = tmp;
    }

}
