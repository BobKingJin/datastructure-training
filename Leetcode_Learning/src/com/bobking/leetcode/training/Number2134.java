package com.bobking.leetcode.training;

public class Number2134 {

    // 参考：https://leetcode.cn/problems/minimum-swaps-to-group-all-1s-together-ii/solution/minimum-swaps-to-group-all-by-ikaruga-9tz3/
    public int minSwaps(int[] nums) {

        // 1.数出一共有多少个 1
        // 2.使用滑动窗口，以 1 的总数为窗口大小
        // 3.计算窗口内 0 的个数，最少的即为需要交换的次数

        int len = nums.length;
        int count_1 = 0;
        int res = Integer.MAX_VALUE;
        // 统计 1 的个数
        for (int i : nums)
            count_1 += i;

        int l = 0;
        int r = 0;
        int count = 0;
        // 窗口左边界为 0，将右边界移动到 count_1 - 1处，使窗口大小为 count_1
        while (r < count_1 - 1)
            // 计算窗口中 1 的个数
            count += nums[r++];

        // 因为是环形数组，所以得窗口左边界一直移动到数组末尾，才能结束
        while (l < len){
            // 注意此处 (r++ % len)
            count += nums[r++ % len];
            //                  count_1 - count：窗口中 0 的个数
            res = Math.min(res, count_1 - count);
            // 左边界右移一位
            count -= nums[l++];
        }

        return res;
    }
}
