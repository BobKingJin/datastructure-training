package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

public class Number315 {

    // 参考：程序猿代码指南P392
    public List<Integer> countSmaller(int[] nums) {

        List<Integer> result = new ArrayList<Integer>();

        if (nums.length == 0)
            return result;

        // 临时记录数组
        int[] temp = new int[nums.length];
        // 记录结果数组
        int[] res = new int[nums.length];

        // 不能对 nums 数组直接排序，如果对 nums 数组直接排序，会改变 nums[i] 原本的顺序，从而造成结果不对
        // 索引数组，作用：归并的时候，方便知道是哪个下标的元素，在排序的时候不是改变 nums 中的数的顺序
        // 而是改变 indexes 中的数，即 nums 中的数的角标
        int[] indexes = new int[nums.length];
        for (int i = 0; i < nums.length; i++)
            indexes[i] = i;

        mergeAndCountSmaller(nums, 0, nums.length - 1, indexes, temp, res);

        for (int i = 0; i < nums.length; i++)
            result.add(res[i]);

        return result;
    }

    // 针对数组 nums 指定的区间 [left, right] 进行归并排序，在排序的过程中完成统计任务
    private void mergeAndCountSmaller(int[] nums, int left, int right, int[] indexes, int[] temp, int[] res) {

        if (left == right)
            return;

        int mid = left + (right - left) / 2;
        mergeAndCountSmaller(nums, left, mid, indexes, temp, res);
        mergeAndCountSmaller(nums, mid + 1, right, indexes, temp, res);

        // 归并排序的优化，如果索引数组有序，则不存在逆序关系，没有必要合并
        if (nums[indexes[mid]] <= nums[indexes[mid + 1]])
            return;

        mergeOfTwoSortedArrAndCountSmaller(nums, left, mid, right, indexes, temp, res);
    }

    // [left, mid] 是排好序的，[mid + 1, right] 是排好序的
    private void mergeOfTwoSortedArrAndCountSmaller(int[] nums, int left, int mid, int right, int[] indexes, int[] temp, int[] res) {

        for (int i = left; i <= right; i++)
            temp[i] = indexes[i];

        int i = left;
        int j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                indexes[k] = temp[j];
                j++;
            } else if (j > right) {
                indexes[k] = temp[i];
                i++;
                res[indexes[k]] += (right - mid);
            } else if (nums[temp[i]] <= nums[temp[j]]) {
                // 注意：这里是 <=  保证稳定性
                indexes[k] = temp[i];
                i++;
                res[indexes[k]] += (j - mid - 1);
            } else {
                indexes[k] = temp[j];
                j++;
            }
        }
    }
}
