package com.bobking.leetcode.training;

import java.util.TreeSet;

/**
 * @author BobKing
 * @create 2022-12-30 10:51
 */
public class Number855 {

    // 参考：https://leetcode.cn/problems/exam-room/solution/kao-chang-jiu-zuo-by-leetcode/
    private class ExamRoom {

        int N;
        // 存储目前有学生的座位编号
        TreeSet<Integer> students;

        public ExamRoom(int N) {
            this.N = N;
            students = new TreeSet<Integer>();
        }

        // 当要调用 seat() 函数时，遍历这个有序集合，对于相邻的两个座位 i 和 j
        // 如果选择在这两个座位之间入座，那么最近的距离 d 为 (j - i) / 2，选择的座位为 i + d
        // 除此之外，还需要考虑坐在最左侧 0 和最右侧 N - 1 的情况
        public int seat() {
            int student = 0;
            if (students.size() > 0) {
                int dist = students.first();
                Integer prev = null;
                for (Integer s : students) {
                    if (prev != null) {
                        int d = (s - prev) / 2;
                        if (d > dist) {
                            dist = d;
                            student = prev + d;
                        }
                    }
                    prev = s;
                }

                if (N - 1 - students.last() > dist)
                    student = N - 1;
            }

            students.add(student);
            return student;
        }

        // 当要调用 leave(p) 函数时，只需要把有序集合中的 p 移除即可
        public void leave(int p) {
            students.remove(p);
        }
    }
}
