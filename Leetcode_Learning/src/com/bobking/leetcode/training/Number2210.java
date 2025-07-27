package com.bobking.leetcode.training;

/**
 * @Date: 2025/7/27 9:34
 * @Author: BobKing
 * @Description:
 */
public class Number2210 {

    // 参考: https://leetcode.cn/problems/count-hills-and-valleys-in-an-array/solutions/1352602/by-endlesscheng-59gn/?envType=daily-question&envId=2025-07-27
    public int countHillValley1(int[] nums) {
        int k = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[k] = nums[i];
                k++;
            }
        }
        int ans = 0;
        for (int i = 1; i < k - 1; i++) {
            // 如果 nums[i − 1] < nums[i] 和 nums[i] > nums[i + 1] 都为 true 则为「峰」
            // 如果 nums[i − 1] < nums[i] 和 nums[i] > nums[i + 1] 都为 false 则为「谷」
            // 两种情况可以合并为: nums[i − 1] < nums[i] 的布尔值和 nums[i] > nums[i + 1] 的布尔值相等
            if ((nums[i - 1] < nums[i]) == (nums[i] > nums[i + 1])) {
                ans++;
            }
        }
        return ans;
    }

    // 参考: https://leetcode.cn/problems/count-hills-and-valleys-in-an-array/solutions/1352602/by-endlesscheng-59gn/?envType=daily-question&envId=2025-07-27
    public int countHillValley2(int[] nums) {
        int ans = 0;
        int pre = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            int cur = nums[i];
            int nxt = nums[i + 1];
            if (cur == nxt) {
                continue;
            }
            // 注意 pre 可能等于 cur, 比如 [1, 1, 2] 中 pre = cur = 1, nxt = 2
            if (pre != cur && (pre < cur) == (cur > nxt)) {
                ans++;
            }
            pre = cur;
        }
        return ans;
    }

}
