package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-12-20 11:25
 */
public class Number941 {

    // 参考：https://leetcode.cn/problems/valid-mountain-array/solution/shuang-zhi-zhen-ji-bai-liao-100de-yong-hu-by-sdwwl/
    public boolean validMountainArray(int[] arr) {

        int len = arr.length;
        int left = 0;
        int right = len - 1;
        // 从左边往右边找，一直找到山峰为止
        while (left + 1 < len && arr[left] < arr[left + 1])
            left++;
        // 从右边往左边找，一直找到山峰为止
        while (right > 0 && arr[right - 1] > arr[right])
            right--;
        // 判断从左边和从右边找的山峰是不是同一个
        return left > 0 && right < len - 1 && left == right;
    }
}
