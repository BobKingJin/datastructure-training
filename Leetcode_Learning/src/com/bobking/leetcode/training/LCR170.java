package com.bobking.leetcode.training;

/**
 * @Date: 2024/11/11 10:41
 * @Author: BobKing
 * @Description:
 */
public class LCR170 {

    int[] record;
    int[] tmp;

    public int reversePairs(int[] record) {
        this.record = record;
        this.tmp = new int[record.length];
        return mergeSort(0, record.length - 1);
    }

    private int mergeSort(int l, int r) {

        if (l >= r)
            return 0;

        int m = (l + r) / 2;
        int res = mergeSort(l, m) + mergeSort(m + 1, r);
        int i = l;
        int j = m + 1;

        for (int k = l; k <= r; k++)
            tmp[k] = record[k];

        for (int k = l; k <= r; k++) {
            if (i == m + 1) {
                record[k] = tmp[j++];
            } else if (j == r + 1 || tmp[i] <= tmp[j]) {
                record[k] = tmp[i++];
            } else {
                record[k] = tmp[j++];
                res += m - i + 1;
            }
        }
        return res;
    }
}
