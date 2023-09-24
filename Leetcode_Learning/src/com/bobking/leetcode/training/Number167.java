package com.bobking.leetcode.training;

public class Number167 {

    // 参考：程序猿代码指南P380
    public int[] twoSum(int[] numbers, int target) {

        if (numbers == null || numbers.length < 2)
            return null;

        int[] res = new int[2];

        int l = 0;
        int r = numbers.length - 1;
        while (l < r) {

            if (numbers[l] + numbers[r] == target) {
                res[0] = l + 1;
                res[1] = r + 1;
                break;
            } else if (numbers[l] + numbers[r] < target) {
                l++;
            } else {
                r--;
            }
        }

        return res;
    }
}
