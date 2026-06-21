package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2026/6/20 18:30
 * @Author: BobKing
 * @Description:
 */
public class LCR180 {

    public int[][] fileCombination(int target) {
        int i = 1;
        int j = 1;
        int sum = 0;
        List<int[]> res = new ArrayList<int[]>();

        while (i <= target / 2) {
            if (sum < target) {
                sum += j;
                j++;
            } else if (sum > target) {
                sum -= i;
                i++;
            } else {
                int[] arr = new int[j - i];
                for (int k = i; k < j; k++) {
                    arr[k - i] = k;
                }
                res.add(arr);
                sum -= i;
                i++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }

}
