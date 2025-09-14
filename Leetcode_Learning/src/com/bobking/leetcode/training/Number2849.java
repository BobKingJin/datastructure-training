package com.bobking.leetcode.training;

/**
 * @Date: 2025/9/14 14:15
 * @Author: BobKing
 * @Description:
 */
public class Number2849 {

    // 参考: https://leetcode.cn/problems/determine-if-a-cell-is-reachable-at-a-given-time/solutions/2435321/xian-xie-zhao-zou-zai-zhi-zou-ran-hou-za-lkxu/
    public boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
        if (sx == fx && sy == fy) {
            return t != 1;
        }
        return Math.max(Math.abs(sx - fx), Math.abs(sy - fy)) <= t;
    }

}
