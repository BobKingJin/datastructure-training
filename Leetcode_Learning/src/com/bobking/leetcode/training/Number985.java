package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-02-25 11:27
 */
public class Number985 {

    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {

        int[] result = new int[queries.length];
        // 偶数标志位数组
        boolean[] flag = new boolean[nums.length];
        int sum = 0;

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] % 2 == 0) {
                flag[i] = true;
                sum += nums[i];
            }
        }

        for (int i = 0; i < queries.length; ++i) {
            int val = queries[i][0];
            int index = queries[i][1];
            // 该位置原本是偶数
            if (flag[index]) {
                // 加上之后还是偶数
                if ((nums[index] + val) % 2 == 0) {
                    sum = sum + val;
                } else {
                    // 加上之后变成了奇数，移除该元素，标志为 false
                    sum = sum - nums[index];
                    flag[index] = false;
                }
            } else {
                // 该位置原本是奇数
                // 相加变为偶数，标志变为 true
                if ((nums[index] + val) % 2 == 0) {
                    sum = sum + nums[index] + val;
                    flag[index] = true;
                }
            }
            nums[index] += val;
            result[i] = sum;
        }
        return result;
    }
}
