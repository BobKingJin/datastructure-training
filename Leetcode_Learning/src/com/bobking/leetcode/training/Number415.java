package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-11-02 8:02
 */
public class Number415 {

    public String addStrings(String num1, String num2) {

        StringBuilder res = new StringBuilder("");

        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int ca = 0;

        while(i >= 0 || j >= 0){
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int tmp = n1 + n2 + ca;
            ca = tmp / 10;
            res.append(tmp % 10);
            i--;
            j--;
        }

        if(ca == 1)
            res.append(1);

        return res.reverse().toString();
    }
}
