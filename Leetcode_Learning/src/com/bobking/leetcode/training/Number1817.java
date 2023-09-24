package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author BobKing
 * @create 2023-01-20 11:11
 */
public class Number1817 {

    public int[] findingUsersActiveMinutes(int[][] logs, int k) {

        Map<Integer, Set<Integer>> activeMinutes = new HashMap<Integer, Set<Integer>>();

        for (int[] log : logs) {
            int id = log[0];
            int time = log[1];
            activeMinutes.putIfAbsent(id, new HashSet<Integer>());
            activeMinutes.get(id).add(time);
        }

        int[] answer = new int[k];
        for (Set<Integer> minutes : activeMinutes.values()) {
            int activeCount = minutes.size();
            answer[activeCount - 1]++;
        }

        return answer;
    }
}
