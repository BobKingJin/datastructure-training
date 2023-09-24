package com.bobking.leetcode.training;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author BobKing
 * @create 2021-03-13 21:22
 */
public class Number451 {

    // 参考：https://leetcode-cn.com/problems/sort-characters-by-frequency/solution/gong-shui-san-xie-shu-ju-jie-gou-yun-yon-gst9/
    public String frequencySort(String s) {

        if (s == null || s.length() < 2)
            return s;

        char[] ch = s.toCharArray();
        // A 的 acsii码值为 65，z 的 acsii码值为 122
        int[] frequency = new int[128];
        for (int i = 0; i < ch.length; i++)
            frequency[ch[i]]++;
        // 大根堆
        PriorityQueue<Character> queue = new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return frequency[o2] - frequency[o1];
            }
        });

        for (int i = 0; i < frequency.length; i++) {
            if (frequency[i] != 0)
                queue.offer((char) i);
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            char c = queue.poll();
            for (int i = 0; i < frequency[c]; i++)
                sb.append(c);
        }

        return sb.toString();
    }

}
