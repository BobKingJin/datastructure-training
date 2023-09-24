package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2022-07-24 8:34
 */
public class Number488 {

    private int INF = 0x3f3f3f3f;
    private String b;
    private int m;
    private Map<String, Integer> map = new HashMap<String, Integer>();

    // 参考：https://leetcode.cn/problems/zuma-game/solution/gong-shui-san-xie-yi-ti-shuang-jie-sou-s-3ftb/
    public int findMinStep(String board, String hand) {
        b = hand;
        m = b.length();
        int ans = dfs(board, 1 << m);
        return ans == INF ? -1 : ans;
    }

    private int dfs(String a, int cur) {

        if (a.length() == 0)
            return 0;

        String hashKey = a + "_" + cur;
        if (map.containsKey(hashKey))
            return map.get(hashKey);
        int ans = INF;
        int n = a.length();
        for (int i = 0; i < m; i++) {
            if (((cur >> i) & 1) == 1)
                continue;
            int next = (1 << i) | cur;
            for (int j = 0; j <= n; j++) {
                boolean ok = false;
                if (j > 0 && j < n && a.charAt(j) == a.charAt(j - 1) && a.charAt(j - 1) != b.charAt(i))
                    ok = true;
                if (j < n && a.charAt(j) == b.charAt(i))
                    ok = true;
                if (!ok)
                    continue;
                StringBuilder sb = new StringBuilder();
                sb.append(a.substring(0, j)).append(b.substring(i, i + 1));
                if (j != n)
                    sb.append(a.substring(j));
                int k = j;
                while (0 <= k && k < sb.length()) {
                    char c = sb.charAt(k);
                    int l = k;
                    int r = k;
                    while (l >= 0 && sb.charAt(l) == c)
                        l--;
                    while (r < sb.length() && sb.charAt(r) == c)
                        r++;
                    if (r - l - 1 >= 3) {
                        sb.delete(l + 1, r);
                        k = l >= 0 ? l : r;
                    } else {
                        break;
                    }
                }
                ans = Math.min(ans, dfs(sb.toString(), next) + 1);
            }
        }
        map.put(hashKey, ans);
        return ans;
    }
}
