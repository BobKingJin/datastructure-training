package com.bobking.leetcode.training;

public class Number31 {

    // 参考：https://leetcode-cn.com/problems/next-permutation/solution/xia-yi-ge-pai-lie-by-leetcode-solution/
    public void nextPermutation(int[] nums) {

        if (nums == null || nums.length <= 1) {
            return;
        }

        //  需要将一个左边的「较小数」与一个右边的「较大数」交换，以能够让当前排列变大，从而得到下一个排列
        //  同时要让这个「较小数」尽量靠右，而「较大数」尽可能小。当交换完成后
        // 「较大数」右边的数需要按照升序重新排列，这样可以在保证新排列大于原来排列的情况下，使变大的幅度尽可能小

        int i = nums.length - 2;
        // 从后向前查找第一个顺序对 (i, i + 1)，满足 a[i] < a[i + 1]
        // 此时 [i + 1, n) 必然是下降序列，因为 (i, i + 1) 是第一对，若 [i + 1, n) 不是下降序列，那么必然早于(i, i + 1)发现
        // 当 nums 严格降序，例如：3，2，1，那么此时 i = -1
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            int j = nums.length - 1;
            // 找到 i + 1 至 nums.length - 1 范围内第一个大于 nums[i] 的数
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        // 直接使用双指针反转区间 [i + 1, n)使其变为升序，而无需对该区间进行排序
        // 因为 (i, i + 1) 是从右往左的第一个升序对，意味着 (i + 1) - n 都是降序的，同时因为 nums[i] < numsp[j]
        // 那么即使 nums[i] 和 nums[j] 交换后，(i + 1) - n 还是是降序的
        reverse(nums, i + 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int i) {
        int j = nums.length - 1;
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }
}
