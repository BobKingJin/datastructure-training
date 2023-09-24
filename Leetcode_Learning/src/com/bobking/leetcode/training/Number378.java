package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author BobKing
 * @create 2022-05-04 15:48
 */
public class Number378 {

    // 参考：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/solution/you-xu-ju-zhen-zhong-di-kxiao-de-yuan-su-by-leetco/
    public int kthSmallest1(int[][] matrix, int k) {

        int rows = matrix.length;
        int columns = matrix[0].length;
        int[] sorted = new int[rows * columns];
        int index = 0;
        for (int[] row : matrix) {
            for (int num : row)
                sorted[index++] = num;
        }

        Arrays.sort(sorted);

        return sorted[k - 1];
    }

    // 参考：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/solution/you-xu-ju-zhen-zhong-di-kxiao-de-yuan-su-by-leetco/
    public int kthSmallest2(int[][] matrix, int k) {

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        int n = matrix.length;
        for (int i = 0; i < n; i++)
            pq.offer(new int[]{matrix[i][0], i, 0});

        for (int i = 0; i < k - 1; i++) {
            int[] now = pq.poll();
            if (now[2] != n - 1)
                pq.offer(new int[]{matrix[now[1]][now[2] + 1], now[1], now[2] + 1});
        }

        return pq.poll()[0];
    }

    // 参考：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/solution/you-xu-ju-zhen-zhong-di-kxiao-de-yuan-su-by-leetco/
    public int kthSmallest3(int[][] matrix, int k) {

        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];

        // 任取一个数 mid 满足 l ≤ mid ≤ r，那么矩阵中不大于 mid 的数，肯定全部分布在矩阵的左上角
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            // 设当前位置为 matrix[i][j]，若 matrix[i][j] ≤ mid，则将当前所在列的不大于 mid 的数的数量（即 i + 1）累加到答案中，并向右移动
            // 否则向上移动
            if (check(matrix, mid, k, n)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    // 统计出了这个矩阵中不大于 mid 的数的个数
    private boolean check(int[][] matrix, int mid, int k, int n) {

        int i = n - 1;
        int j = 0;
        int num = 0;
        while (i >= 0 && j < n) {
            // 若 matrix[i][j] ≤ mid，则将当前所在列的不大于 mid 的数的数量（即 i + 1）累加到答案中，并向右移动，否则向上移动
            if (matrix[i][j] <= mid) {
                num += i + 1;
                j++;
            } else {
                i--;
            }
        }

        return num >= k;
    }


}
