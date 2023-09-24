package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-02-12 11:32
 */
public class Number992 {

    // 参考：https://leetcode.cn/problems/subarrays-with-k-different-integers/solution/cong-zui-jian-dan-de-wen-ti-yi-bu-bu-tuo-7f4v/
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMostKDistinct(nums, k) - atMostKDistinct(nums, k - 1);
    }

    private int atMostKDistinct(int[] nums, int k) {

        int res = 0;
        int cnt = 0;
        int left = 0;
        int right = 0;
        int[] freq = new int[nums.length + 1];

        while (right < nums.length) {
            if (freq[nums[right++]]++ == 0)
                cnt++;
            while (cnt > k) {
                if (--freq[nums[left++]] == 0)
                    cnt--;
            }
            res += right - left;
        }
        return res;
    }
}
