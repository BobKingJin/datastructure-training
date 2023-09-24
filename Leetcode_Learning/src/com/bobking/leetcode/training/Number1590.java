package com.bobking.leetcode.training;

import java.util.HashMap;

/**
 * @author BobKing
 * @create 2023-03-10 16:28
 */
public class Number1590 {

    // 参考：https://leetcode.cn/problems/make-sum-divisible-by-p/solution/tao-lu-qian-zhui-he-ha-xi-biao-pythonjav-rzl0/
    public int minSubarray(int[] nums, int p) {

        int n = nums.length;
        int ans = n;
        int[] s = new int[n + 1];

        for (int i = 0; i < n; ++i)
            s[i + 1] = (s[i] + nums[i]) % p;

        int x = s[n];
        if (x == 0)
            // 移除空子数组
            return 0;

        HashMap<Integer, Integer> last = new HashMap<Integer, Integer>();
        for (int i = 0; i <= n; ++i) {
            last.put(s[i], i);
            // 如果不存在，-n 可以保证 i - j >= n
            int j = last.getOrDefault((s[i] - x + p) % p, -n);
            ans = Math.min(ans, i - j);
        }
        return ans < n ? ans : -1;
    }
}
