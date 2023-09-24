package com.bobking.leetcode.training;

public class Interview17_24 {

    // 参考：程序猿代码指南P398
    // 参考：https://leetcode-cn.com/problems/max-submatrix-lcci/solution/zhe-yao-cong-zui-da-zi-xu-he-shuo-qi-you-jian-dao-/
    public int[] getMaxMatrix(int[][] matrix) {

        int[] res = new int[4];

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return res;

        int max = Integer.MIN_VALUE;
        // 累加和数组
        int[] sum = null;
        // 暂时记录左上角
        int tempRow = 0;
        int tempCol = 0;
        // 以每一行为开始
        for (int i = 0; i < matrix.length; i++) {
            // 第 i 行 - 第 j 行 的累加和
            sum = new int[matrix[0].length];
            for (int j = i; j < matrix.length; j++) {
                // 当得到第 i 行 - 第 j 行 的累加和，然后计算当前累加和的最大值
                int cur = 0;
                for (int k = 0; k < matrix[0].length; k++) {

                    sum[k] += matrix[j][k];
                    if (cur > 0) {
                        cur += sum[k];
                    } else {
                        cur = sum[k];
                        tempRow = i;
                        tempCol = k;
                    }

                    max = Math.max(cur, max);
                    if (max == cur) {
                        res[0] = tempRow;
                        res[1] = tempCol;
                        res[2] = j;
                        res[3] = k;
                    }
                    // 注意这个位置当 cur <= 0 时，意味着当前位置不可能成为最大值，即置为 0
                    cur = cur <= 0 ? 0 : cur;
                }
            }
        }

        return res;
    }
}
