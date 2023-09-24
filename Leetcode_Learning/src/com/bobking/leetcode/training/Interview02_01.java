package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

public class Interview02_01 {

    // 参考：程序猿代码指南P78
    public ListNode removeDuplicateNodes1(ListNode head) {

        if (head == null || head.next == null)
            return head;

        Set<Integer> set = new HashSet<Integer>();
        ListNode pre = head;
        ListNode cur = head.next;
        set.add(head.val);

        while (cur != null) {

            if (set.contains(cur.val)) {
                pre.next = cur.next;
            } else {
                set.add(cur.val);
                pre = cur;
            }
            cur = cur.next;
        }

        return head;
    }

    // 参考：程序猿代码指南P78
    public ListNode removeDuplicateNodes2(ListNode head) {

        if (head == null || head.next == null)
            return head;

        // 最近一个没有被删除的节点
        ListNode pre = null;
        ListNode next = null;
        ListNode cur = head;

        // 即一次性把后面的所有相同节点都删除
        // 类似于选择排序
        while (cur != null) {

            pre = cur;
            next = cur.next;
            while (next != null) {
                if (cur.val == next.val) {
                    pre.next = next.next;
                } else {
                    pre = next;
                }
                next = next.next;
            }
            cur = cur.next;
        }

        return head;
    }
}
