package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2023-10-11 7:59
 */
public class Number2512 {

    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {

        List<Integer> list = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((i, j) -> {
            if (i[1] != j[1]) {
                return j[1] - i[1];
            } else {
                return i[0] - j[0];
            }
        });

        int len = report.length;
        int[][] r = new int[len][2];
        Set<String> positive_feedbackSet = new HashSet<String>(Arrays.asList(positive_feedback));
        Set<String> negative_feedbackSet = new HashSet<String>(Arrays.asList(negative_feedback));

        for (int i = 0; i < report.length; i++) {
            String[] str = report[i].split(" ");
            int score = 0;
            for (String s : str) {
                if (positive_feedbackSet.contains(s)) {
                    score += 3;
                } else if (negative_feedbackSet.contains(s)) {
                    score -= 1;
                }
            }
            pq.offer(new int[]{student_id[i], score});
        }

        while (!pq.isEmpty() && k-- > 0)
            list.add(pq.poll()[0]);

        return list;
    }
}
