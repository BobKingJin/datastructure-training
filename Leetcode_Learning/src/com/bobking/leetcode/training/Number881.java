package com.bobking.leetcode.training;

import java.util.Arrays;

public class Number881 {

    public int numRescueBoats(int[] people, int limit) {

        // 由于一个船要么载两人要么载一人，在人数给定的情况下，为了让使用的总船数最小，要当尽可能让更多船载两人
        // 即尽可能多的构造出数量之和不超过 limit 的二元组
        // 先对 people 进行排序，然后使用两个指针 l 和 r 分别从首尾开始进行匹配
        // 如果 people[l] + people[r] <= limit，说明两者可以同船，此时船的数量加一，两个指针分别往中间靠拢
        // 如果 people[l] + people[r] > limit，说明不能成组，由于题目确保人的重量不会超过 limit
        // 此时让 people[r] 独立成船，船的数量加一，r 指针左移

        Arrays.sort(people);

        int n = people.length;
        int l = 0;
        int r = n - 1;
        int ans = 0;

        while (l <= r) {
            if (people[l] + people[r] <= limit)
                l++;
            r--;
            ans++;
        }

        return ans;
    }
}
