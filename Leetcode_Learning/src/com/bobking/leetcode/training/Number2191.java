package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/**
 * @author BobKing
 * @create 2022-06-25 11:53
 */
public class Number2191 {

    // 参考：https://leetcode.cn/problems/sort-the-jumbled-numbers/solution/shu-zu-zhan-pai-xu-you-tu-you-zhu-shi-to-z2vs/
    public int[] sortJumbled(int[] mapping, int[] nums) {

        // 二维数组
        // copy[i][0]：保存数据的映射，copy[i][1]：保存原数据

        int[][] copy = new int[nums.length][2];

        for (int i = 0; i < copy.length; i++) {
            copy[i][0] = nums[i];
            copy[i][1] = nums[i];
        }

        // 对数据进行映射
        for (int i = 0; i < copy.length; i++) {

            Stack<Integer> stack = new Stack<Integer>();
            if (copy[i][0] == 0)
                stack.push(0);

            int tmp = 0;
            while (copy[i][0] > 0) {
                tmp = copy[i][0] % 10;
                stack.push(tmp);
                copy[i][0] /= 10;
            }

            tmp = 0;
            while (!stack.isEmpty()) {
                tmp *= 10;
                tmp += mapping[stack.pop()];
            }

            copy[i][0] = tmp;
        }

        // 排序 小 -> 大
        Arrays.sort(copy, new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for (int i = 0; i < copy.length; i++)
            nums[i] = copy[i][1];

        return nums;
    }
}
