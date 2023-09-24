package com.bobking.leetcode.training;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

/**
 * @author BobKing
 * @create 2023-03-16 23:52
 */
public class Number874 {

    // 参考：https://leetcode.cn/problems/walking-robot-simulation/solution/tu-jie-mo-ni-xing-zou-ji-qi-ren-by-dekeshile/
    public int robotSim(int[] commands, int[][] obstacles) {

        int[] direx = {0, 1, 0, -1};
        int[] direy = {1, 0, -1, 0};
        int curx = 0;
        int cury = 0;
        int curdire = 0;
        int comLen = commands.length;
        int ans = 0;

        Set<Pair<Integer, Integer>> obstacleSet = new HashSet<Pair<Integer, Integer>>();
        for (int i = 0; i < obstacles.length; i++)
            obstacleSet.add(new Pair<Integer, Integer>(obstacles[i][0], obstacles[i][1]));

        for (int i = 0; i < comLen; i++) {
            if (commands[i] == -1) {
                curdire = (curdire + 1) % 4;
            } else if (commands[i] == -2) {
                curdire = (curdire + 3) % 4;
            } else {
                for (int j = 0; j < commands[i]; j++) {
                    int nx = curx + direx[curdire];
                    int ny = cury + direy[curdire];
                    if (!obstacleSet.contains(new Pair<Integer, Integer>(nx, ny))) {
                        curx = nx;
                        cury = ny;
                        ans = Math.max(ans, curx * curx + cury * cury);
                    } else {
                        break;
                    }
                }
            }
        }
        return ans;
    }
}
