package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2023-11-04 7:23
 */
public class Number241 {

    char[] cs;

    public List<Integer> diffWaysToCompute(String expression) {

        cs = expression.toCharArray();
        return dfs(0, cs.length - 1);
    }

    private List<Integer> dfs(int l, int r) {

        List<Integer> ans = new ArrayList<Integer>();

        for (int i = l; i <= r; i++) {
            if (cs[i] >= '0' && cs[i] <= '9')
                continue;
            List<Integer> left = dfs(l, i - 1);
            List<Integer> right = dfs(i + 1, r);
            for (int a : left) {
                for (int b : right) {
                    int cur = 0;
                    if (cs[i] == '+') {
                        cur = a + b;
                    } else if (cs[i] == '-') {
                        cur = a - b;
                    } else {
                        cur = a * b;
                    }
                    ans.add(cur);
                }
            }
        }

        if (ans.isEmpty()) {
            int cur = 0;
            for (int i = l; i <= r; i++)
                cur = cur * 10 + (cs[i] - '0');
            ans.add(cur);
        }

        return ans;
    }
}
