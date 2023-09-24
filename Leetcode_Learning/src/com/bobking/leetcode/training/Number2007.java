package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author BobKing
 * @create 2022-10-21 10:44
 */
public class Number2007 {

    public int[] findOriginalArray(int[] changed) {

        if (changed.length % 2 != 0)
            return new int[]{};

        Arrays.sort(changed);

        int[] res = new int[changed.length / 2];
        int index = 0;

        Queue<Integer> queue = new LinkedBlockingQueue<Integer>();
        for (int i : changed) {
            if (!queue.isEmpty() && queue.element() * 2 == i) {
                res[index] = queue.poll();
                index++;
            } else {
                queue.add(i);
            }
        }

        if (!queue.isEmpty())
            return new int[]{};

        return res;
    }
}
