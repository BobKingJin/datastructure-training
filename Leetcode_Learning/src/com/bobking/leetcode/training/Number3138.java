package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @Date: 2025/2/22 10:30
 * @Author: BobKing
 * @Description:
 */
public class Number3138 {

    public int minAnagramLength1(String s) {
        char[] ch = s.toCharArray();
        int n = s.length();

        next:
        for (int k = 1; k <= n / 2; k++) {
            if (n % k > 0) {
                continue;
            }
            int[] cnt0 = new int[26];
            for (int j = 0; j < k; j++) {
                cnt0[ch[j] - 'a']++;
            }
            for (int i = k * 2; i <= n; i += k) {
                int[] cnt = new int[26];
                for (int j = i - k; j < i; j++) {
                    cnt[ch[j] - 'a']++;
                }
                if (!Arrays.equals(cnt, cnt0)) {
                    continue next;
                }
            }
            return k;
        }
        return n;
    }

    public int minAnagramLength2(String s) {
        char[] ch = s.toCharArray();
        int n = s.length();
        int[] cntAll = new int[26];
        for (char c : ch) {
            cntAll[c - 'a']++;
        }
        int g = 0;
        for (int c : cntAll) {
            g = gcd(g, c);
        }
        next:
        for (int times = g; times > 1; times--) {
            if (g % times > 0) {
                continue;
            }
            int k = n / times;
            int[] cnt0 = new int[26];
            for (int j = 0; j < k; j++) {
                cnt0[ch[j] - 'a']++;
            }
            for (int i = k * 2; i <= n; i += k) {
                int[] cnt = new int[26];
                for (int j = i - k; j < i; j++) {
                    cnt[ch[j] - 'a']++;
                }
                if (!Arrays.equals(cnt, cnt0)) {
                    continue next;
                }
            }
            return k;
        }
        return n;
    }

    private int gcd(int a, int b) {
        while (a != 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }


}
