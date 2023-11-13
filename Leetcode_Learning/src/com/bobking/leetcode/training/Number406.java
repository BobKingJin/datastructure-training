package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Number406 {

    // 参考：https://leetcode-cn.com/problems/queue-reconstruction-by-height/solution/406-gen-ju-shen-gao-zhong-jian-dui-lie-java-xian-p/
    public int[][] reconstructQueue(int[][] people) {

        if (people == null || people.length == 0 || people[0].length == 0)
            return new int[0][0];

        // 1.排序规则: 按照先 H 高度降序, K 个数升序排序
        // 2.遍历排序后的数组, 根据 K 插入到 K 的位置上
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
            }
        });

        List<int[]> queue = new ArrayList<int[]>();
        for (int[] p : people)
            // 核心思想：高个子先站好位，矮个子插入到 K 位置上，前面肯定有 K 个高个子，矮个子再插到前面也满足 K 的要求
            queue.add(p[1], p);

        return queue.toArray(new int[queue.size()][]);
    }
}
