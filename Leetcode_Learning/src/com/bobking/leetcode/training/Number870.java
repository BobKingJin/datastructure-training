package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-12-11 10:51
 */
public class Number870 {

    // 参考：https://leetcode.cn/problems/advantage-shuffle/solution/tian-ji-sai-ma-by-endlesscheng-yxm6/
    public int[] advantageCount(int[] nums1, int[] nums2) {

        Arrays.sort(nums1);
        int len = nums2.length;
        Integer[] index = new Integer[len];
        int[] res = new int[len];
        for (int i = 0; i < len; i++)
            index[i] = i;

        // 注意比较器引用的数组需要是对象，所以不能放基本数据类型的数组
        // 根据 nums2 的值升序，来排序 nums2 的值的对应下标
        // 排序后 index[] 中第一个元素是 nums2 中最小值的下标，第二个元素是 nums2 中第二小值的下标
        Arrays.sort(index, (a, b) -> (nums2[a] - nums2[b]));
        int left = 0;
        int right = len - 1;
        // 遍历 nums1
        for (int num : nums1) {
            // index[left] 为 nums2 中最小值的下标，index[right] 为 nums2 中最大值的下标
            // 如果 num 比 nums2 中的最小值大，则本次 res 中 num 对应的下标为 index[left],然后 left++
            // 否则本次 res 中 num 对应的下标为 index[right],然后 right--
            int i = num > nums2[index[left]] ? index[left++] : index[right--];
            res[i] = num;
        }

        return res;
    }
}
