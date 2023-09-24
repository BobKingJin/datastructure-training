package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author BobKing
 * @create 2023-06-23 21:49
 */
public class Number382 {

    // 参考：https://leetcode.cn/problems/linked-list-random-node/solution/gong-shui-san-xie-xu-shui-chi-chou-yang-1lp9d/
    class Solution {

        List<Integer> list = new ArrayList<Integer>();
        Random random = new Random(20220116);

        public Solution(ListNode head) {
            while (head != null) {
                list.add(head.val);
                head = head.next;
            }
        }

        public int getRandom() {
            int idx = random.nextInt(list.size());
            return list.get(idx);
        }
    }
}
