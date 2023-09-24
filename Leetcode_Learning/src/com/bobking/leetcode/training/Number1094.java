package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-08-07 9:50
 */
public class Number1094 {

    // 参考：https://leetcode.cn/problems/car-pooling/solution/gou-zao-fu-zai-shu-zu-jian-cha-fu-zai-ji-ke-by-the/
    public boolean carPooling1(int[][] trips, int capacity) {

        int[] allTrip = new int[1001];

        for (int i = 0; i < trips.length; i++) {
            for (int j = trips[i][1]; j < trips[i][2]; j++) {
                allTrip[j] += trips[i][0];
                if (allTrip[j] > capacity)
                    return false;
            }
        }

        return true;
    }

    // 参考：https://leetcode.cn/problems/car-pooling/solution/gou-zao-fu-zai-shu-zu-jian-cha-fu-zai-ji-ke-by-the/
    public boolean carPooling2(int[][] trips, int capacity) {

        int[] loadByTime = new int[1001];

        for (int i = 0; i < trips.length; i++) {
            loadByTime[trips[i][1]] += trips[i][0];
            loadByTime[trips[i][2]] -= trips[i][0];
        }

        int load = 0;

        for (int i = 0; i < loadByTime.length; i++) {
            load += loadByTime[i];
            if (load > capacity)
                return false;
        }

        return true;
    }

}
