package com.bobking.leetcode.training;

/**
 * @Date: 2025/2/16 10:18
 * @Author: BobKing
 * @Description:
 */
public class Number3097 {

    // 参考: https://leetcode.cn/problems/shortest-subarray-with-or-at-least-k-ii/solutions/2716483/zi-shu-zu-orandgcd-tong-yong-mo-ban-pyth-n8xj/?envType=daily-question&envId=2025-02-16
    public int minimumSubarrayLength1(int[] nums, int k) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (x >= k) {
                return 1;
            }
            for (int j = i - 1; j >= 0 && (nums[j] | x) != nums[j]; j--) {
                nums[j] |= x;
                if (nums[j] >= k) {
                    ans = Math.min(ans, i - j + 1);
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // 参考: https://leetcode.cn/problems/shortest-subarray-with-or-at-least-k-ii/solutions/2716483/zi-shu-zu-orandgcd-tong-yong-mo-ban-pyth-n8xj/?envType=daily-question&envId=2025-02-16
    public int minimumSubarrayLength2(int[] nums, int k) {
        int ans = Integer.MAX_VALUE;
        int left = 0;
        int bottom = 0;
        int rightOr = 0;

        for (int right = 0; right < nums.length; right++) {
            rightOr |= nums[right];
            while (left <= right && (nums[left] | rightOr) >= k) {
                ans = Math.min(ans, right - left + 1);
                left++;
                if (bottom < left) {
                    // 重新构建一个栈
                    for (int i = right - 1; i >= left; i--) {
                        nums[i] |= nums[i + 1];
                    }
                    bottom = right;
                    rightOr = 0;
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }


}
