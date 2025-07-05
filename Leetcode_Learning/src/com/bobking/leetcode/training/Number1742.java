package com.bobking.leetcode.training;

/**
 * @Date: 2025/7/5 17:28
 * @Author: BobKing
 * @Description:
 */
public class Number1742 {

    public int countBalls1(int lowLimit, int highLimit) {
        int ans = 0;
        // 99999 的数位和 = 45
        int[] cnt = new int[46];
        for (int i = lowLimit; i <= highLimit; i++) {
            int s = 0;
            for (int x = i; x > 0; x /= 10) {
                s += x % 10;
            }
            cnt[s]++;
            ans = Math.max(ans, cnt[s]);
        }
        return ans;
    }

    // 定义 s[i][j] 表示 [0, i] 中的数位和为 j 的数字个数
    private final int[][] s = new int[100_001][46];

    public int countBalls2(int lowLimit, int highLimit) {
        for (int i = 1; i < s.length; i++) {
            System.arraycopy(s[i - 1], 0, s[i], 0, s[i].length);
            int sum = 0;
            for (int x = i; x > 0; x /= 10) {
                sum += x % 10;
            }
            s[i][sum]++;
        }
        int ans = 0;
        for (int j = 1; j < s[0].length; j++) {
            ans = Math.max(ans, s[highLimit][j] - s[lowLimit - 1][j]);
        }
        return ans;
    }

}
