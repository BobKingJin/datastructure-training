package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author BobKing
 * @create 2022-04-17 9:43
 */
public class Number819 {

    // 参考：https://leetcode-cn.com/problems/most-common-word/solution/by-ac_oier-6aqd/
    public String mostCommonWord(String paragraph, String[] banned) {
        
        Set<String> set = new HashSet<String>();
        for (String b : banned) 
            set.add(b);
        
        char[] cs = paragraph.toCharArray();
        int n = cs.length;
        String res = null;
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < n; ) {

            if (!Character.isLetter(cs[i]) && ++i >= 0)
                continue;

            int j = i;
            while (j < n && Character.isLetter(cs[j]))
                j++;

            String sub = paragraph.substring(i, j).toLowerCase();
            i = j + 1;
            if (set.contains(sub))
                continue;

            map.put(sub, map.getOrDefault(sub, 0) + 1);

            if (res == null || map.get(sub) > map.get(res))
                res = sub;
        }

        return res;
    }
}
