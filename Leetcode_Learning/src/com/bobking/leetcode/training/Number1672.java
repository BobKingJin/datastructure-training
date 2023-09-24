package com.bobking.leetcode.training;

public class Number1672 {

    public int maximumWealth(int[][] accounts) {
        
        int m = accounts.length;
        int n = accounts[0].length;
        int res = 0;

        for (int i = 0; i < m; i++) {

            int cur = 0;

            for (int j = 0; j < n; j++)
                cur += accounts[i][j];

            res = Math.max(res, cur);
        }

        return res;
    }
    
}
