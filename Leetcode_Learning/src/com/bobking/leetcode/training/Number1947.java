package com.bobking.leetcode.training;

public class Number1947 {

    int max = 0;

    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        match(students, mentors, 0, new boolean[students.length], 0);
        return max;
    }

    private void match(int[][] students, int[][] mentors, int index, boolean[] match, int value) {

        if (index == students.length) {
            max = Math.max(max, value);
            return;
        }

        for (int i = 0; i < mentors.length; i++) {

            if (match[i])
                continue;

            match[i] = true;
            match(students, mentors, index + 1, match, value + getValue(students[index], mentors[i]));
            // 回溯
            match[i] = false;
        }
    }

    private int getValue(int[] nums1, int[] nums2) {

        int count = 0;

        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] == nums2[i])
                count++;
        }

        return count;
    }
}
