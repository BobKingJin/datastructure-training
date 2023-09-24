package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-06-10 23:29
 */
public class Number409 {

    // 参考：https://leetcode.cn/problems/longest-palindrome/solution/javade-2chong-shi-xian-fang-fa-by-sweetiee/
    public int longestPalindrome(String s) {

        int[] cnt = new int[58];
        for (char c : s.toCharArray())
            cnt[c - 'A'] += 1;

        int ans = 0;
        for (int x : cnt)
            // 回文串里每种字符都出现了偶数次，除了奇数长度的回文串的时候最中间的那个字符可以出现奇数次
            // 比如回文串 abba，每个字符都出现了偶数次。而奇数长度的回文串abcbcbcba，c出现了奇数次
            // 因为其实奇数也是可以组成回文串的，这个位置其实不用担心因为奇数，所以不论能否组成回文串，都去 -1，在最后结果判断处其实会做处理
            // 所以这个位置其实可以直接 -1
            // 字符出现的次数最多用偶数次
            // 奇数次的只能出现一个
            ans += x - (x & 1);

        // 如果最终的长度小于原字符串的长度，说明里面某个字符出现了奇数次，那么那个字符可以放在回文串的中间，所以额外再加一
        return ans < s.length() ? ans + 1 : ans;
    }
}
