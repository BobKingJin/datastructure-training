package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-11-08 20:38
 */
public class Number1291 {

    List<Integer> ans;
    int low;
    int high;

    public List<Integer> sequentialDigits(int low, int high) {

        ans = new ArrayList<Integer>();
        this.low = low;
        this.high = high;

        for (int i = 1; i < 9; i++)
            slove(i, i);

        Collections.sort(ans);
        return ans;
    }

    private void slove(int cur, int num) {

        if (num > high || cur > 9)
            return;

        if (num >= low && num <= high)
            ans.add(num);

        slove(cur + 1, num * 10 + cur + 1);
    }
}
