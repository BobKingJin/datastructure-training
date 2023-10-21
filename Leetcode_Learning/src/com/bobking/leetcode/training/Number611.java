package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-10-14 9:54
 */
public class Number611 {

    public int triangleNumber1(int[] nums) {

        // 判断三条边能组成三角形的条件为：
        // 任意两边之和大于第三边，任意两边之差小于第三边。
        // 三条边长从小到大为 a、b、c，当且仅当 a + b > c 这三条边能组成三角形

        // 首先对数组排序
        // 固定最短的两条边，二分查找最后一个小于两边之和的位置。可以求得固定两条边长之和满足条件的结果。枚举结束后，总和就是答案

        Arrays.sort(nums);
        int n = nums.length;
        int res = 0;

        for (int i = 0; i < n - 2; ++i) {
            for (int j = i + 1; j < n - 1; ++j) {
                int s = nums[i] + nums[j];
                int l = j + 1;
                int r = n - 1;
                while (l < r) {
                    int mid = (l + r + 1) >>> 1;
                    if (nums[mid] < s) {
                        l = mid;
                    } else {
                        r = mid - 1;
                    }
                }

                if (nums[r] < s)
                    res += r - j;
            }
        }
        return res;
    }

    // 参考: https://leetcode.cn/problems/valid-triangle-number/solutions/914449/gong-shui-san-xie-yi-ti-san-jie-jian-dan-y1we/
    public int triangleNumber2(int[] nums) {

        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i - 1, k = 0; k < j; j--) {
                while (k < j && nums[k] + nums[j] <= nums[i])
                    k++;
                ans += j - k;
            }
        }
        return ans;
    }
}
