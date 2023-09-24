package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BobKing
 * @create 2022-12-30 10:39
 */
public class Number2032 {

    // 参考：https://leetcode.cn/problems/two-out-of-three/solution/zhi-shao-zai-liang-ge-shu-zu-zhong-chu-x-5131/
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i : nums1)
            map.put(i, 1);

        for (int i : nums2)
            map.put(i, map.getOrDefault(i, 0) | 2);

        for (int i : nums3)
            map.put(i, map.getOrDefault(i, 0) | 4);

        List<Integer> res = new ArrayList<Integer>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int k = entry.getKey();
            int v = entry.getValue();
            if ((v & (v - 1)) != 0)
                res.add(k);
        }
        return res;
    }
}
