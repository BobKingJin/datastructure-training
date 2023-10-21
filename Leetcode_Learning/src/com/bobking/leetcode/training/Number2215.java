package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author BobKing
 * @create 2023-10-14 10:17
 */
public class Number2215 {

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {

        HashSet<Integer> set1 = (HashSet<Integer>) Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        HashSet<Integer> set2 = (HashSet<Integer>) Arrays.stream(nums2).boxed().collect(Collectors.toSet());

        for (int n : nums2)
            set1.remove(n);

        for (int n : nums1)
            set2.remove(n);

        return List.of(List.copyOf(set1), List.copyOf(set2));
    }
}
