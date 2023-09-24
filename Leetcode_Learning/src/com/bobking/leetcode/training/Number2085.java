package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

public class Number2085 {

    public int countWords(String[] words1, String[] words2) {

        Set<String> set = new HashSet<String>();
        int m = words1.length;
        Set<String> ones = new HashSet<String>();

        for (String word : words1) {
            if (set.add(word)) {
                ones.add(word);
            } else {
                ones.remove(word);
            }
        }

        Set<String> secOnes = new HashSet<String>();
        set.clear();

        for (String word : words2) {
            if (set.add(word) && ones.contains(word)) {
                secOnes.add(word);
            } else {
                secOnes.remove(word);
            }
        }

        return secOnes.size();
    }
}
