package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-30 8:59
 */
public class Number2516 {

    // 参考：https://leetcode.cn/problems/take-k-of-each-character-from-left-and-right/solution/on-shuang-zhi-zhen-by-endlesscheng-4g9p/
    public int takeCharacters1(String s, int k) {

        char[] cs = s.toCharArray();
        int[] freq = new int[100];
        for (char c : cs)
            freq[c]++;

        for (int i = 97; i < freq.length; i++)
            if (freq[i] < k)
                return -1;

        int max = 0;
        int n = cs.length;
        int l = 0;
        int r = 0;

        while (r < n) {
            if (--freq[cs[r]] < k) {
                max = Math.max(max, r - l);
                while (freq[cs[r]] < k)
                    ++freq[cs[l++]];
            }
            r++;
        }

        max = Math.max(max, r - l);

        return n - max;
    }

    // 参考：https://leetcode.cn/problems/take-k-of-each-character-from-left-and-right/solution/on-shuang-zhi-zhen-by-endlesscheng-4g9p/
    public int takeCharacters2(String s, int k) {

        // aabaaaacaabc k = 2
        // 前缀      后缀           答案
        //           baaaacaabc     10
        // a         baaaacaabc     11
        // aa        baaaacaabc     12
        // aab       caabc          8
        // aaba      caabc          9
        // aabaa     caabc          10
        // ...       ...            ...
        // 枚举前缀，维护后缀

        char[] cs = s.toCharArray();
        int[] freq = new int[100];
        int n = cs.length;
        int j = n;

        while (freq[97] < k || freq[98] < k || freq[99] < k) {
            if (j <= 0)
                return -1;
            freq[cs[--j]]++;
        }

        int ans = n - j;
        for (int i = 0; i < n && j < n; i++) {
            freq[cs[i]]++;
            while (j < n && freq[cs[j]] > k)
                freq[cs[j++]]--;
            ans = Math.min(ans, i + 1 + n - j);
        }

        return ans;
    }
}
