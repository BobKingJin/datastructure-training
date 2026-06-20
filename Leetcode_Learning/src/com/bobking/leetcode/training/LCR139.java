package com.bobking.leetcode.training;

/**
 * @Date: 2024/11/15 10:27
 * @Author: BobKing
 * @Description:
 */
public class LCR139 {

    public int[] trainingPlan1(int[] actions) {
        int i = 0;
        int j = actions.length - 1;
        int tmp;
        while (i < j) {
            while (i < j && (actions[i] & 1) == 1) {
                i++;
            }
            while (i < j && (actions[j] & 1) == 0) {
                j--;
            }
            swap(actions, i, j);
        }
        return actions;
    }

    public int[] trainingPlan2(int[] actions) {
        if (actions == null || actions.length == 0) {
            return actions;
        }
        int swapIdx = actions.length;
        int curIdx = 0;
        while (curIdx < swapIdx) {
            if ((actions[curIdx] & 1) != 0) {
                curIdx++;
            } else {
                swap(actions, curIdx, --swapIdx);
            }
        }
        return actions;
    }

    private void swap(int[] actions, int l, int r) {
        int temp = actions[l];
        actions[l] = actions[r];
        actions[r] = temp;
    }
}
