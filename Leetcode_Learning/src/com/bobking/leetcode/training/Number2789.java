package com.bobking.leetcode.training;

/**
 * @Date: 2024/3/14 0:51
 * @Author: BobKing
 * @Description:
 */
public class Number2789 {

    // 参考: https://leetcode.cn/problems/largest-element-in-an-array-after-merge-operations/solutions/2355390/yi-ci-bian-li-de-jian-ji-die-dai-fa-by-y-ljc7/?envType=daily-question&envId=2024-03-14
    public long maxArrayValue(int[] nums) {

        int n = nums.length;
        long ans = nums[n - 1];
        long res = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            if (res >= nums[i]) {
                res += nums[i];
            } else {
                res = nums[i];
            }
            ans = Math.max(ans, res);
        }
        return ans;
    }
}
