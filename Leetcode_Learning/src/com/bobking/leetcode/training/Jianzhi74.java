package com.bobking.leetcode.training;

import java.util.ArrayList;

/**
 * @Date: 2026/6/20 18:26
 * @Author: BobKing
 * @Description:
 */
public class Jianzhi74 {

    // 参考: LCR180
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        for (int l = 1, r = 2; l < r; ) {
            int count = (l + r) * (r - l + 1) / 2;
            if (count == sum) {
                ArrayList<Integer> temp = new ArrayList<Integer>();
                for (int i = l; i <= r; i++) {
                    temp.add(i);
                }
                res.add(temp);
                l++;
            } else if (count < sum) {
                r++;
            } else {
                l++;
            }
        }
        return res;
    }

}
