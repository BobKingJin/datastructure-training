package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;

public class Number735 {

    public int[] asteroidCollision(int[] asteroids) {

        Deque<Integer> d = new ArrayDeque<Integer>();

        for (int t : asteroids) {
            boolean ok = true;
            while (ok && !d.isEmpty() && d.peekLast() > 0 && t < 0) {
                int a = d.peekLast();
                int b = -t;
                if (a <= b)
                    d.pollLast();
                if (a >= b)
                    ok = false;
            }
            if (ok)
                d.addLast(t);
        }

        int sz = d.size();
        int[] ans = new int[sz];
        while (!d.isEmpty())
            ans[--sz] = d.pollLast();

        return ans;
    }
}
