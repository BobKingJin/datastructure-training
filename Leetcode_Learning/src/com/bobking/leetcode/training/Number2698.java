package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-10-25 7:31
 */
public class Number2698 {

    private final int[] PRE_SUM = new int[1001];

    private void init() {
        for (int i = 1; i <= 1000; i++) {
            // 把 i^2 转成字符串 s，然后写一个递归，枚举 s 分割出的第一个子串、第二个子串、……，把每个子串对应的整数值 x 加到 sum 中
            char[] s = Integer.toString(i * i).toCharArray();
            PRE_SUM[i] = PRE_SUM[i - 1] + (dfs(s, i, 0, 0) ? i * i : 0);
        }
    }

    public int punishmentNumber(int n) {
        init();
        return PRE_SUM[n];
    }

    private boolean dfs(char[] s, int i, int p, int sum) {

        if (p == s.length)
            return sum == i;

        int x = 0;

        for (int j = p; j < s.length; j++) {
            x = x * 10 + s[j] - '0';
            if (dfs(s, i, j + 1, sum + x))
                return true;
        }
        return false;
    }

}
