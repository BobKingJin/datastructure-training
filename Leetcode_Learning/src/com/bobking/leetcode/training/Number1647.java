package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

/**
 * @author BobKing
 * @create 2023-02-17 14:47
 */
public class Number1647 {

    // 参考：https://leetcode.cn/problems/minimum-deletions-to-make-character-frequencies-unique/solution/tan-xin-si-xiang-tong-ji-qu-zhong-bu-xu-yao-pai-xu/
    public int minDeletions(String s) {

        int[] a = new int[26];
        char[] cs = s.toCharArray();
        for (char c : cs)
            a[c - 'a'] ++;

        Set<Integer> h = new HashSet<Integer>();
        int res = 0;
        for (int i : a) {
            if (i != 0) {
                // set已经包含就自减
                while (h.contains(i)) {
                    i--;
                    res++;
                }
                if (i != 0)
                    // 自减到 0 时，表示完全删除了某个字母，不能加入set中
                    h.add(i);
            }
        }
        return res;
    }
}
