package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-12-20 10:44
 */
public class Number1760 {

    // 参考：https://leetcode.cn/problems/minimum-limit-of-balls-in-a-bag/solution/dai-zi-li-zui-shao-shu-mu-de-qiu-by-leet-boay/
    public int minimumSize(int[] nums, int maxOperations) {

        int left = 1;
        int right = Arrays.stream(nums).max().getAsInt();
        int ans = 0;

        while (left <= right) {

            int y = (left + right) / 2;
            long ops = 0;
            for (int x : nums)
                ops += (x - 1) / y;

            if (ops <= maxOperations) {
                ans = y;
                right = y - 1;
            } else {
                left = y + 1;
            }
        }
        return ans;
    }
}
