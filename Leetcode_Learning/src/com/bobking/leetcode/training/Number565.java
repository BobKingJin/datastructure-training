package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-07-17 15:52
 */
public class Number565 {

    public int arrayNesting(int[] nums) {

        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            int cur = i;
            int cnt = 0;
            while (nums[cur] != -1) {
                cnt++;
                int c = cur;
                cur = nums[cur];
                nums[c] = -1;
            }
            ans = Math.max(ans, cnt);
        }

        return ans;
    }
}
