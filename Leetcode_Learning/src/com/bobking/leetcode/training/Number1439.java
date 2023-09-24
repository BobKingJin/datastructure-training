package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-28 8:06
 */
public class Number1439 {

    private int count;

    public int kthSmallest(int[][] mat, int k) {

        int n = mat[0].length;
        int m = mat.length;
        int lo = 0;
        int hi = 0;

        for (int i = 0; i < m; i++) {
            lo += mat[i][0];
            hi += mat[i][n - 1];
        }

        int init = lo;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            count = 1;
            dfs(mid, 0, init, k, mat);
            if (count < k) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    private void dfs(int mid, int index, int sum, int k, int[][] mat) {

        int m = mat.length;
        int n = mat[0].length;

        if (sum > mid || index == m || count > k)
            return;
        // 选 或者 不选 第 index 行
        // 按行递归
        // 不选 第 index 行
        dfs(mid, index + 1, sum, k, mat);
        // 选 第 index 行
        for (int i = 1; i < n; i++) {
            if (sum + mat[index][i] - mat[index][0] <= mid) {
                count++;
                dfs(mid, index + 1, sum + mat[index][i] - mat[index][0], k, mat);
            } else {
                break;
            }
        }
    }
}
