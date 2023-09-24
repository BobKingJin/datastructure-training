package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-25 8:41
 */
public class Number788 {

    int[] check = {0, 0, 1, -1, -1, 1, 1, -1, 0, 1};

    // 参考：https://leetcode.cn/problems/rotated-digits/solution/xuan-zhuan-shu-zi-by-leetcode-solution-q9bh/
    public int rotatedDigits(int n) {

        int ans = 0;

        for (int i = 1; i <= n; ++i) {

            String num = String.valueOf(i);
            boolean valid = true;
            boolean diff = false;

            for (int j = 0; j < num.length(); ++j) {
                char ch = num.charAt(j);
                if (check[ch - '0'] == -1) {
                    valid = false;
                } else if (check[ch - '0'] == 1) {
                    diff = true;
                }
            }

            if (valid && diff)
                ++ans;
        }

        return ans;
    }

}
