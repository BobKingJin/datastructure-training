package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2022-12-11 10:35
 */
public class Number1726 {

    // 参考：https://leetcode.cn/problems/tuple-with-same-product/solution/c-ha-xi-huan-chong-si-lu-jie-jue-tle-by-drtg5/
    public int tupleSameProduct1(int[] nums) {

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int muti = nums[i] * nums[j];
                map.put(muti, map.getOrDefault(muti, 0) + 1);
            }
        }

        int res = 0;
        for (Integer value : map.values())
            res += value * (value - 1) / 2;

        return res * 8;
    }

    public int tupleSameProduct2(int[] nums) {

        if (nums.length < 4)
            return 0;

        int ans = 0;
        int n = nums.length;
        Arrays.sort(nums);

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 3; j < n; j++) {
                int x = nums[i] * nums[j];
                int p = i + 1;
                int q = j - 1;
                while (p < q) {
                    int y = nums[p] * nums[q];
                    if (y == x) {
                        ans++;
                        p++;
                        q--;
                    } else if (y > x) {
                        q--;
                    } else {
                        p++;
                    }
                }
            }
        }
        return ans << 3;
    }
}
