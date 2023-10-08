package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-04 23:01
 */
public class Number169 {

    public int majorityElement(int[] nums) {

        if(nums == null || nums.length == 0)
            return 0;

        int times = 0;
        int candidate = 0;

        for(int i = 0; i < nums.length; i++){
            if(times == 0){
                candidate = nums[i];
                times = 1;
            }else if(nums[i] == candidate){
                times++;
            }else{
                times--;
            }
        }

        times = 0;

        for(int i = 0; i < nums.length; i++){
            if(nums[i] == candidate)
                times++;
        }

        if(times > nums.length / 2)
            return candidate;

        return 0;
    }
}
