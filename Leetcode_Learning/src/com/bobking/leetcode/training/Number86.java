package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-03-29 10:00
 */
public class Number86 {

    // 参考：https://leetcode-cn.com/problems/partition-list/solution/fen-ge-lian-biao-by-leetcode-solution-7ade/
    public ListNode partition(ListNode head, int x) {

        ListNode small = new ListNode(0);
        ListNode smallHead = small;
        ListNode large = new ListNode(0);
        ListNode largeHead = large;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }

        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;
    }
}
