package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2022-07-30 22:27
 */
public class Number414 {

    public int thirdMax1(int[] nums) {

        Set<Integer> set = new HashSet<Integer>();
        for (int x : nums)
            set.add(x);

        List<Integer> list = new ArrayList<Integer>(set);
        Collections.sort(list);
        return list.size() < 3 ? list.get(list.size() - 1) : list.get(list.size() - 3);
    }

    private long INF = (long)-1e18;

    public int thirdMax2(int[] nums) {

        // 可以使用 a、b 和 c 三个变量来代指「最大值」、「严格次大值」和「严格第三大值」
        // 从前往后遍历 nums，假设当前元素为 x，对是否更新三者进行分情况讨论（判断优先级从上往下）：
        // x > a，说明最大值被更新，将原本的「最大值」和「次大值」往后顺延为「次大值」和「第三大值」，并用 x 更新 a
        // x < a 且 x > b，说明次大值被更新，将原本的「次大值」往后顺延为「第三大值」，并用 x 更新 b
        // x < b 且 x > c，说明第三大值被更新，使用 x 更新 c
        // 起始时，希望使用一个足够小的数来初始化 a、b 和 c，但由于 num[i] 的范围为 [-2^31, 2^31 - 1]
        // 因此需要使用 long 来进行代替

        long a = INF;
        long b = INF;
        long c = INF;
        for (int x : nums) {
            if (x > a) {
                c = b;
                b = a;
                a = x;
            } else if (x < a && x > b) {
                c = b;
                b = x;
            } else if (x < b && x > c) {
                c = x;
            }
        }
        return c != INF ? (int)c : (int)a;
    }
}
