package com.bobking.leetcode.training;

public class Number868 {

    public int binaryGap(int n) {

        int res = 0;

        for (int i = 31, j = -1; i >= 0; i--) {
            if (((n >> i) & 1) == 1) {
                if (j != -1)
                    res = Math.max(res, j - i);
                j = i;
            }
        }

        return res;
    }
}
