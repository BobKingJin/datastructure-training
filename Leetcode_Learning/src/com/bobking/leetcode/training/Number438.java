package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Number438 {

    // 参考：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/solution/438-zhao-dao-zi-fu-chuan-zhong-suo-you-z-nx6b/
    // 思路：怎么去让 s 中的字符去匹配 p 呢？不是从 s 的每一位 i 开始，然后从 i (i + 1) (i + 2) ... 去匹配 p
    // 而是直接用一个数组去统计 p 中字符，然后判断 s 中的连续子序列去匹配这个统计数组即可
    public List<Integer> findAnagrams1(String s, String p) {

        List<Integer> res = new ArrayList<Integer>();

        if (s == null || p == null || "".equals(s) || "".equals(p) || s.length() < p.length())
            return res;

        int m = s.length();
        int n = p.length();

        int[] m_count = new int[26];
        int[] n_count = new int[26];

        // 结束角标为 (n - 1)
        for (int i = 0; i < n; i++) {
            m_count[s.charAt(i) - 'a']++;
            n_count[p.charAt(i) - 'a']++;
        }

        if (Arrays.equals(m_count, n_count))
            res.add(0);

        // 从 i = n 开始遍历
        for (int i = n; i < m; i++) {
            m_count[s.charAt(i) - 'a']++;
            // 往右移动一位，则前面的字符要去掉，第 (i - n) 位去掉
            m_count[s.charAt(i - n) - 'a']--;
            if (Arrays.equals(m_count, n_count))
                res.add(i - n + 1);
        }
        return res;
    }

    // 参考：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/solution/438-zhao-dao-zi-fu-chuan-zhong-suo-you-z-nx6b/
    public List<Integer> findAnagrams2(String s, String p) {

        List<Integer> res = new ArrayList<>();

        if (s == null || p == null || "".equals(s) || "".equals(p) || s.length() < p.length())
            return res;

        int m = s.length();
        int n = p.length();

        int[] m_count = new int[26];
        int[] n_count = new int[26];

        for (int i = 0; i < n; i++)
            n_count[p.charAt(i) - 'a']++;

        int left = 0;
        for (int right = 0; right < m; right++) {
            int curRight = s.charAt(right) - 'a';
            m_count[curRight]++;
            // 滑动窗口
            while (m_count[curRight] > n_count[curRight]) {
                int curLeft = s.charAt(left) - 'a';
                m_count[curLeft]--;
                left++;
            }
            if (right - left + 1 == n)
                res.add(left);
        }
        return res;
    }
}
