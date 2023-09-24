package com.bobking.leetcode.training;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author BobKing
 * @create 2022-07-16 9:08
 */
public class Jianzhi_2_041 {

    private class MovingAverage {

        private int length;
        private Queue<Integer> queue;
        private double sum;


        public MovingAverage(int size) {
            length = size;
            queue = new LinkedList<Integer>();
            sum = 0;
        }

        public double next(int val) {

            if (queue.size() == length)
                sum -= queue.remove();

            queue.add(val);
            sum += val;
            return sum / queue.size();
        }
    }
}
