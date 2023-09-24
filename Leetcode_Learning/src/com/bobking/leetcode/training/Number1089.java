package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-06-17 22:42
 */
public class Number1089 {

    public void duplicateZeros(int[] arr) {

        int n = arr.length;
        // 可以假设已经通过原数组 arr 处理出最终的目标数组 ans
        // 起始使用指针 i 指向原数组的开头位置，使用指针 j 指向目标数组的开头位置
        int i = 0;
        int j = 0;

        while (j < n) {

            if (arr[i] == 0)
                j++;

            i++;
            j++;
        }

        i--;
        j--;

        while (i >= 0) {

            if (j < n)
                arr[j] = arr[i];

            if (arr[i] == 0 && --j >= 0)
                arr[j] = 0;

            i--;
            j--;
        }
    }
}
