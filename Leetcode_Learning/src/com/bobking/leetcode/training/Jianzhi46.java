package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-12 7:16
 */
public class Jianzhi46 {

    // 'a'->1, 'b->2', ... , 'z->26'
    // 注意与 LCR165的不同点
    public int solve(String nums) {

        if (nums == null || nums.length() < 1 || "0".equals(nums)) {
            return 0;
        }

        // 排除只有一种可能的 10 和 20
        if ("10".equals(nums) || "20".equals(nums)) {
            return 1;
        }

        // 当0的前面不是1或2时,无法译码
        for (int i = 1; i < nums.length(); i++) {
            if (nums.charAt(i) == '0') {
                if (nums.charAt(i - 1) != '1' && nums.charAt(i - 1) != '2') {
                    return 0;
                }
            }
        }

        int prePre = 1;
        int pre = 1;
        int res = 1;

        for (int i = 2; i <= nums.length(); i++) {
            String tmp = nums.substring(i - 2, i);
            res = (tmp.compareTo("11") >= 0 && tmp.compareTo("19") <= 0) ||
                (tmp.compareTo("21") >= 0 && tmp.compareTo("26") <= 0) ? prePre + pre : pre;
            prePre = pre;
            pre = res;
        }
        return res;
    }
}
