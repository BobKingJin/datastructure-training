package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

/**
 * @Date: 2024/2/21 23:29
 * @Author: BobKing
 * @Description:
 */
public class Number3043 {

    public int longestCommonPrefix(int[] arr1, int[] arr2) {

        Set<String> st = new HashSet<String>();
        for (int x : arr1) {
            String s = Integer.toString(x);
            for (int i = 1; i <= s.length(); i++)
                st.add(s.substring(0, i));
        }

        int ans = 0;
        for (int x : arr2) {
            String s = Integer.toString(x);
            for (int i = 1; i <= s.length(); i++) {
                if (!st.contains(s.substring(0, i)))
                    break;
                ans = Math.max(ans, i);
            }
        }
        return ans;
    }
}
