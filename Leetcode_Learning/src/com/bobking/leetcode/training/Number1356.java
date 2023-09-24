package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-01-13 11:43
 */
public class Number1356 {

    // 参考：https://leetcode.cn/problems/sort-integers-by-the-number-of-1-bits/solution/javaliang-ci-xun-huan-da-bai-100-by-yourtion/
    public int[] sortByBits(int[] arr) {

        int[] map = new int[arr.length];

        for (int i = 0; i < arr.length; i++)
            //                                  题目中不会大于 10^4，因此这个位置只需娶一个大于 10000的数即可
            map[i] = Integer.bitCount(arr[i]) * 10000000 + arr[i];

        Arrays.sort(map);

        for (int i = 0; i < map.length; i++)
            map[i] = map[i] % 10000000;

        return map;
    }
}
