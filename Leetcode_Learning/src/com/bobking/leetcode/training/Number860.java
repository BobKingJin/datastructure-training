package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-31 6:35
 */
public class Number860 {

    public boolean lemonadeChange(int[] bills) {

        int five = 0;
        int ten = 0;

        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {

                if (five == 0)
                    return false;
                five--;
                ten++;
            } else {
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
