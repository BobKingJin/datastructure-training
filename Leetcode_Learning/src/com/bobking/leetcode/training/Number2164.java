package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-10-28 10:57
 */
public class Number2164 {

    public int[] sortEvenOdd1(int[] nums) {
        List<Integer> even = new ArrayList<Integer>();
        List<Integer> odd = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if ((i & 1) == 1) {
                odd.add(nums[i]);
            } else {
                even.add(nums[i]);
            }
        }
        Collections.sort(even);
        Collections.sort(odd, (a, b) -> b - a);
        for (int i = 0; i < even.size(); i++)
            nums[2 * i] = even.get(i);

        for (int i = 0; i < odd.size(); i++)
            nums[2 * i + 1] = odd.get(i);

        return nums;
    }

    public int[] sortEvenOdd2(int[] nums) {
        int s1 = 0;
        int s2 = 0;
        if (nums.length % 2 == 0) {
            s1 = s2 = nums.length / 2;
        } else {
            s1 = nums.length / 2;
            s2 = nums.length / 2 + 1;
        }
        // 奇
        int[] a1 = new int[s1];
        // 偶
        int[] a2 = new int[s2];
        int[] res = new int[nums.length];
        int idx = 0;

        for (int i = 0; i < nums.length; ) {

            a2[idx] = nums[i++];

            if (i == nums.length)
                break;

            a1[idx] = nums[i++];
            idx++;
        }

        Arrays.sort(a1);
        Arrays.sort(a2);
        int idx1 = a1.length - 1;
        int idx2 = 0;

        for (int i = 0; i < nums.length; ) {

            res[i++] = a2[idx2++];

            if (i == nums.length)
                break;

            res[i++] = a1[idx1--];
        }
        return res;
    }
}
