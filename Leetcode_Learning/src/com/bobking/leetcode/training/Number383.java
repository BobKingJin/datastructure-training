package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-06-12 11:09
 */
public class Number383 {

    // 参考：https://leetcode.cn/problems/ransom-note/solution/gong-shui-san-xie-jian-dan-ji-shu-mo-ni-udpzn/
    public boolean canConstruct(String ransomNote, String magazine) {

        int[] cnt = new int[26];
        for (char c : magazine.toCharArray())
            cnt[c - 'a']++;

        for (char c : ransomNote.toCharArray())
            if (--cnt[c - 'a'] < 0)
                return false;

        return true;
    }
}
