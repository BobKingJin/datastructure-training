package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-08-06 22:55
 */
public class Number233 {

    // 参考：https://leetcode.cn/problems/number-of-digit-one/solution/gong-shui-san-xie-jiang-shu-wei-dp-wen-t-c9oi/
    public int countDigitOne(int n) {

        // 对于一个长度为 m 的数字 n，可以计算其在「个位（从右起第 1 位）」、「十位（第 2 位）」、「百位（第 3 位）」和「第 m 位」中 1 出现的次数
        // 假设有 n = abcde，即 m = 5，假设需要统计第 3 位中 1 出现的次数
        // 即可统计满足 --1-- 形式，同时满足 1 <= --1-- <= abcde 要求的数有多少个，称 1 <= --1-- <= abcde 关系为「大小要求」
        // 只需对 c 前后出现的值进行分情况讨论
        // 当 c 前面的部分 < ab，即范围为 [0, ab)，此时必然满足「大小要求」，因此后面的部分可以任意取，即范围为 [0, 99]
        // 根据「乘法原理」，可得知此时数量为 ab * 100
        // 当 c 前面的部分 = ab，这时候「大小关系」主要取决于 c
        // 当 c = 0，必然不满足「大小要求」，数量为 0
        // 当 c = 1，此时「大小关系」取决于后部分，后面的取值范围为 [0, de]，数量为 1 * (de + 1)
        // 当 c > 1，必然满足「大小关系」，后面的部分可以任意取，即范围为 [0, 99]，数量为 1 * 100
        // 当 c 前面的部分 > ab，必然不满足「大小要求」，数量为 0

        String s = String.valueOf(n);
        int m = s.length();
        if (m == 1)
            return n > 0 ? 1 : 0;

        // 计算第 i 位前缀代表的数值，和后缀代表的数值
        // 例如 abcde 则有 ps[2] = ab，ss[2] = de
        int[] ps = new int[m];
        int[] ss = new int[m];
        ss[0] = Integer.parseInt(s.substring(1));
        for (int i = 1; i < m - 1; i++) {
            ps[i] = Integer.parseInt(s.substring(0, i));
            ss[i] = Integer.parseInt(s.substring(i + 1));
        }

        ps[m - 1] = Integer.parseInt(s.substring(0, m - 1));
        // 分情况讨论
        int ans = 0;
        for (int i = 0; i < m; i++) {
            // x 为当前位数值，len 为当前位后面长度为多少
            int x = s.charAt(i) - '0';
            int len = m - i - 1;
            int prefix = ps[i];
            int suffix = ss[i];
            int tot = 0;
            tot += prefix * Math.pow(10, len);
            if (x == 0) {

            } else if (x == 1) {
                tot += suffix + 1;
            } else {
                tot += Math.pow(10, len);
            }
            ans += tot;
        }
        return ans;
    }
}
