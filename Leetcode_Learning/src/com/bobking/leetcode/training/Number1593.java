package com.bobking.leetcode.training;

import java.util.HashSet;

/**
 * @author BobKing
 * @create 2023-06-02 9:20
 */
public class Number1593 {

    private int ans = 1 ;
    private HashSet<String> set = new HashSet<String>();

    public int maxUniqueSplit(String s) {
        dfs(s, 0);
        return ans;
    }

    private void dfs(String s, int begin) {

        // 剪枝
        // 剩下的字符串，即使每个字符作为一个子字符串，总数目也比目前的 ans 小，就不用计算了
        if (set.size() + s.length() - begin <= ans)
            return;

        if (begin == s.length()) {
            ans = Math.max(ans, set.size());
            return;
        }

        for (int i = begin; i < s.length(); i++) {
            String sub = s.substring(begin, i + 1);
            if (set.add(sub)) {
                dfs(s, i + 1);
                // 回溯
                set.remove(sub);
            }
        }
    }
}
