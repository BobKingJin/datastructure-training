package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-03-20 21:08
 */
public class Number142 {

    // 参考：程序猿代码指南P69
    public ListNode detectCycle(ListNode head) {

        if (head == null || head.next == null) {
            return null;
        }

        // 慢指针
        ListNode s = head;
        // 快指针
        ListNode f = head;

        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
            if (s == f) {
                s = head;
                while (s != f) {
                    s = s.next;
                    f = f.next;
                }
                return s;
            }
        }

        return null;
    }
}
