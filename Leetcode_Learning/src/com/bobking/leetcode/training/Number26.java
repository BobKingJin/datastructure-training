package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-03-28 13:59
 */
public class Number26 {

    public int removeDuplicates1(int[] nums) {

        if(nums == null || nums.length == 0)
            return 0;

        // p 初始化为 0，而不是 -1，是因为第一位必定保留
        int p = 0;
        int q = 1;
        while(q < nums.length){
            if(nums[p] != nums[q]) {
                nums[++p] = nums[q];
            }
            q++;
        }

        return p + 1;
    }

    // 参考：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/solution/shuang-zhi-zhen-shan-chu-zhong-fu-xiang-dai-you-hu/
    public int removeDuplicates2(int[] nums) {

        if(nums == null || nums.length == 0)
            return 0;

        int p = 0;
        int q = 1;
        while(q < nums.length){
            if(nums[p] != nums[q]){
                if(q - p > 1)
                    nums[p + 1] = nums[q];
                p++;
            }
            q++;
        }

        return p + 1;
    }

}
