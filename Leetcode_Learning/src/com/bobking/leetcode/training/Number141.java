package com.bobking.leetcode.training;

public class Number141 {

    // 参考：程序猿代码指南P69
    public boolean hasCycle(ListNode head) {

        if (head == null || head.next == null) {
            return false;
        }

        // 慢指针
        ListNode slowIndex = head;
        // 快指针
        ListNode fastIndex = head;

        while (fastIndex != null && fastIndex.next != null) {
            slowIndex = slowIndex.next;
            fastIndex = fastIndex.next.next;
            if (slowIndex == fastIndex) {
                return true;
            }
        }

        return false;
    }
}
