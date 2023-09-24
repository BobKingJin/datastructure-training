package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-09 9:52
 */
public class Number1718 {

    private int n;

    private int[] ans;

    private boolean[] visited;

    public int[] constructDistancedSequence(int n) {
        this.n = n;
        ans = new int[n * 2 - 1];
        visited = new boolean[n + 1];
        backtrack(0);
        return ans;
    }

    private boolean backtrack(int idx) {

        if (idx >= ans.length)
            return true;

        if (ans[idx] != 0)
            return backtrack(idx + 1);

        for (int i = n; i > 1; i--) {

            if (!visited[i] && idx + i < ans.length && ans[idx + i] == 0) {

                ans[idx] = i;
                ans[idx + i] = i;
                visited[i] = true;

                if (backtrack(idx + 1))
                    return true;

                ans[idx] = 0;
                ans[idx + i] = 0;
                visited[i] = false;
            }
        }

        if (!visited[1]) {
            ans[idx] = 1;
            visited[1] = true;
            if (backtrack(idx + 1))
                return true;
            ans[idx] = 0;
            visited[1] = false;
        }
        return false;
    }

}
