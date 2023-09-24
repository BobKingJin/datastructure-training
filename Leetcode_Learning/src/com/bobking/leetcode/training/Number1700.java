package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-10-19 14:30
 */
public class Number1700 {

    // 参考：https://leetcode.cn/problems/number-of-students-unable-to-eat-lunch/solution/chao-guo-100-java-zhi-li-yu-you-ya-de-ji-6nq6/
    public int countStudents(int[] students, int[] sandwiches) {

        int[] counts = new int[2];
        for (int num : students)
            counts[num] += 1;

        int n = sandwiches.length;
        for (int i = 0; i < n; i++) {
            if (counts[sandwiches[i]] > 0) {
                counts[sandwiches[i]] -= 1;
            } else {
                return n - i;
            }
        }
        return 0;
    }
}
