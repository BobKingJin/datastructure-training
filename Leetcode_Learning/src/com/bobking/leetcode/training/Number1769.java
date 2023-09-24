package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-12-07 11:04
 */
public class Number1769 {

    // 参考：https://leetcode.cn/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/solution/-by-muse-77-ilaz/
    public int[] minOperations1(String boxes) {

        int[] result = new int[boxes.length()];

        for (int i = 0; i < boxes.length(); i++) {
            if (boxes.charAt(i) == '0')
                continue;
            for (int j = 0; j < result.length; j++)
                // 当发现字符为 1 时，计算每个盒子的操作数
                result[j] += Math.abs(j - i);
        }

        return result;
    }

    // 参考：https://leetcode.cn/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/solution/-by-muse-77-ilaz/
    public int[] minOperations2(String boxes) {

        int[] result = new int[boxes.length()];
        char[] bc = boxes.toCharArray();

        // rc：右侧 1 的总数  lc：左侧 1 的总数
        int rc = 0;
        int lc = (bc[0] == '1' ? 1 : 0);

        for (int i = 1; i < bc.length; i++) {
            if (bc[i] == '1') {
                // 初始化第 1 个盒子操作数
                result[0] += i;
                // 右侧 1 的总数加 1
                rc++;
            }
        }

        for (int i = 1; i < result.length; i++) {
            // 第 N 个盒子操作数
            result[i] = result[i - 1] + lc - rc;
            if (bc[i] == '1') {
                // 重新计算 lc 与 rc 的值
                lc++;
                rc--;
            }
        }
        return result;
    }

}
