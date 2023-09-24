package com.bobking.leetcode.training;

import java.util.List;

/**
 * @author BobKing
 * @create 2021-07-26 10:17
 */
public class Number120 {

    // 参考：https://leetcode-cn.com/problems/triangle/solution/di-gui-ji-yi-hua-dp-bi-xu-miao-dong-by-sweetiee/
    public int minimumTotal1(List<List<Integer>> triangle) {

        if(triangle == null || triangle.size() == 0)
            return 0;

        return dfs1(triangle, 0, 0);
    }

    // 按行递归，即 i 递归
    private int dfs1(List<List<Integer>> triangle, int i, int j) {

        if (i == triangle.size())
            return 0;
                            // 角标 j 不变                               角标 j + 1
        return Math.min(dfs1(triangle, i + 1, j), dfs1(triangle, i + 1, j + 1)) + triangle.get(i).get(j);
    }

    Integer[][] memo;
    // 参考：https://leetcode-cn.com/problems/triangle/solution/di-gui-ji-yi-hua-dp-bi-xu-miao-dong-by-sweetiee/
    public int minimumTotal2(List<List<Integer>> triangle) {

        memo = new Integer[triangle.size()][triangle.size()];
        return dfs2(triangle, 0, 0);
    }

    private int dfs2(List<List<Integer>> triangle, int i, int j) {

        if (i == triangle.size())
            return 0;

        if (memo[i][j] != null)
            return memo[i][j];

        return memo[i][j] = Math.min(dfs2(triangle, i + 1, j), dfs2(triangle, i + 1, j + 1)) + triangle.get(i).get(j);
    }

    // 参考：https://leetcode-cn.com/problems/triangle/solution/di-gui-ji-yi-hua-dp-bi-xu-miao-dong-by-sweetiee/
    public int minimumTotal3(List<List<Integer>> triangle) {

        int n = triangle.size();
        // dp[i][j] 表示从点 (i, j) 到底边的最小路径和
        int[][] dp = new int[n + 1][n + 1];
        // 从三角形的最后一行开始递推
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++)
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
        }

        return dp[0][0];
    }

    // 从上到下有问题，因为         2
    //                         3   4
    //                       6   5   7
    //                     4   1   8   3
    // 当移动到 4 时，dp[1][1] = Math.min(dp[0][1], dp[0][0]) + 4，此时 dp[0][1] = 0，其实 dp[0][1]不存在
    // 主要还是边界条件的问题，在每行的最右的一个元素，其实只能来自上一行的最后一个元素

    public int minimumTotal4(List<List<Integer>> triangle) {

        int res = Integer.MAX_VALUE;

        int n = triangle.size();
        // dp[i][j] 表示从点 (0, 0) 到 (i, j) 的最小路径和
        int[][] dp = new int[n + 1][n + 1];
        dp[0][0] = triangle.get(0).get(0);
        // 从三角形的最后一行开始递推
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++){
                if(j == 0){
                    dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
                }else if(j == i){
                    dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                } else{
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
                }
            }
        }

        for(int j = 0; j < n; j++)
            res = Math.min(res, dp[n - 1][j]);

        return res;
    }

}
