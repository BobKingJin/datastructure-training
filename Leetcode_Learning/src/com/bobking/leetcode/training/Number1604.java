package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2023-02-07 10:42
 */
public class Number1604 {

    public List<String> alertNames(String[] keyName, String[] keyTime) {

        Map<String, List<Integer>> d = new HashMap<String, List<Integer>>();
        for (int i = 0; i < keyName.length; ++i) {
            String name = keyName[i];
            String time = keyTime[i];
            int t = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3));
            d.computeIfAbsent(name, k -> new ArrayList<Integer>()).add(t);
        }

        List<String> ans = new ArrayList<String>();
        for (Map.Entry<String, List<Integer>> e : d.entrySet()) {
            List<Integer> ts = e.getValue();
            int n = ts.size();
            if (n > 2) {
                Collections.sort(ts);
                for (int i = 0; i < n - 2; ++i) {
                    if (ts.get(i + 2) - ts.get(i) <= 60) {
                        ans.add(e.getKey());
                        break;
                    }
                }
            }
        }
        Collections.sort(ans);
        return ans;
    }
}
