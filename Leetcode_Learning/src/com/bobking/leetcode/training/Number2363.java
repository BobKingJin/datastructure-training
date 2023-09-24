package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author BobKing
 * @create 2023-02-28 10:11
 */
public class Number2363 {

    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {

        Arrays.sort(items1, (a, b) -> a[0] - b[0]);
        Arrays.sort(items2, (a, b) -> a[0] - b[0]);

        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int i = 0, j = 0; i < items1.length || j < items2.length; ) {
            if (i >= items1.length) {
                ans.add(Arrays.asList(items2[j][0], items2[j++][1]));
            } else if (j >= items2.length) {
                ans.add(Arrays.asList(items1[i][0], items1[i++][1]));
            } else if (items1[i][0] == items2[j][0]) {
                ans.add(Arrays.asList(items1[i][0], items1[i++][1] + items2[j++][1]));
            } else if (items1[i][0] < items2[j][0]) {
                ans.add(Arrays.asList(items1[i][0], items1[i++][1]));
            } else {
                ans.add(Arrays.asList(items2[j][0], items2[j++][1]));
            }
        }

        return ans;
    }
}
