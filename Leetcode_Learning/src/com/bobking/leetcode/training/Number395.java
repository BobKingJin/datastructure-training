package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-05-14 11:05
 */
public class Number395 {

    // 参考：https://leetcode.cn/problems/longest-substring-with-at-least-k-repeating-characters/solution/xiang-jie-mei-ju-shuang-zhi-zhen-jie-fa-50ri1/
    public int longestSubstring(String s, int k) {
        
        int res = 0;
        int n = s.length();
        char[] cs = s.toCharArray();
        // 题目说明了只包含小写字母（26 个，为有限数据），可以枚举最大长度所包含的字符类型数量
        // 答案必然是 [1, 26]，即最少包含 1 个字母，最多包含 26 个字母
        int[] cnt = new int[26];
        for (int p = 1; p <= 26; p++) {
            Arrays.fill(cnt, 0);
            // tot 代表 [j, i] 区间所有的字符种类数量  sum 代表满足「出现次数不少于 k」的字符种类数量
            for (int i = 0, j = 0, tot = 0, sum = 0; i < n; i++) {
                int u = cs[i] - 'a';
                cnt[u]++;
                // 如果添加到 cnt 之后为 1，说明字符总数 + 1
                if (cnt[u] == 1)
                    tot++;
                // 如果添加到 cnt 之后等于 k，说明该字符从不达标变为达标，达标数量 + 1
                if (cnt[u] == k)
                    sum++;
                // 当区间所包含的字符种类数量 tot 超过了当前限定的数量 p，那么要删除掉一些字母，即「左指针」右移
                while (tot > p) {
                    int t = cs[j++] - 'a';
                    cnt[t]--;
                    // 如果添加到 cnt 之后为 0，说明字符总数 - 1
                    if (cnt[t] == 0)
                        tot--;
                    // 如果添加到 cnt 之后等于 k - 1，说明该字符从达标变为不达标，达标数量 - 1
                    if (cnt[t] == k - 1)
                        sum--;
                }
                // 当所有字符都符合要求，更新答案
                if (tot == sum)
                    res = Math.max(res, i - j + 1);
            }
        }

        return res;
    }
}
