package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2025/5/24 11:14
 * @Author: BobKing
 * @Description:
 */
public class Number2942 {

    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].indexOf(x) >= 0) {
                ans.add(i);
            }
        }
        return ans;
    }

}
