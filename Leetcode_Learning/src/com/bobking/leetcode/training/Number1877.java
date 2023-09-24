package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-09-24 11:18
 */
public class Number1877 {

    // 参考：https://leetcode.cn/problems/minimize-maximum-pair-sum-in-array/solution/gong-shui-san-xie-noxiang-xin-ke-xue-xi-ru29y/
    public int minPairSum(int[] nums) {

        // 「尽量让“较小数”和“较大数”组成数对，可以有效避免出现“较大数成对”的现象」
        // 假定 nums 本身有序，由于要将 nums 拆分成 n / 2 个数对
        // 根据猜想，得到的数对序列为：
        // (nums[0], nums[n - 1]), (nums[1], nums[n - 2]), ... , (nums[(n / 2) - 1], nums[n / 2])
        // 换句话说，构成答案的数对必然是较小数取自有序序列的左边，较大数取自有序序列的右边，且与数组中心对称

        Arrays.sort(nums);
        int n = nums.length;
        int ans = nums[0] + nums[n - 1];

        for (int i = 0, j = n - 1; i < j; i++, j--)
            ans = Math.max(ans, nums[i] + nums[j]);

        return ans;
    }
}
