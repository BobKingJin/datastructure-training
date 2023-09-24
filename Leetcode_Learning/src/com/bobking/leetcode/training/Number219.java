package com.bobking.leetcode.training;

import java.util.HashSet;

/**
 * @author BobKing
 * @create 2022-05-29 11:09
 */
public class Number219 {

    // 参考：https://leetcode.cn/problems/contains-duplicate-ii/solution/hua-jie-suan-fa-219-cun-zai-zhong-fu-yuan-su-ii-by/
    public boolean containsNearbyDuplicate(int[] nums, int k) {

        // 维护一个哈希表，里面始终最多包含 k 个元素，当出现重复值时则说明在 k 距离内存在重复元素
        // 每次遍历一个元素则将其加入哈希表中，如果哈希表的大小大于 k，则移除最前面的数字
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++) {

            if(set.contains(nums[i]))
                return true;

            set.add(nums[i]);
            if(set.size() > k)
                set.remove(nums[i - k]);
        }
        return false;
    }
}
