package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-12 7:16
 */
public class Jianzhi46 {

    public int translateNum1(int num) {
        String strNum = String.valueOf(num);
        return dfs(strNum, 0);
    }

    private int dfs(String strNum, int index) {

        if (index == strNum.length() - 1 || index == strNum.length())
            return 1;

        int curAndNextNum = Integer.parseInt(
                strNum.substring(index, index + 1)
                        + strNum.substring(index + 1, index + 2)
        );
        // 1 位必定合法，所以这个位置 dfs(strNum, index + 1) 直接加上即可，不用判断
        if (curAndNextNum >= 10 && curAndNextNum <= 25) {
            return dfs(strNum, index + 1) + dfs(strNum, index + 2);
        } else {
            return dfs(strNum, index + 1);
        }
    }

    public int translateNum2(int num) {

        // f(n) = f(n - 1) + f(n - 2)
        String s = String.valueOf(num);
        int a = 1;
        int b = 1;

        for(int i = 2; i <= s.length(); i++) {
            String tmp = s.substring(i - 2, i);
            int c = tmp.compareTo("10") >= 0 && tmp.compareTo("25") <= 0 ? a + b : a;
            b = a;
            a = c;
        }
        return a;
    }
}
