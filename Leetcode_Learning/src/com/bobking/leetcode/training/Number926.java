package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-06-11 12:12
 */
public class Number926 {

    // 参考：https://leetcode.cn/problems/flip-string-to-monotone-increasing/solution/zui-gui-fan-de-dong-tai-gui-hua-xie-fa-d-82le/
    public int minFlipsMonoIncr1(String s) {

        // 定义 dp[i][0]，dp[i][0] 表示前 i 个元素递增且第 i 个元素为 0 的最小翻转次数
        // 定义 dp[i][1]，dp[i][1] 表示前 i 个元素递增且第 i 个元素为 1 的最小翻转次数
        // 由定义可知，如果前 i 个元素最后以 0 结尾且满足单调递增，那么前 i 个元素必须全部为 0
        // 由此可得 dp[i][0] 的状态转移如下：dp[i][0] = dp[i - 1][0] + (s.charAt(i) == '0' ? 0 : 1);
        // 由定义可知， dp[i][1] 只要满足最后一个元素为 1 就行，那么前 i - 1 个元素既可以为 0，也可以为 1
        // 因此 dp[i][1] 的状态转移如下：dp[i][1] = min(dp[i - 1][1], dp[i - 1][0]) + (s.charAt(i) == '1' ? 0 : 1)

        int dp[][] = new int[s.length()][2];
        //初始化
        dp[0][0] = s.charAt(0) == '0' ? 0 : 1;
        dp[0][1] = s.charAt(0) == '1' ? 0 : 1;
        //状态转移
        for (int i = 1; i < s.length(); i++) {
            dp[i][0] = dp[i - 1][0] + (s.charAt(i) == '0' ? 0 : 1);
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + (s.charAt(i) == '1' ? 0 : 1);
        }

        return Math.min(dp[s.length() - 1][0], dp[s.length() - 1][1]);
    }

    // 参考：https://leetcode.cn/problems/flip-string-to-monotone-increasing/solution/by-ac_oier-h0we/
    public int minFlipsMonoIncr2(String s) {

        // 令 s 长度为 n，原问题等价于在 s 中找到最长不下降子序列，设其长度为 ans，那么对应的 n - ans 即是答案
        // 由于数据范围为 1e5，因此需要使用 LIS 问题的贪心求解方式：使用 g 数组记录每个长度的最小结尾元素
        // 即 g[len] = x 含义为长度为 len 的最长不下降子序列的结尾元素为 x，然后在从前往后处理每个 t = s[i] 时
        // 由于是求解「最长不下降子序列」，等价于找「满足大于 t 的最小下标」，这可以运用「二分」进行求解
        char[] cs = s.toCharArray();
        int n = cs.length;
        int ans = 0;
        int[] g = new int[n + 10];
        Arrays.fill(g, n + 10);
        for (int i = 0; i < n; i++) {
            int t = s.charAt(i) - '0';
            int l = 1;
            int r = i + 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (g[mid] > t) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            g[r] = t;
            ans = Math.max(ans, r);
        }

        return n - ans;
    }

    // 参考：https://leetcode.cn/problems/flip-string-to-monotone-increasing/solution/by-ac_oier-h0we/
    public int minFlipsMonoIncr3(String s) {

        char[] cs = s.toCharArray();
        int n = cs.length;
        int ans = n;

        int[] sum = new int[n + 10];

        // 左边有多少个 1
        for (int i = 1; i <= n; i++)
            sum[i] = sum[i - 1] + (cs[i - 1] - '0');

        for (int i = 1; i <= n; i++) {
            int l = sum[i - 1];
            int r = (n - i) - (sum[n] - sum[i]);
            ans = Math.min(ans, l + r);
        }

        return ans;
    }



}
