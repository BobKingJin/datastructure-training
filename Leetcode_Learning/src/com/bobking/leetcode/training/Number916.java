package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

public class Number916 {

    // 参考：https://leetcode.cn/problems/word-subsets/solution/dan-ci-zi-ji-by-leetcode/
    public List<String> wordSubsets(String[] words1, String[] words2) {

        // 例如：检验 "warrior" 是否是 B = ["wrr", "wa", "or"] 的超集时，可以按照字母出现的最多次数将 B 中所有单
        // 词合并成一个单词 "arrow"，然后判断一次即可

        int[] bmax = count("");
        for (String b : words2) {
            int[] bCount = count(b);
            for (int i = 0; i < 26; ++i)
                bmax[i] = Math.max(bmax[i], bCount[i]);
        }

        List<String> ans = new ArrayList<String>();
        search:
        for (String a : words1) {
            int[] aCount = count(a);
            for (int i = 0; i < 26; ++i)
                if (aCount[i] < bmax[i])
                    continue search;
            ans.add(a);
        }

        return ans;
    }

    private int[] count(String S) {
        int[] ans = new int[26];
        for (char c : S.toCharArray())
            ans[c - 'a']++;
        return ans;
    }
}
