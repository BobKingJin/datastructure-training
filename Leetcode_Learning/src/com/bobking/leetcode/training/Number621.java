package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-08-20 22:26
 */
public class Number621 {

    // 参考：https://leetcode.cn/problems/task-scheduler/solution/tong-zi-by-popopop/
    public int leastInterval(char[] tasks, int n) {

        int[] temp = new int[26];
        int countMaxTask = 0;
        int maxTask = 0;
        for (char c : tasks) {
            temp[c - 'A']++;
            maxTask = Math.max(temp[c - 'A'], maxTask);
        }

        for (int i = 0; i < 26; i++) {
            if (temp[i] == maxTask)
                countMaxTask++;
        }

        return Math.max(tasks.length, (maxTask - 1) * (n + 1) + countMaxTask);
    }
}
