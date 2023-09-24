package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-03-14 15:29
 */
public class Number605 {

    // 参考：https://leetcode-cn.com/problems/can-place-flowers/solution/qi-si-miao-jie-by-heroding-h7m0/
    public boolean canPlaceFlowers(int[] flowerbed, int n) {

        if (flowerbed == null || flowerbed.length < 1 || n > flowerbed.length)
            return false;

        int index = 0;
        while (index < flowerbed.length) {
            if (flowerbed[index] == 0) {
                // 为什么当 index == flowerbed.length - 1 时也可以直接 n--？？难道不会存在 ... 1 0(角标为 flowerbed.length - 1) 这种情况吗？
                // 这个位置要注意 index 角标变化，index的变化规则可以确保 index - 1 一定为 0
                if (index == flowerbed.length - 1 || flowerbed[index + 1] == 0) {
                    // 可种，种一朵，跳到下一个有可能可以种的地方，步进 2
                    n--;
                    index += 2;
                } else {
                    // 不可种，当前位置下一个位置为 1，则下一个有可能可以种的地方需要步进 3
                    index += 3;
                }
            } else {
                // 不可种，当前位置为 1，则下一个有可能可以种的地方需要步进 2
                index += 2;
            }
        }

        return n <= 0;
    }
}
