package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-10-26 7:45
 */
public class Number553 {

    // 参考: https://leetcode.cn/problems/optimal-division/solutions/1295375/gong-shui-san-xie-shu-xue-lei-tan-xin-yu-61sq/
    public String optimalDivision(int[] nums) {

        int n = nums.length;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            sb.append(nums[i]);
            if (i + 1 < n)
                sb.append("/");
        }

        if (n > 2) {
            sb.insert(sb.indexOf("/") + 1, "(");
            sb.append(")");
        }

        return sb.toString();
    }
}
