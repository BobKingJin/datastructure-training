package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2021-03-20 20:24
 */
public class Number139 {

    // 参考：https://leetcode-cn.com/problems/word-break/solution/dan-ci-chai-fen-by-leetcode-solution/
    public boolean wordBreak1(String s, List<String> wordDict) {

        if (s == null || s.length() < 1 || wordDict == null || wordDict.size() < 1)
            return false;
        // 去重
        Set<String> set = new HashSet<String>(wordDict);
        // dp[i] 表示字符串 s 前 i 个字符组成的字符串 s[0...(i - 1)] 是否能被空格拆分成若干个字典中出现的单词
        boolean[] dp = new boolean[s.length() + 1];
        // 空串
        dp[0] = true;
        // 需要枚举 s[0...i - 1] 中的分割点 j，看 s[0...j - 1] 组成的字符串 s1 （默认 j = 0 时 s1 为空串）和
        // s[j..i - 1] 组成的字符串 s2 是否都合法
        // 从前往后，后面依赖前面
        for (int i = 1; i <= s.length(); i++) {
            // 尝试 0 - (i - 1) 中的每个位置
            for (int j = 0; j < i; j++) {
                // 只要有一个符合，即可跳出循环
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    // 参考：https://leetcode-cn.com/problems/word-break/solution/shou-hui-tu-jie-san-chong-fang-fa-dfs-bfs-dong-tai/
    // BFS
    public boolean wordBreak2(String s, List<String> wordDict) {

        if (s == null || s.length() < 1 || wordDict == null || wordDict.size() < 1)
            return false;

        Queue<Integer> queue = new ArrayDeque<Integer>();
        boolean[] visit = new boolean[s.length()];
        queue.offer(0);

        while (!queue.isEmpty()) {
            int start = queue.poll();
            // 访问过的，跳过
            if (visit[start] == true)
                continue;
            // 未访问过的，记录一下
            visit[start] = true;
            for (int i = start + 1; i <= s.length(); i++) {
                String curStr = s.substring(start, i);
                if (wordDict.contains(curStr)) {
                    // i 还没越界，还能继续划分，让它入列，作为下一层待考察的节点
                    if (i < s.length()) {
                        queue.add(i);
                    } else {
                        // i == len，指针越界，说明 s 串一路被切出单词，现在没有剩余子串，返回 true
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // 参考：https://leetcode-cn.com/problems/word-break/solution/shou-hui-tu-jie-san-chong-fang-fa-dfs-bfs-dong-tai/
    // DFS
    public boolean wordBreak3(String s, List<String> wordDict) {

        if (s == null || s.length() < 1 || wordDict == null || wordDict.size() < 1)
            return false;

        // 用一个数组，存储计算的结果，数组索引为指针位置，值为计算的结果
        // 下次遇到相同的子问题，直接返回数组中的缓存值，就不用进入重复的递归
        int[] visited = new int[s.length()];
        return dfs(s, 0, wordDict, visited);
    }

    private boolean dfs(String s, int start, List<String> wordDict, int[] visited) {

        // start 来到字符串末尾的「下一位」 说明匹配成功
        if (start == s.length())
            return true;

        // 剪枝 防止重复计算
        if (visited[start] == 1)
            return true;
        if (visited[start] == -1)
            return false;
        // 从 end 开始一步步尝试
        for (int end = start + 1; end <= s.length(); end++) {
            // 包含 start，不包含 end，所以 end 可以取到 s.length()
            String pre = s.substring(start, end);
            if (wordDict.contains(pre) &&
                    // 判断后面部分能不能匹配
                    dfs(s, end, wordDict, visited)) {
                // 标记匹配成功
                visited[start] = 1;
                return true;
            }
        }
        // 标记匹配失败
        visited[start] = -1;
        return false;
    }

}
