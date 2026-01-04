package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2021-07-09 22:40
 */
public class Number448 {

    // 参考：https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/solution/zhao-dao-suo-you-shu-zu-zhong-xiao-shi-d-mabl/
    public List<Integer> findDisappearedNumbers(int[] nums) {

        List<Integer> res = new ArrayList<Integer>();

        if (nums == null || nums.length == 0) {
            return res;
        }

        int n = nums.length;
        // 注意这个位置是在 nums 数组，即原数组中进行值的改变的，但是这并不会影响结果的正确性
        // 例如 [4, 3, 2, 7, 8, 2, 3, 1]，第一个数为 4 (4 - 1) % 8 = 3，即把 nums[3] = 7 + 8 = 15
        // 但是这时候 nums[3] 并没有遍历到，即改变了 nums[3] 的原值，当遍历 nums[3] = 15 时，(15 - 1) % 8 = 6
        // 与 nums[3] 原值 nums[3] = 7 (7 - 1) % 8 = 6 无区别 这是因为 nums[x] 每次加上 n 而 n % n = 0 所以无影响
        for (int num : nums) {
            // 第 i 个数的角标为 i - 1
            int x = (num - 1) % n;
            nums[x] += n;
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                res.add(i + 1);
            }
        }

        return res;
    }


}
