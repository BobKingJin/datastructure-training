package com.bobking.leetcode.training;

import java.util.TreeSet;

/**
 * @author BobKing
 * @create 2022-05-29 11:46
 */
public class Number220 {

    // 参考：https://leetcode.cn/problems/contains-duplicate-iii/solution/gong-shui-san-xie-yi-ti-shuang-jie-hua-d-dlnv/
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

        // 对于任意一个位置 i（假设其值为 u），其实是希望在下标范围为 [max(0, i - k), i) 内找到值范围在 [u - t, u + t]的数
        // 因为它后面这个位置是对 nums 进行遍历，那么其实下标范围在 [max(0, i - k), i) 而不是 [max(0, i - k), i + k)] 的原因
        // 是因为它后面其实会遍历到 i + k 的位置(如果 (i + k) < nums.length) 即它不会漏掉结果
        // 或许会想到近似 O(1) 操作的 HashMap，但注意这里要找的是符合 abs(nums[i], nums[j]) ⩽ t 的两个值，nums[i] 与 nums[j] 并不一定相等
        // 而 HashMap 无法很好的支持「范围查询」操作
        int n = nums.length;
        TreeSet<Long> ts = new TreeSet<Long>();
        for (int i = 0; i < n; i++) {
            Long u = nums[i] * 1L;
            // 从 ts 中找到小于等于 u 的最大值（小于等于 u 的最接近 u 的数）
            Long l = ts.floor(u);
            // 从 ts 中找到大于等于 u 的最小值（大于等于 u 的最接近 u 的数）
            Long r = ts.ceiling(u);
            if(l != null && u - l <= t)
                return true;
            if(r != null && r - u <= t)
                return true;
            // 将当前数加到 ts 中，并移除下标范围不在 [max(0, i - k), i) 的数（维持滑动窗口大小为 k）
            ts.add(u);
            if (i >= k)
                ts.remove(nums[i - k] * 1L);
        }

        return false;
    }
}
