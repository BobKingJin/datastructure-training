package com.bobking.leetcode.training;

public class Number335 {

    // 参考: https://leetcode.cn/problems/self-crossing/solutions/1072162/gong-shui-san-xie-fen-qing-kuang-tao-lun-zdrb/
    public boolean isSelfCrossing(int[] distance) {

        int n = distance.length;
        if (n < 4)
            return false;

        for (int i = 3; i < n; i++) {
            if (distance[i] >= distance[i - 2] && distance[i - 1] <= distance[i - 3])
                return true;
            if (i >= 4 && distance[i - 1] == distance[i - 3] && distance[i] + distance[i - 4] >= distance[i - 2])
                return true;
            if (i >= 5 && distance[i - 1] <= distance[i - 3]
                    && distance[i - 2] > distance[i - 4]
                    && distance[i] + distance[i - 4] >= distance[i - 2]
                    && distance[i - 1] + distance[i - 5] >= distance[i - 3])
                return true;
        }
        return false;
    }
}
