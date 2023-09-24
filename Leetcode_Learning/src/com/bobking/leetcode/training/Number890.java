package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-06-12 10:05
 */
public class Number890 {

    // 参考：https://leetcode.cn/problems/find-and-replace-pattern/solution/by-ac_oier-s4cw/
    public List<String> findAndReplacePattern(String[] words, String pattern) {

        List<String> ans = new ArrayList<String>();
        // 使用 map 记录具体的映射关系，使用 vis 记录哪些字符已被映射
        int[] map = new int[26];
        int[] vis = new int[26];

        for (String s : words) {
            Arrays.fill(map, -1);
            Arrays.fill(vis, 0);
            boolean ok = true;

            for (int i = 0; i < pattern.length() && ok; i++) {
                int c1 = s.charAt(i) - 'a';
                int c2 = pattern.charAt(i) - 'a';
                if (map[c1] == -1 && vis[c2] == 0) {
                    map[c1] = c2;
                    vis[c2] = 1;
                } else if (map[c1] != c2) {
                    ok = false;
                }
            }

            if (ok)
                ans.add(s);
        }

        return ans;
    }
}
