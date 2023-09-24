package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-06-26 12:01
 */
public class Number1578 {

    // 参考：https://leetcode.cn/problems/minimum-time-to-make-rope-colorful/solution/bi-mian-zhong-fu-zi-mu-de-zui-xiao-shan-chu-chen-4/
    public int minCost(String colors, int[] neededTime) {

        // 在这一系列重复颜色中，保留删除成本最高的颜色，并删除其他颜色。这样得到的删除成本一定是最低的
        int i = 0;
        int len = colors.length();
        int res = 0;

        while (i < len) {

            char ch = colors.charAt(i);
            int maxValue = 0;
            int sum = 0;

            while (i < len && colors.charAt(i) == ch) {
                maxValue = Math.max(maxValue, neededTime[i]);
                sum += neededTime[i];
                i++;
            }
            // 保留删除成本最高的颜色
            res += sum - maxValue;
        }
        
        return res;
    }
}
