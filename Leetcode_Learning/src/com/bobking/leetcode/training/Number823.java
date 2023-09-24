package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2023-06-24 8:02
 */
public class Number823 {

    // 参考：https://leetcode.cn/problems/binary-trees-with-factors/solution/dai-yin-zi-de-er-cha-shu-by-leetcode/
    public int numFactoredBinaryTrees(int[] arr) {

        int MOD = 1000000007;
        int N = arr.length;
        Arrays.sort(arr);
        long[] dp = new long[N];
        Arrays.fill(dp, 1);

        Map<Integer, Integer> index = new HashMap<Integer, Integer>();
        for (int i = 0; i < N; ++i)
            index.put(arr[i], i);

        for (int i = 0; i < N; ++i)
            for (int j = 0; j < i; ++j) {
                // A[j] is left child
                if (arr[i] % arr[j] == 0) {
                    int right = arr[i] / arr[j];
                    if (index.containsKey(right))
                        dp[i] = (dp[i] + dp[j] * dp[index.get(right)]) % MOD;
                }
            }

        long ans = 0;
        for (long x : dp)
            ans += x;
        return (int) (ans % MOD);
    }
}
