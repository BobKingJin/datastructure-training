package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Date: 2025/8/30 15:36
 * @Author: BobKing
 * @Description:
 */
public class Number3272 {

    // 参考: https://leetcode.cn/problems/find-the-count-of-good-integers/solutions/2899725/mei-ju-suo-you-hui-wen-shu-zu-he-shu-xue-3d35/?envType=daily-question&envId=2025-08-26
    public long countGoodIntegers(int n, int k) {
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        long ans = 0;
        Set<String> vis = new HashSet<>();
        int base = (int) Math.pow(10, (n - 1) / 2);
        for (int i = base; i < base * 10; i++) {
            String s = Integer.toString(i);
            // 后面部分是回文的右半部分, 加上回文的左半部分s, 就得到完整的回文
            // n % 2是考虑到数位奇偶的情况
            s += new StringBuilder(s).reverse().substring(n % 2);
            // 回文数不能被 k 整除
            if (Long.parseLong(s) % k > 0) {
                continue;
            }
            char[] sortedS = s.toCharArray();
            Arrays.sort(sortedS);
            if (!vis.add(new String(sortedS))) {
                continue;
            }
            int[] cnt = new int[10];
            for (char c : sortedS) {
                cnt[c - '0']++;
            }
            int res = (n - cnt[0]) * factorial[n - 1];
            for (int c : cnt) {
                res /= factorial[c];
            }
            ans += res;
        }
        return ans;
    }

}
