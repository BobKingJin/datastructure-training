package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2023-05-25 7:30
 */
public class Number2451 {

    public String oddString(String[] words) {

        Map<String, Integer> map = new HashMap<String, Integer>(2);
        int[] diff = new int[words[0].length() - 1];
        String cur = "";

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < words[i].length() - 1; j++)
                diff[j] = words[i].charAt(j + 1) - words[i].charAt(j);
            map.put(Arrays.toString(diff), i);
        }

        for (int i = 2; i < words.length; i++) {
            for (int j = 0; j < words[i].length() - 1; j++)
                diff[j] = words[i].charAt(j + 1) - words[i].charAt(j);

            cur = Arrays.toString(diff);
            if (map.size() == 1) {
                if (!map.containsKey(cur))
                    return words[i];
            } else {
                return words[1 - map.get(cur)];
            }
        }

        return "";
    }
}
