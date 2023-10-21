package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-10-20 7:29
 */
public class Number2525 {

    public String categorizeBox(int length, int width, int height, int mass) {

        boolean x = (length >= 10000) || (width >= 10000) || (height >= 10000) || (long) length * width * height >= 1000000000;

        boolean y = mass >= 100;

        if (x && y)
            return "Both";

        if (x)
            return "Bulky";

        if (y)
            return "Heavy";

        return "Neither";
    }
}
