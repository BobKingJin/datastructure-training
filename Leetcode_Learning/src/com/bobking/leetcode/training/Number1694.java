package com.bobking.leetcode.training;

public class Number1694 {

    public String reformatNumber(String number) {

        if (number.length() <= 3)
            return number;

        int count = 0;
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch >= '0' && ch <= '9') {
                ans.append(ch);
                count++;
                if (count == 3) {
                    ans.append('-');
                    count = 0;
                }
            }
        }

        if (count == 0) {
            ans.deleteCharAt(ans.length() - 1);
        } else if (count == 1) {
            ans.deleteCharAt(ans.length() - 2);
            ans.insert(ans.length() - 2, '-');
        }

        return ans.toString();
    }
}
