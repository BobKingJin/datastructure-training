package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BobKing
 * @create 2022-11-17 22:38
 */
public class Number792 {

    // 参考：https://leetcode.cn/problems/number-of-matching-subsequences/solution/by-ac_oier-u1ox/
    public int numMatchingSubseq(String s, String[] words) {

        int n = s.length();
        int ans = 0;
        Map<Character, List<Integer>> map = new HashMap<Character, List<Integer>>();
        for (int i = 0; i < n; i++) {
            List<Integer> list = map.getOrDefault(s.charAt(i), new ArrayList<Integer>());
            list.add(i);
            map.put(s.charAt(i), list);
        }

        for (String w : words) {

            boolean ok = true;
            int m = w.length();
            int idx = -1;

            for (int i = 0; i < m && ok; i++) {
                List<Integer> list = map.getOrDefault(w.charAt(i), new ArrayList<Integer>());
                int l = 0;
                int r = list.size() - 1;
                while (l < r) {
                    int mid = l + r >> 1;
                    if (list.get(mid) > idx) {
                        r = mid;
                    } else {
                        l = mid + 1;
                    }
                }
                if (r < 0 || list.get(r) <= idx) {
                    ok = false;
                } else {
                    idx = list.get(r);
                }
            }

            if (ok)
                ans++;
        }
        return ans;
    }
}
