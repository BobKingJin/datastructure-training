package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-11-05 10:14
 */
public class Number1663 {

    public String getSmallestString1(int n, int k) {

        // 由于要使得构造出的字符串字典序最小，因此可以考虑贪心地从字符串的开头处开始构造，每次选择一个满足要求的最小的字母，即可得到最终答案
        // 那么怎样选择字母才是满足要求的呢？假设当前构造到了某一个位置，包括此位置还剩下 n'个位置没有放入字符，并且这些位置的数值之和为 k'
        // 那么如果放入字母 c，那么剩余 n' - 1位置以及 k' - c的数值之和，必须满足：n′ − 1 ≤ k′− c ≤ 26 * (n′ − 1)
        // 即 k′ − 26 * (n′ − 1) ≤ c ≤ k′ − (n′ − 1)
        // 那么就得到了 c 的取值下限 k' - 26 * (n' - 1)
        // 因此：如果 k' - 26 * (n' - 1) ≤ 0，选择字符 a
        //      如果 k' - 26 * (n' - 1) > 0，选择该数值对应的字符

        String ans = "";

        for (int rest = n; rest >= 1; --rest) {
            int bound = k - 26 * (rest - 1);
            if (bound > 0) {
                ans += bound + 'a' - 1;
                k -= bound;
            } else {
                ans += 'a';
                k -= 1;
            }
        }
        return ans;
    }

    public String getSmallestString2(int n, int k) {

        char[] res = new char[n];
        Arrays.fill(res, 'a');

        int remain = k - n;
        int i = n - 1;

        while (remain > 0) {
            if (remain > 25) {
                remain -= 25;
                res[i--] = 'z';
            } else {
                res[i--] += remain;
                remain = 0;
            }
        }
        return String.valueOf(res);
    }
}
