package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-02-20 20:38
 */
public class Number2424 {

    // 参考：https://leetcode.cn/problems/longest-uploaded-prefix/comments/
    private class LUPrefix {

        private int[] st;

        private int n;
        private int m;

        public LUPrefix(int n) {
            this.n = n;
            this.st = new int[n + 1];
        }

        public void upload(int video) {
            st[video] = 1;
            while (m + 1 <= n && st[m + 1] > 0)
                m++;
        }

        public int longest() {
            return m;
        }
    }
}
