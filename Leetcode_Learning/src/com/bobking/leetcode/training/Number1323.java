package com.bobking.leetcode.training;

public class Number1323 {

    public int maximum69Number(int num) {

        int ans = num;
        int digit = 1;
        int modifyDigit = 0;

        // 找到 num 中最高位的 6，将其翻转成 9
        while(num != 0){

            if(num % 10 == 6)
                modifyDigit = digit;

            digit *= 10;
            num /= 10;
        }

        // 找到最高位的 6，将其转换为 9，那么两个相差的是 modifyDigit * 3
        ans += modifyDigit * 3;
        return ans;
    }
}
