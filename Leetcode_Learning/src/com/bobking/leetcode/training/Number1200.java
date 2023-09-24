package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-06-18 20:08
 */
public class Number1200 {

    public List<List<Integer>> minimumAbsDifference(int[] arr) {

        Arrays.sort(arr);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        int temp = Integer.MAX_VALUE;
        int j = 0;

        for (int i = 0; i < arr.length; i++) {
            if (i + 1 < arr.length) {
                j = temp;
                if (Math.min(temp, arr[i + 1] - arr[i]) == arr[i + 1] - arr[i]) {
                    temp = arr[i + 1] - arr[i];
                    if (j != temp)
                        res.clear();

                    list.add(arr[i]);
                    list.add(arr[i + 1]);
                    res.add(new ArrayList<Integer>(list));
                    list.clear();
                } else {
                    continue;
                }
            }
        }

        return res;
    }
}
