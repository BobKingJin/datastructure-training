package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-07-24 8:22
 */
public class Number1184 {

    public int distanceBetweenBusStops(int[] distance, int start, int destination) {

        if (start > destination) {
            int temp = start;
            start = destination;
            destination = temp;
        }

        int sum = 0;
        int sumA = 0;

        for (int i = 0; i < distance.length; i++) {
            sum += distance[i];
            if (i >= start && i <= destination - 1)
                sumA += distance[i];
        }

        return Math.min(sumA, sum - sumA);
    }
}
