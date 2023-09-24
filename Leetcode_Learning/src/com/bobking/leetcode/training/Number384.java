package com.bobking.leetcode.training;

import java.util.Random;

/**
 * @author BobKing
 * @create 2022-05-04 16:13
 */
public class Number384 {

    // 参考：https://leetcode-cn.com/problems/shuffle-an-array/solution/gong-shui-san-xie-xi-pai-suan-fa-yun-yon-0qmy/
    private class Solution {
        
        int[] nums;
        int n;
        Random random = new Random();
        
        public Solution(int[] nums) {
            this.nums = nums;
            this.n = nums.length;
        }
        
        public int[] reset() {
            return nums;
        }

        // 从前往后尝试填充 [0, n - 1] 该填入什么数时，通过随机当前下标与（剩余的）哪个下标进行值交换来实现
        public int[] shuffle() {
            
            int[] res = nums.clone();
            
            for (int i = 0; i < n; i++) 
                swap(res, i, i + random.nextInt(n - i));
            
            return res;
        }
        
        private void swap(int[] arr, int i, int j) {
            int c = arr[i];
            arr[i] = arr[j];
            arr[j] = c;
        }
    }
}
