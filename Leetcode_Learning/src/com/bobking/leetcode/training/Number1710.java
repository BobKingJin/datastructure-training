package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author BobKing
 * @create 2022-11-15 20:02
 */
public class Number1710 {

    // 参考：https://leetcode.cn/problems/maximum-units-on-a-truck/solution/zhua-wa-mou-si-by-muse-77-mttj/
    public int maximumUnits(int[][] boxTypes, int truckSize) {

        int result = 0;
        // 根据 numberOfUnitsPerBox 对数组 boxTypes 进行排序
        Arrays.sort(boxTypes, Comparator.comparingInt(o -> o[1]));

        for (int i = boxTypes.length - 1; i >= 0; i--) {
            if (truckSize > boxTypes[i][0]) {
                result += boxTypes[i][0] * boxTypes[i][1];
                truckSize -= boxTypes[i][0];
            } else {
                result += truckSize * boxTypes[i][1];
                return result;
            }
        }

        return result;
    }
}
