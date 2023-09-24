package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-03 11:24
 */
public class Number2443 {

    public boolean sumOfNumberAndReverse(int num) {

        int[] arr = new int[6];

        for (int i = 0; i <= num; i++) {

            int reverse = 0;
            int k = i;
            int index = 0;
            int count = 1;

            while (k > 9) {
                int e = k % 10;
                if (e > 0 || index > 0) {
                    arr[index++] = e;
                    count *= 10;
                }
                k /= 10;
            }
            arr[index] = k;
            for (int j = 0; j <= index; j++) {
                reverse += count * arr[j];
                count /= 10;
            }

            if (reverse + i == num)
                return true;
        }
        return false;
    }
}
