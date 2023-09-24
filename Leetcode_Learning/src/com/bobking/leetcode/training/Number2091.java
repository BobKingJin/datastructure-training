package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-03-01 19:58
 */
public class Number2091 {

    // 参考：https://leetcode.cn/problems/removing-minimum-and-maximum-from-array/solution/san-chong-tan-xin-ce-lue-qu-zui-xiao-zhi-fhnn/
    public int minimumDeletions(int[] nums) {

        // 最小值的位置为 i
        // 最大值的位置为 j
        int i = 0;
        int j = 0;

        for(int k = 1; k < nums.length; k++){
            if (nums[k] < nums[i])
                i = k;
            if (nums[k] > nums[j])
                j = k;
        }
        // 保证 i <= j
        if (i > j) {
            int temp = i;
            i = j;
            j = temp;
        }
        return Math.min(Math.min(j + 1, nums.length - i), i + 1 + nums.length - j);
    }
}
