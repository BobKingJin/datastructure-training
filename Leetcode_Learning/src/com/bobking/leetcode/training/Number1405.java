package com.bobking.leetcode.training;

import java.util.PriorityQueue;

/**
 * @author BobKing
 * @create 2022-09-10 20:45
 */
public class Number1405 {

    // 参考：https://leetcode.cn/problems/longest-happy-string/solution/gong-shui-san-xie-jie-he-you-xian-dui-li-q6fd/
    public String longestDiverseString(int a, int b, int c) {

        PriorityQueue<int[]> q = new PriorityQueue<int[]>((x, y) -> y[1] - x[1]);

        if (a > 0)
            q.add(new int[]{0, a});
        if (b > 0)
            q.add(new int[]{1, b});
        if (c > 0)
            q.add(new int[]{2, c});

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int n = sb.length();
            if (n >= 2 && sb.charAt(n - 1) - 'a' == cur[0] && sb.charAt(n - 2) - 'a' == cur[0]) {
                if (q.isEmpty())
                    break;
                int[] next = q.poll();
                sb.append((char) (next[0] + 'a'));
                if (--next[1] != 0)
                    q.add(next);
                q.add(cur);
            } else {
                sb.append((char) (cur[0] + 'a'));
                if (--cur[1] != 0)
                    q.add(cur);
            }
        }
        return sb.toString();
    }
}
