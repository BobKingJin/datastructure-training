package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2023-01-05 11:27
 */
public class Number1803 {

    // 参考：https://leetcode.cn/problems/count-pairs-with-xor-in-a-range/solution/bu-hui-zi-dian-shu-zhi-yong-ha-xi-biao-y-p2pu/
    public int countPairs(int[] nums, int low, int high) {

        int ans = 0;
        HashMap<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (int x : nums)
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);

        for (++high; high > 0; high >>= 1, low >>= 1) {
            HashMap<Integer, Integer> nxt = new HashMap<Integer, Integer>();
            for (Map.Entry<Integer, Integer> e : cnt.entrySet()) {
                int x = e.getKey();
                int c = e.getValue();
                // %2 是取判断比特 1 来划分区间
                ans += c * (high % 2 * cnt.getOrDefault((high - 1) ^ x, 0) -
                        low % 2 * cnt.getOrDefault((low - 1) ^ x, 0));
                nxt.put(x >> 1, nxt.getOrDefault(x >> 1, 0) + c);
            }
            cnt = nxt;
        }
        return ans / 2;
    }
}
