package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-07-16 10:28
 */
public class Interview10_01 {

    public void merge(int[] A, int m, int[] B, int n) {

        int pa = 0;
        int pb = 0;
        int[] sorted = new int[m + n];
        int cur;
        while (pa < m || pb < n) {
            if (pa == m) {
                cur = B[pb++];
            } else if (pb == n) {
                cur = A[pa++];
            } else if (A[pa] < B[pb]) {
                cur = A[pa++];
            } else {
                cur = B[pb++];
            }
            sorted[pa + pb - 1] = cur;
        }

        for (int i = 0; i != m + n; ++i)
            A[i] = sorted[i];
    }
}
