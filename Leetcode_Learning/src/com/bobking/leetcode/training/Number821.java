package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Number821 {

    // 参考：https://leetcode-cn.com/problems/shortest-distance-to-a-character/solution/by-ac_oier-5bjs/
    public int[] shortestToChar1(String s, char c) {
        
        int n = s.length();
        int[] res = new int[n];
        Arrays.fill(res, n + 1);
        
        for (int i = 0, j = -1; i < n; i++) {
            // j 为从左到右最近一次出现字符 c 的位置
            if (s.charAt(i) == c) 
                j = i;
            if (j != -1) 
                res[i] = i - j;
        }
        
        for (int i = n - 1, j = -1; i >= 0; i--) {
            if (s.charAt(i) == c) 
                j = i;
            if (j != -1)
                res[i] = Math.min(res[i], j - i);
        }

        return res;
    }

    // 参考：https://leetcode-cn.com/problems/shortest-distance-to-a-character/solution/by-ac_oier-5bjs/
    public int[] shortestToChar2(String s, char c) {
        
        int n = s.length();
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Deque<Integer> d = new ArrayDeque<Integer>();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) {
                d.addLast(i);
                res[i] = 0;
            }
        }

        int[] dirs = new int[]{-1, 1};
        while (!d.isEmpty()) {
            int t = d.pollFirst();
            for (int di : dirs) {
                int ne = t + di;
                if (ne >= 0 && ne < n && res[ne] == -1) {
                    res[ne] = res[t] + 1;
                    d.addLast(ne);
                }
            }
        }

        return res;
    }
}
