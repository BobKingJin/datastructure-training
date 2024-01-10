package com.bobking.leetcode.training;

/**
 * @Date: 2023/12/22 8:12
 * @Author: BobKing
 * @Description:
 */
public class Number1671 {

    // 参考: https://leetcode.cn/problems/minimum-number-of-removals-to-make-mountain-array/solutions/501992/jian-dan-dong-tai-gui-hua-by-monologue-s/?envType=daily-question&envId=2023-12-22
    public int minimumMountainRemovals(int[] nums) {

        int n = nums.length;
        int[] delleft = new int[n];
        int[] delright = new int[n];

        for(int i = 0; i < n; ++i){
            delleft[i] = i;
            delright[i] = n - i - 1;
        }

        for(int i = 0; i < n; ++i){
            for(int j = i - 1; j >= 0; --j){
                if(nums[j] < nums[i])
                    delleft[i] = Math.min(delleft[i], delleft[j] + i - j - 1);
            }
        }

        for(int i = n - 1; i >= 0; --i){
            for(int j = i + 1; j < n; ++j){
                if(nums[j] < nums[i])
                    delright[i] = Math.min(delright[i], delright[j] + j - i - 1);
            }
        }

        int ans = Integer.MAX_VALUE;

        for(int i = 1; i < n - 1; ++i){
            if(delleft[i] == i || delright[i] == n - i - 1)
                continue;
            ans = Math.min(ans, delleft[i] + delright[i]);
        }

        return ans;
    }
}
