package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-05-03 16:33
 */
public class Number324 {

    int n = -1;

    // 参考：https://leetcode-cn.com/problems/wiggle-sort-ii/solution/yi-bu-yi-bu-jiang-shi-jian-fu-za-du-cong-onlognjia/
    public void wiggleSort1(int[] nums) {

        // 找到中位数索引
        int midIndex = quickSelect(nums, 0, nums.length - 1);
        // 找到中位数
        int mid = nums[midIndex];
        int n = nums.length;
        // 三分法
        // 将大数排在左边，小数排在右边
        // i 是虚拟地址，(1 + 2 * (i)) % (n | 1)是实际地址
        // 其中 n 为数组长度，‘|’为按位或，如果 n 为偶数，(n | 1)为 n + 1，如果 n 为奇数，(n | 1)仍为 n
        for (int i = 0, j = 0, k = nums.length - 1; j <= k; ) {
            if (nums[V(j)] > mid) {
                swap(nums, V(j++), V(i++));
            } else if (nums[V(j)] < mid) {
                swap(nums, V(j), V(k--));
            } else {
                j++;
            }
        }
    }

    public int V(int i) {
        return (1 + 2 * (i)) % (n | 1);
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    private int quickSelect(int[] nums, int left, int right) {

        int pivot = nums[left];
        int l = left;
        int r = right;
        while (l < r) {

            while (l < r && nums[r] >= pivot)
                r--;

            if (l < r)
                nums[l++] = nums[r];

            while (l < r && nums[l] <= pivot)
                l++;

            if (l < r)
                nums[r--] = nums[l];
        }

        nums[l] = pivot;

        if (l == nums.length / 2) {
            return l;
        } else if (l > nums.length / 2) {
            return quickSelect(nums, left, l - 1);
        } else {
            return quickSelect(nums, l + 1, right);
        }
    }

    // 参考：https://leetcode-cn.com/problems/wiggle-sort-ii/solution/3ms-99-xian-pai-xu-zai-chuan-cha-by-wang-o7pm/
    public void wiggleSort2(int[] nums) {

        // 不能写成 int[] help = nums，排序后两个数组都改变
        int[] help = nums.clone();
        Arrays.sort(help);
        int N = nums.length;
        //比如 1 2 3 4 5 6
        for (int i = 1; i < nums.length; i += 2)
            // 遍历完成后 x 6 x 5 x 4
            nums[i] = help[--N];

        for (int i = 0; i < nums.length; i += 2)
            // 遍历完成后 3 6 2 5 1 4
            nums[i] = help[--N];
    }

}
