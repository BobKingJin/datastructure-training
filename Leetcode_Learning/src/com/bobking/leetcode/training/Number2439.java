package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-20 16:58
 */
public class Number2439 {

    // 参考：https://leetcode.cn/problems/minimize-maximum-of-array/solution/cppjava-you-shi-yi-dao-jing-dian-de-er-f-w3i6/
    public int minimizeArrayValue(int[] nums) {

        int left = 0;
        int right = 1000000000;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (check(nums, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] nums, int k) {

        long have = 0;

        for (int n : nums) {
            if (n <= k) {
                have += k - n;
            } else {
                if (have < n - k) {
                    return false;
                } else {
                    have -= (n - k);
                }
            }
        }
        return true;
    }
}
