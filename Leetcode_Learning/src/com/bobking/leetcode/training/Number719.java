package com.bobking.leetcode.training;

import java.util.Arrays;

public class Number719 {

    // 参考：https://leetcode.cn/problems/find-k-th-smallest-pair-distance/solution/719-zhao-chu-di-k-xiao-de-ju-chi-dui-er-g1i76/
    public int smallestDistancePair(int[] nums, int k) {

        Arrays.sort(nums);
        int n = nums.length;
        int left = 0;
        int right = nums[n - 1] - nums[0];

        // 如果比 mid 小的距离的数量比 k 小（getCount(mid) < k），说明当前 mid 偏小，要增大 mid，令 left = mid + 1
        // 如果比 mid 小的距离的数量等于 k（getCount(mid) == k），这并不能代表当前 mid 就是想要的结果
        // 比如对于 nums = [1, 3, 1]，k = 1，它的距离排序后是 [0, 2, 2]，如果 mid 为 1，比 mid 小的距离的数量为 1
        // 正好等于 k，但显然 1 并不是最终结果，0 才是，所以这种情况 mid 应该继续减小，令 right = mid - 1
        // 如果比 mid 小的距离的数量比 k 大（getCount(mid) > k），mid 偏大，要减小 mid，令 right = mid - 1
        // 4 和 5 可以合并，最后返回的是 left

        while(left <= right){
            int mid = (left + right) / 2;
            if(getCount(mid, nums) < k){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }

        return left;
    }

    private int getCount(int dis, int[] nums){

        int l = 0;
        int cnt = 0;

        for(int r = 0; r < nums.length; r++){

            while(nums[r] - nums[l] > dis)
                l++;

            cnt += r - l;
        }

        return cnt;
    }
}
