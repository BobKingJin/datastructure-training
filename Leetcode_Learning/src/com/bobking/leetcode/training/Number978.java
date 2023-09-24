package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-07-30 21:31
 */
public class Number978 {

    // 参考：https://leetcode.cn/problems/longest-turbulent-subarray/solution/yi-zhang-dong-tu-xiang-jie-dong-tai-gui-wrwvn/
    public int maxTurbulenceSize(int[] arr) {

        // 需要定义两个状态数组，分别表示以 i 结尾的在增长和降低的最长湍流子数组长度
        // 状态的定义：
        // 定义 up[i] 表示以位置 i 结尾的，并且 arr[i - 1] < arr[i] 的最长湍流子数组长度
        // 定义 down[i] 表示以位置 i 结尾的，并且 arr[i - 1] > arr[i] 的最长湍流子数组长度
        // up[i] 和 down[i] 初始化都是 1，因为每个数字本身都是一个最小的湍流子数组
        // 状态转移方程：
        // up[i] = down[i - 1] + 1，当 arr[i - 1] < arr[i]；
        // down[i] = up[i - 1] + 1，当 arr[i - 1] > arr[i]；

        int res = 1;
        int up = 1;
        int down = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] < arr[i]) {
                up = down + 1;
                down = 1;
            } else if (arr[i - 1] > arr[i]) {
                down = up + 1;
                up = 1;
            } else {
                up = 1;
                down = 1;
            }
            res = Math.max(res, Math.max(up, down));
        }
        return res;
    }
}
