package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 2025/7/12 14:23
 * @Author: BobKing
 * @Description:
 */
public class Numebr2588 {

    // 参考: https://leetcode.cn/problems/count-the-number-of-beautiful-subarrays/solutions/2163133/tao-lu-qian-zhui-he-ha-xi-biao-pythonjav-3fna/?envType=daily-question&envId=2025-07-12
    public long beautifulSubarrays(int[] nums) {
        long ans = 0;
        int s = 0;
        // 预分配空间
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>(nums.length + 1);
        cnt.put(0, 1);
        for (int x : nums) {
            s ^= x;
            int c = cnt.getOrDefault(s, 0);
            ans += c;
            cnt.put(s, c + 1);
        }
        return ans;
    }

}
