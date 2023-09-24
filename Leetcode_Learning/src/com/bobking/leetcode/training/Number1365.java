package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.Comparator;

public class Number1365 {

    public int[] smallerNumbersThanCurrent1(int[] nums) {

        if(nums == null || nums.length == 0)
            return nums;

        int n = nums.length;
        int[][] data = new int[n][2];
        for (int i = 0; i < n; i++) {
            data[i][0] = nums[i];
            data[i][1] = i;
        }

        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] data1, int[] data2) {
                return data1[0] - data2[0];
            }
        });

        int[] res = new int[n];
        int prev = -1;

        for (int i = 0; i < n; i++) {
            if (prev == -1 || data[i][0] != data[i - 1][0])
                prev = i;

            res[data[i][1]] = prev;
        }

        return res;
    }

    public int[] smallerNumbersThanCurrent2(int[] nums) {

        if(nums == null || nums.length == 0)
            return nums;

        int[] cnt = new int[101];
        int n = nums.length;
        for (int i = 0; i < n; i++)
            cnt[nums[i]]++;

        for (int i = 1; i <= 100; i++)
            cnt[i] += cnt[i - 1];

        int[] res = new int[n];
        for (int i = 0; i < n; i++)
            res[i] = nums[i] == 0 ? 0 : cnt[nums[i] - 1];

        return res;
    }


}
