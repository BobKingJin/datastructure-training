package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-09 15:05
 */
public class Number1598 {

    // 参考：https://leetcode.cn/problems/crawler-log-folder/solution/by-ac_oier-24xb/
    public int minOperations(String[] logs) {

        int depth = 0;

        for (String s : logs) {
            if (s.equals("../")) {
                depth = Math.max(0, depth - 1);
            } else if (!s.equals("./")) {
                depth++;
            }
        }

        return depth;
    }
}
