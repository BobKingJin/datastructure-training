package com.bobking.leetcode.training;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Number215 {

    public int findKthLargest1(int[] nums, int k) {

        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            return 0;
        }

        // 维护一个长度为 k 的小根堆，那么小根堆的堆顶元素即为第 k 大的元素
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        for (int num : nums) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

    // 参考：https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/partitionfen-er-zhi-zhi-you-xian-dui-lie-java-dai-/
    public int findKthLargest2(int[] nums, int k) {

        int left = 0;
        int right = nums.length - 1;

        // 转换一下, 第 k 大元素的索引是 len - k
        int target = nums.length - k;

        while (true) {
            int index = partition(nums, left, right);
            if (index == target) {
                return nums[index];
            } else if (index < target) {
                left = index + 1;
            } else {
                right = index - 1;
            }
        }
    }

    private int partition(int[] nums, int left, int right) {
        // 在数组 nums 的子区间 [left, right] 执行 partition 操作，返回 nums[left] 排序以后应该在的位置
        // [left + 1, j] < nums[left]  (j, i] >= nums[left]
        int pivot = nums[left];
        // 认为 left - j 已经排好序
        int j = left;
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < pivot) {
                // j 的初值为 left，先右移，再交换，小于 pivot 的元素都被交换到前面
                // j 表示遍历到目前为止，最右边小于 pivot 的角标，因此需要先 j++，再交换
                // 例如：6 4 7 3 2 8
                swap(nums, ++j, i);
            }
        }
        // 在之前遍历的过程中，满足 [left + 1, j] < pivot，并且 (j, i] >= pivot
        // 注意最后还需要交换一次
        swap(nums, j, left);
        // 交换以后 [left, j - 1] < pivot, nums[j] = pivot, [j + 1, right] >= pivot
        return j;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

}
