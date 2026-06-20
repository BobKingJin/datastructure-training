package com.bobking.leetcode.training;

import java.util.HashMap;

/**
 * @Date: 2026/6/20 16:34
 * @Author: BobKing
 * @Description:
 */
public class Jianzhi75 {

    private StringBuilder s = new StringBuilder();
    private HashMap<Character, Integer> mp = new HashMap<Character, Integer>();

    // 参考: Number387
    public void Insert(char ch) {
        s.append(ch);
        mp.put(ch, mp.getOrDefault(ch, 0) + 1);
    }

    // return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        for (int i = 0; i < s.length(); i++) {
            if (mp.get(s.charAt(i)) == 1) {
                return s.charAt(i);
            }
        }
        return '#';
    }

}
