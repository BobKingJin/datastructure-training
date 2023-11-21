package com.bobking.leetcode.training;

import java.util.Deque;
import java.util.LinkedList;

public class Number2216 {

    // 参考: https://leetcode.cn/problems/minimum-deletions-to-make-array-beautiful/solutions/1371891/liang-chong-jie-fa-by-tsreaper-02yo/?envType=daily-question&envId=Invalid+Date
    public int minDeletion1(int[] nums) {

        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                ans++;
            } else {
                i++;
            }
        }

        if ((n - ans) % 2 != 0)
            ans++;

        return ans;
    }

    // 参考: https://leetcode.cn/problems/minimum-deletions-to-make-array-beautiful/solutions/1371885/zhan-si-xiang-mo-ni-o1-e-wai-kong-jian-b-mlx0/?envType=daily-question&envId=Invalid+Date
    public int minDeletion2(int[] nums) {

        Deque<Integer> stack = new LinkedList<Integer>();

        for (int num : nums) {
            if (stack.size() % 2 == 0 || stack.getLast() != num)
                stack.addLast(num);
        }

        if (stack.size() % 2 == 0) {
            return nums.length - stack.size();
        } else {
            return nums.length - stack.size() + 1;
        }
    }
}
