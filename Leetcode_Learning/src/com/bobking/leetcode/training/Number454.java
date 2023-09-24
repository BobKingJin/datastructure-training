package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2022-05-14 11:40
 */
public class Number454 {

    // 参考：https://leetcode.cn/problems/4sum-ii/solution/chao-ji-rong-yi-li-jie-de-fang-fa-si-shu-xiang-jia/
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int res = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int sumAB = nums1[i] + nums2[j];
                if (map.containsKey(sumAB)){
                    map.put(sumAB, map.get(sumAB) + 1);
                } else{
                    map.put(sumAB, 1);
                }
            }
        }

        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                int sumCD = -(nums3[i] + nums4[j]);
                if (map.containsKey(sumCD))
                    res += map.get(sumCD);
            }
        }
        return res;
    }
}
