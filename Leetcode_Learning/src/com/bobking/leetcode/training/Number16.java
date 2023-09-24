package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-03-26 23:21
 */
public class Number16 {

    // 参考：https://leetcode-cn.com/problems/3sum-closest/solution/hua-jie-suan-fa-16-zui-jie-jin-de-san-shu-zhi-he-b/
    public int threeSumClosest(int[] nums, int target) {

        if(nums == null || nums.length == 0)
            return target;

        Arrays.sort(nums);
        // 这个位置最好不要设置 res = Integer.MIN_VALUE(为一个负数)，因为后面在用绝对值进行判断，即 Math.abs(target - res)
        // 那么 target - res 会很大
        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                int sum = nums[start] + nums[end] + nums[i];
                if (Math.abs(target - sum) < Math.abs(target - res))
                    res = sum;
                if (sum > target){
                    end--;
                } else if (sum < target){
                    start++;
                } else{
                    return res;
                }
            }
        }
        
        return res;
    }
}
