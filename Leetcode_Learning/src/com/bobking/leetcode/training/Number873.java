package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2022-07-09 10:10
 */
public class Number873 {

    // 参考：https://leetcode.cn/problems/length-of-longest-fibonacci-subsequence/solution/zhuang-tai-ding-yi-hen-shi-zhong-yao-by-christmas_/
    public int lenLongestFibSubseq1(int[] arr) {

        // dp[i][j]：以 A[i], A[j] 结尾的最长的长度 - 2
        // 如果存在 A[k] = A[j] - A[i] 且 k < i 则 dp[i][j] = dp[k][i] + 1
        int[][] dp = new int[arr.length - 1][arr.length];
        int maxLen = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++)
            map.put(arr[i], i);

        for (int i = 1; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int stepLen = arr[j] - arr[i];
                if (map.containsKey(stepLen) && map.get(stepLen) < i) {
                    int k = map.get(stepLen);
                    dp[i][j] = dp[k][i] + 1;
                    maxLen = Math.max(maxLen, dp[i][j] + 2);
                }
            }
        }

        return maxLen;
    }

    // 参考：https://leetcode.cn/problems/length-of-longest-fibonacci-subsequence/solution/by-ac_oier-beo2/
    public int lenLongestFibSubseq2(int[] arr) {

        // 定义 f[i][j] 为使用 arr[i] 为斐波那契数列的最后一位，使用 arr[j] 为倒数第二位（即 arr[i] 的前一位）时的最长数列长度
        // 不失一般性考虑 f[i][j] 该如何计算，首先根据斐波那契数列的定义，可以直接算得 arr[j] 前一位的值为 arr[i] - arr[j]
        // 而快速得知 arr[i] - arr[j] 值的坐标 t，可以利用 arr 的严格单调递增性质，使用「哈希表」对坐标进行转存，若坐标 t 存在，并且符合 t < j
        // 说明此时至少凑成了长度为 3 的斐波那契数列，同时结合状态定义，可以使用 f[j][t] 来更新 f[i][j]
        // 即有状态转移方程：f[i][j] = max(3, f[j][t] + 1)
        // 同时，当「从小到大」枚举 i，并且「从大到小」枚举 j 时，可以进行如下的剪枝操作：
        // 可行性剪枝：当出现 arr[i] - arr[j] >= arr[j]，说明即使存在值为 arr[i] - arr[j] 的下标 t，根据 arr 单调递增性质，也不满足 t < j < i 的要求
        // 继续枚举更小的 j，仍然有 arr[i] - arr[j] >= arr[j]，仍不合法，直接 break 掉当前枚举 j 的搜索分支
        // 最优性剪枝：假设当前最大长度为 ans，只有当 j + 2 > ans，才有必要往下搜索，j + 2 的含义为以 arr[j] 为斐波那契数列倒数第二个数时的理论最大长度

        int n = arr.length;
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++)
            map.put(arr[i], i);

        int[][] f = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0 && j + 2 > ans; j--) {

                if (arr[i] - arr[j] >= arr[j])
                    break;
                int t = map.getOrDefault(arr[i] - arr[j], -1);
                if (t == -1)
                    continue;
                f[i][j] = Math.max(3, f[j][t] + 1);
                ans = Math.max(ans, f[i][j]);
            }
        }

        return ans;
    }
}
