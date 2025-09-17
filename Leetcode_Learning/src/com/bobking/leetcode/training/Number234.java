package com.bobking.leetcode.training;

import java.util.Stack;

public class Number234 {

    // 参考：程序猿代码指南P55
    public boolean isPalindrome1(ListNode head) {

        if (head == null || head.next == null) {
            return true;
        }

        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        while (head != null) {
            if (stack.pop().val != head.val) {
                return false;
            }
            head = head.next;
        }

        return true;
    }

    // 参考：程序猿代码指南P55
    public boolean isPalindrome2(ListNode head) {

        if (head == null || head.next == null) {
            return true;
        }

        ListNode cur = head;
        ListNode right = head.next;
        while (cur.next != null && cur.next.next != null) {
            cur = cur.next.next;
            right = right.next;
        }

        Stack<ListNode> stack = new Stack<ListNode>();
        while (right != null) {
            stack.push(right);
            right = right.next;
        }

        while (!stack.isEmpty()) {
            if (stack.pop().val != head.val) {
                return false;
            }
            head = head.next;
        }

        return true;
    }
}
