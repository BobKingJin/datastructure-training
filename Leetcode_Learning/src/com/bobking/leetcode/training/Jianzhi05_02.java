package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-31 6:39
 */
public class Jianzhi05_02 {

    // 参考：https://leetcode.cn/problems/binary-number-to-string-lcci/solution/zheng-ming-zhi-duo-xun-huan-6-ci-pythonj-b6sj/
    public String printBin(double num) {

        StringBuilder sb = new StringBuilder("0.");
        for (int i = 0; i < 6; ++i) { // 至多循环 6 次
            num *= 2;
            if (num < 1)
                sb.append('0');
            else {
                sb.append('1');
                if (--num == 0)
                    return sb.toString();
            }
        }
        return "ERROR";
    }
}
