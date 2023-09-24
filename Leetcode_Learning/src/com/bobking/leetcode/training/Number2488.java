package com.bobking.leetcode.training;

import java.util.HashMap;

/**
 * @author BobKing
 * @create 2023-03-16 0:53
 */
public class Number2488 {

    // 参考：https://leetcode.cn/problems/count-subarrays-with-median-k/solution/deng-jie-zhuan-huan-pythonjavacgo-by-end-5w11/
    public int countSubarrays(int[] nums, int k) {

        int pos = 0;
        int n = nums.length;
        while (nums[pos] != k)
            ++pos;

        HashMap<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        // i = pos 的时候 x 是 0，直接记到 cnt 中，这样下面不是大于 k 就是小于 k
        cnt.put(0, 1);
        // 从 pos - 1 开始累加 x
        for (int i = pos - 1, x = 0; i >= 0; --i) {
            x += nums[i] < k ? 1 : -1;
            // 等价于 cnt.put(c, cnt.getOrDefault(c, 0) + 1)
            cnt.merge(x, 1, Integer::sum);
        }

        // i = pos 的时候 x 是 0，直接加到答案中，这样下面不是大于 k 就是小于 k
        int ans = cnt.get(0) + cnt.getOrDefault(-1, 0);
        // 从 pos + 1 开始累加 x
        for (int i = pos + 1, x = 0; i < n; ++i) {
            x += nums[i] > k ? 1 : -1;
            ans += cnt.getOrDefault(x, 0) + cnt.getOrDefault(x - 1, 0);
        }
        return ans;
    }
}
