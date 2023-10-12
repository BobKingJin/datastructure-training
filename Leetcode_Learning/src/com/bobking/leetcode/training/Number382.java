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
    private class Solution1 {

        List<Integer> list = new ArrayList<Integer>();
        Random random = new Random(20220116);

        public Solution1(ListNode head) {
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

    // 参考: https://leetcode.cn/problems/linked-list-random-node/
    private class Solution2 {
        ListNode head;
        Random random = new Random(20220116);

        public Solution2(ListNode head) {
            this.head = head;
        }
        public int getRandom() {
            int ans = 0;
            int idx = 0;
            ListNode t = head;

            while (t != null && ++idx >= 0) {
                // 当处理到节点 k 时，在 [0, k) 范围内进行随机，若随机到结果为 0(发生概率为 1/k)
                // 则将节点 k 的值存入答案，最后一次覆盖答案的节点即为本次抽样结果
                if (random.nextInt(idx) == 0)
                    ans = t.val;
                t = t.next;
            }
            return ans;
        }
    }
}
