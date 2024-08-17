package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-03-26 21:45
 */
public class Number13 {

    // 参考：https://leetcode-cn.com/problems/roman-to-integer/solution/yong-shi-9993nei-cun-9873jian-dan-jie-fa-by-donesp/
    public int romanToInt(String s) {

        if(s == null || s.length() == 0)
            return 0;

        int sum = 0;
        int preNum = getValue(s.charAt(0));

        for(int i = 1; i < s.length(); i ++) {
            int num = getValue(s.charAt(i));
            if(preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        // 注意最后一个数还得加上
        sum += preNum;
        return sum;
    }

    private int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
}
