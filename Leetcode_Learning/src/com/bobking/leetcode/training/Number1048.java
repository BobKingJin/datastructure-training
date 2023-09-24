package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2023-04-28 8:10
 */
public class Number1048 {

    // 参考：https://leetcode.cn/problems/longest-string-chain/solution/jiao-ni-yi-bu-bu-si-kao-dong-tai-gui-hua-wdkm/
    private Map<String, Integer> ws = new HashMap<String, Integer>();

    public int longestStrChain1(String[] words) {

        // 0 表示未被计算
        for (String s : words)
            ws.put(s, 0);

        int ans = 0;
        for (String s : ws.keySet())
            ans = Math.max(ans, dfs(s));
        return ans;
    }

    private int dfs(String s) {

        int res = ws.get(s);
        // 之前计算过
        if (res > 0)
            return res;
        // 枚举去掉 s[i]
        for (int i = 0; i < s.length(); i++) {
            String t = s.substring(0, i) + s.substring(i + 1);
            if (ws.containsKey(t))
                res = Math.max(res, dfs(t));
        }
        ws.put(s, res + 1);
        return res + 1;
    }

    // 参考：https://leetcode.cn/problems/longest-string-chain/solution/jiao-ni-yi-bu-bu-si-kao-dong-tai-gui-hua-wdkm/
    public int longestStrChain2(String[] words) {

        // 从短的字符串转移到长的字符串
        Arrays.sort(words, (a, b) -> a.length() - b.length());

        Map<String, Integer> f = new HashMap<String, Integer>();

        int ans = 0;

        for (String s : words) {
            int res = 0;
            for (int i = 0; i < s.length(); i++) {
                String t = s.substring(0, i) + s.substring(i + 1);
                res = Math.max(res, f.getOrDefault(t, 0));
            }
            f.put(s, res + 1);
            ans = Math.max(ans, res + 1);
        }
        return ans;
    }
}
