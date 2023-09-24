package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-23 7:32
 */
public class Number1444 {

    // 参考：https://leetcode.cn/problems/number-of-ways-of-cutting-a-pizza/solution/dong-tai-gui-hua-c-by-smilyt_/
    public int ways(String[] pizza, int k) {

        int n = pizza.length;
        int m = pizza[0].length();
        int mod = (int) Math.pow(10, 9) + 7;
        int[][] nums = new int[n][m];
        count(nums, pizza);

        if (nums[n - 1][m - 1] == 0)
            return 0;

        // dp[i][j][k] i, j 表示披萨剩余部分的左上角, k 表示当前披萨被切成 k 块
        int[][][] dp = new int[n][m][k + 1];
        dp[0][0][1] = 1;

        for (int x = 2; x <= k; x++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {

                    if (dp[i][j][x - 1] == 0)
                        continue;

                    // 穷举水平切
                    for (int z = i + 1; z < n; z++) {
                        if (hasA(nums, i, j, z - 1, m - 1) && hasA(nums, z, j, n - 1, m - 1)) {
                            dp[z][j][x] += dp[i][j][x - 1];
                            dp[z][j][x] %= mod;
                        }
                    }
                    // 穷举垂直切
                    for (int z = j + 1; z < m; z++) {
                        if (hasA(nums, i, j, n - 1, z - 1) && hasA(nums, i, z, n - 1, m - 1)) {
                            dp[i][z][x] += dp[i][j][x - 1];
                            dp[i][z][x] %= mod;
                        }
                    }
                }
            }
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res += dp[i][j][k];
                res %= mod;
            }
        }
        return res %= mod;
    }

    private void count(int[][] nums, String[] pizza) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                int num1 = 0;
                int num2 = 0;
                int num3 = 0;
                if (i > 0)
                    num1 = nums[i - 1][j];
                if (j > 0)
                    num2 = nums[i][j - 1];
                if (i > 0 && j > 0)
                    num3 = nums[i - 1][j - 1];
                nums[i][j] = num1 + num2 - num3 + (pizza[i].charAt(j) == 'A' ? 1 : 0);
            }
        }
    }

    private boolean hasA(int[][] nums, int sr, int sc, int er, int ec) {

        int num1 = 0;
        int num2 = 0;
        int num3 = 0;
        if (sr != 0)
            num1 = nums[sr - 1][ec];
        if (sc != 0)
            num2 = nums[er][sc - 1];
        if (sr != 0 && sc != 0)
            num3 = nums[sr - 1][sc - 1];
        return nums[er][ec] - num1 - num2 + num3 > 0;
    }
}
