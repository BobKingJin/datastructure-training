package com.bobking.leetcode.training;

public class Number63 {

    // 参考：https://leetcode-cn.com/problems/unique-paths/solution/dong-tai-gui-hua-by-powcai-2/
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        if (obstacleGrid == null || obstacleGrid.length == 0)
            return 0;

        // 自底向上的递归会存在大量的重复计算
        // 将其改写为在二维数组中自顶向下的递推即可，即 dp[i, j] = dp[i - 1, j] + dp[i, j - 1]

        // 定义 dp 数组并初始化第 1 行和第 1 列
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // dp[i][j] 表示 (0, 0) 走到格子 (i, j) 的方法数
        int[][] dp = new int[m][n];
        dp[0][0] = obstacleGrid[0][0] == 0 ? 1 : 0;

        // 第一列
        for (int i = 1; i < m; i++){
            if(obstacleGrid[i][0] == 0){
                dp[i][0] = dp[i - 1][0];
            }else{
                break;
            }
        }

        // 第一行
        for (int j = 1; j < n; j++){
            if(obstacleGrid[0][j] == 0){
                dp[0][j] = dp[0][j - 1];
            }else{
                break;
            }
        }

        // 根据状态转移方程 dp[i][j] = dp[i - 1][j] + dp[i][j - 1] 进行递推
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0)
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }
}
