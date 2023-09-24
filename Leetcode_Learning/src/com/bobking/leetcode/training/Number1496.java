package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

/**
 * @author BobKing
 * @create 2022-10-17 19:29
 */
public class Number1496 {

    public boolean isPathCrossing(String path) {

        Set<Integer> vis = new HashSet<Integer>();

        int x = 0;
        int y = 0;
        vis.add(getHash(x, y));

        int length = path.length();
        for (int i = 0; i < length; i++) {
            char dir = path.charAt(i);
            switch (dir) {
                case 'N':
                    --x;
                    break;
                case 'S':
                    ++x;
                    break;
                case 'W':
                    --y;
                    break;
                case 'E':
                    ++y;
                    break;
            }
            int hashValue = getHash(x, y);
            if (!vis.add(hashValue))
                return true;
        }

        return false;
    }

    private int getHash(int x, int y) {
        return x * 20001 + y;
    }
}
