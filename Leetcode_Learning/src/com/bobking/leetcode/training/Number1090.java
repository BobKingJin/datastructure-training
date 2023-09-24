package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @author BobKing
 * @create 2023-05-23 7:54
 */
public class Number1090 {

    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {

        int len = values.length;
        int[][] items = new int[len][2];
        for (int i = 0; i < len; ++i) {
            items[i][0] = values[i];
            items[i][1] = labels[i];
        }
        // 根据 value 降序排序，每次取当前最大的 value
        Arrays.sort(items, Comparator.comparingInt(i -> -i[0]));
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int res = 0;

        for (int[] item : items) {
            int labelCount = map.getOrDefault(item[1], 0);
            if (labelCount < useLimit) {
                res += item[0];
                if (--numWanted == 0)
                    break;
                map.put(item[1], labelCount + 1);
            }
        }
        return res;
    }
}
