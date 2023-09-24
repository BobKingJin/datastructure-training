package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-04 8:47
 */
public class Number1191 {

    // 参考：https://leetcode.cn/problems/k-concatenation-maximum-sum/solution/java-kadanesuan-fa-yu-jie-ti-si-lu-by-zdxiq125/
    public int kConcatenationMaxSum(int[] arr, int k) {

        int len = arr.length;
        int sum = 0;
        int cur = 0;
        int res = 0;

        int loopCount = Math.min(2, k) * len;
        for (int i = 0; i < len; ++i)
            sum += arr[i];

        for (int i = 0; i < loopCount; ++i) {
            int val = arr[i % len];
            cur = cur + val > 0 ? cur + val : 0;
            res = Math.max(res, cur);
        }

        if (sum > 0) {
            while (k-- > 2)
                res = (res + sum) % 1000000007;
        }

        return res % 1000000007;
    }
}
