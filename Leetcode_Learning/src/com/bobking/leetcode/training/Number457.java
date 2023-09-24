package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-07-31 12:10
 */
public class Number457 {

    int n;
    int[] nums;

    // 参考：https://leetcode.cn/problems/circular-array-loop/solution/gong-shui-san-xie-yi-ti-shuang-jie-mo-ni-ag05/
    public boolean circularArrayLoop(int[] nums) {
        this.nums = nums;
        n = nums.length;
        for (int i = 0; i < n; i++) {
            if (check(i))
                return true;
        }
        return false;
    }

    private boolean check(int start) {

        int cur = start;
        boolean flag = nums[start] > 0;
        // 令 k 为记录过程中扫描过的下标数量
        int k = 1;
        while (true) {
            if (k > n)
                return false;
            // 如果 next 为负数：在 next 的基础上增加 n ∗ ⌈next / n⌉，将其映射回正值
            // 如果 next 为正数：将 next 模数组长度 n，确保不会越界
            int next = ((cur + nums[cur]) % n + n ) % n;

            // 如果在检查过程中，找到了与起点相同的下标，且 k > 1，说明存在符合条件的「循环」，返回 True
            // 如果检查过程中扫描的数量 k 超过了数组长度 n，那么根据「鸽笼原理」，必然有数被重复处理了，同时条件一并不符合
            // 因此再处理下去，也不会到达与起点相同的下标，返回 False
            // 处理过程中发现不全是正数或者负数，返回 False

            if (flag && nums[next] < 0)
                return false;
            if (!flag && nums[next] > 0)
                return false;
            if (next == start)
                return k > 1;
            cur = next;
            k++;
        }
    }
}
