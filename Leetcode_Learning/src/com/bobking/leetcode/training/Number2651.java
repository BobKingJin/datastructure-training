package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-09-19 7:52
 */
public class Number2651 {

    public int findDelayedArrivalTime(int arrivalTime, int delayedTime) {
        return (arrivalTime + delayedTime) % 24;
    }
}
