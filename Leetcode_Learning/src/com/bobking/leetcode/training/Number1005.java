package com.bobking.leetcode.training;

import java.util.PriorityQueue;

/**
 * @author BobKing
 * @create 2023-03-03 21:04
 */
public class Number1005 {

    // 参考：https://leetcode.cn/problems/maximize-sum-of-array-after-k-negations/solution/gong-shui-san-xie-jian-dan-fen-qing-kuan-6qwu/
    public int largestSumAfterKNegations(int[] nums, int k) {

        int n = nums.length;
        int idx = 0;
        PriorityQueue<Integer> q = new PriorityQueue<Integer>((a, b) -> nums[a] - nums[b]);
        boolean zero = false;

        for (int i = 0; i < n; i++) {
            if (nums[i] < 0)
                q.add(i);
            if (nums[i] == 0)
                zero = true;
            if (Math.abs(nums[i]) < Math.abs(nums[idx]))
                idx = i;
        }
        if (k <= q.size()) {
            while (k-- > 0)
                nums[q.peek()] = -nums[q.poll()];
        } else {
            while (!q.isEmpty() && k-- > 0)
                nums[q.peek()] = -nums[q.poll()];
            // 存在 0 值 或 剩余取反次数为偶数：直接返回当前取反数组的总和（0 值可抵消任意次数的取反操作，将偶数次的取反操作应用在同一数值上，结果不变）
            // 不存在 0 值且剩余取反次数为奇数：此时从当前数值中取一个绝对值最小值（使用 idx 记录该值下标）进行取反，得到最终的取反数组
            if (!zero && k % 2 != 0)
                nums[idx] = -nums[idx];
        }

        int ans = 0;

        for (int i : nums)
            ans += i;

        return ans;
    }
}
