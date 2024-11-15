package com.bobking.leetcode.training;

/**
 * @Date: 2024/11/15 10:27
 * @Author: BobKing
 * @Description:
 */
public class LCR139 {

    public int[] trainingPlan(int[] actions) {
        int i = 0;
        int j = actions.length - 1;
        int tmp;
        while (i < j) {
            while (i < j && (actions[i] & 1) == 1)
                i++;
            while (i < j && (actions[j] & 1) == 0)
                j--;
            tmp = actions[i];
            actions[i] = actions[j];
            actions[j] = tmp;
        }
        return actions;
    }
}
