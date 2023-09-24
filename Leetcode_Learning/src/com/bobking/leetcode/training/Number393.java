package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-03-13 8:49
 */
public class Number393 {

    // 从前往后处理每个 data[i]，先统计 data[i] 从第 7 位开始往后有多少位连续的 1，代表这是一个几字节的字符，记为 cnt
    // 如果 cnt 为 1 或者大于 4 均违反编码规则（与字符长度为 1 时的编码规则 和 字符长度只能是 1 到 4 冲突），返回 False
    // 如果位置 i 后面不足 cnt - 1 也返回 False
    // 否则检查下标范围为 [i + 1, i + cnt - 1] 的数是否满足前两位为 10 的要求，若不满足返回 False
    public boolean validUtf8(int[] data) {

        if(data == null || data.length == 0)
            return false;

        int n = data.length;
        for (int i = 0; i < n; ) {

            int t = data[i];
            int j = 7;
            // 判断从第 7 位开始往后有多少位连续的 1
            while (j >= 0 && (((t >> j) & 1) == 1))
                j--;
            int cnt = 7 - j;
            // cnt = 1 说明是一个字节，但是对于 1 字节 的字符，字节的第一位设为 0
            if (cnt == 1 || cnt > 4)
                return false;

            if (i + cnt - 1 >= n)
                return false;

            for (int k = i + 1; k < i + cnt; k++) {
                if ((((data[k] >> 7) & 1) == 1) && (((data[k] >> 6) & 1) == 0))
                    continue;

                return false;
            }

            if (cnt == 0) {
                i++;
            } else {
                i += cnt;
            }
        }

        return true;
    }

}
