package com.bobking.leetcode.training;

/**
 * @Date: 2026/6/28 22:00
 * @Author: BobKing
 * @Description:
 */
public class Jianzhi21 {

    // 奇偶要求保证相对顺序, 对比: LCR139
    public int[] reOrderArray(int[] array) {
        int i = 0;
        for (int j = 0; j < array.length; ++j) {
            // 遇到奇数时
            if (array[j] % 2 == 1) {
                int tmp = array[j];
                for (int k = j - 1; k >= i; --k) {
                    array[k + 1] = array[k];
                }
                array[i++] = tmp;
            }
        }
        return array;
    }

}
