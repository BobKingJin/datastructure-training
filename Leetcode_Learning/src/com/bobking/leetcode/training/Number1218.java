package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2022-03-19 12:17
 */
public class Number1218 {

    // 参考：https://leetcode-cn.com/problems/longest-arithmetic-subsequence-of-given-difference/solution/gong-shui-san-xie-jie-he-tan-xin-de-zhua-dj1k/
    public int longestSubsequence(int[] arr, int difference) {

        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        // 定义 f[i][j]（j 非 0 即 1） 为代表考虑前 i 个数，且第 i 个数的选择情况为 j 时，得到的最长定差子序列长度
        // 最终答案为 max(f[n - 1][0], f[n - 1][1])，同时有显然的初始化条件 f[0][0] = 0 和 f[0][1] = 1
        // f[i][0]：明确了第 i 个不选，那么此时最大长度为前一个位置的结果。即有：f[i][0] = max(f[i - 1][0], f[i - 1][1])
        // f[i][1]：明确了第 i 个要选，此时进行分情况讨论：
        // arr[i] 独立成为一个子序列，此时有：f[i][1] = 1
        // arr[i] 接在某一个数的后面，由于给定了差值 difference，可直接算得上一位的值为 prev = arr[i] - differenceprev
        // 此时应当找到值为 prev，下标最大（下标小于 i）的位置，然后从该位置转移过来，即有：f[i][1] = f[hash[prev]][1] + 1

        int[][] f = new int[n][2];
        f[0][1] = 1;
        map.put(arr[0], 0);

        for (int i = 1; i < n; i++) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][1]);
            f[i][1] = 1;
            int prev = arr[i] - difference;
            if (map.containsKey(prev))
                f[i][1] = Math.max(f[i][1], f[map.get(prev)][1] + 1);
            // 因为要求下标尽量大，所以如果存在重复值时，可以直接覆盖
            map.put(arr[i], i);
        }

        return Math.max(f[n - 1][0], f[n - 1][1]);
    }
}
