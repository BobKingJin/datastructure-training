package com.bobking.leetcode.training;

public class Number363 {

    // 参考：程序猿代码指南P398
    // 参考：https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k/solution/javacong-bao-li-kai-shi-you-hua-pei-tu-pei-zhu-shi/
    public int maxSumSubmatrix(int[][] matrix, int k) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int max = Integer.MIN_VALUE;
        // 累加和数组
        int[] sum = null;
        // 枚举左边界
        for (int i = 0; i < matrix.length; i++) {

            sum = new int[matrix[0].length];
            // 枚举右边界
            for (int j = i; j < matrix.length; j++) {
                for (int l = 0; l < matrix[0].length; l++)
                    sum[l] += matrix[j][l];

                // 求 rowSum 连续子数组的
                // 和尽量大，但不大于 k
                max = Math.max(max, dpmax(sum, k));
            }
        }

        return max;
    }

    private int dpmax(int[] arr, int k) {

        int max = Integer.MIN_VALUE;
        // l - r 之间的累加和
        for (int l = 0; l < arr.length; l++) {
            int sum = 0;
            for (int r = l; r < arr.length; r++) {
                sum += arr[r];
                if (sum > max && sum <= k)
                    max = sum;
            }
        }

        return max;
    }

}
