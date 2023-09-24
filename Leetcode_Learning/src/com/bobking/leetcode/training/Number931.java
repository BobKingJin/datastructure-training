package com.bobking.leetcode.training;

public class Number931 {

    // 参考：https://leetcode-cn.com/problems/minimum-falling-path-sum/solution/dong-tai-gui-hua-lu-jing-wen-ti-zui-xiao-v2kp/
    private int MAX = Integer.MAX_VALUE;

    public int minFallingPathSum1(int[][] matrix) {
        
        int n = matrix.length;
        int res = MAX;
        // 枚举首行的每个下标作为起点
        for (int i = 0; i < n; i++)
            res = Math.min(res, find(matrix, i));
        
        return res;
    }
    
    // 返回以 (0, index) 作为起点的最小路径和
    private int find(int[][] matrix, int index) {

        int n = matrix.length;
        int[][] f = new int[n][n];

        for (int i = 0; i < n; i++)
            f[0][i] = i == index ? matrix[0][i] : MAX;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                f[i][j] = MAX;
                int val = matrix[i][j];
                if (f[i - 1][j] != MAX)
                    f[i][j] = Math.min(f[i][j], f[i - 1][j] + val);
                if (j - 1 >= 0 && f[i - 1][j - 1] != MAX)
                    f[i][j] = Math.min(f[i][j], f[i - 1][j - 1] + val);
                if (j + 1 < n && f[i - 1][j + 1] != MAX)
                    f[i][j] = Math.min(f[i][j], f[i - 1][j + 1] + val);
            }
        }

        int res = MAX;
        for (int i = 0; i < n; i++)
            res = Math.min(res, f[n - 1][i]);
        return res;
    }

    // 参考：https://leetcode-cn.com/problems/minimum-falling-path-sum/solution/dong-tai-gui-hua-lu-jing-wen-ti-zui-xiao-v2kp/
    public int minFallingPathSum2(int[][] matrix) {

        int n = matrix.length;
        // 定义 f[i][j] 为到达位置 (i, j)的最小路径和
        int[][] f = new int[n][n];
        // 初始化：对于首行而言，每个位置的「最小成本」就是其「矩阵值」
        for (int i = 0; i < n; i++)
            f[0][i] = matrix[0][i];
        // 从第二行开始，根据题目给定的条件进行转移
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int val = matrix[i][j];
                f[i][j] = f[i - 1][j] + val;
                if (j - 1 >= 0)
                    f[i][j] = Math.min(f[i][j], f[i - 1][j - 1] + val);
                if (j + 1 < n)
                    f[i][j] = Math.min(f[i][j], f[i - 1][j + 1] + val);
            }
        }

        int res = MAX;
        for (int i = 0; i < n; i++)
            res = Math.min(res, f[n - 1][i]);
        return res;
    }
}
