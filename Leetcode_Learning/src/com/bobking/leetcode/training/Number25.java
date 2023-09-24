package com.bobking.leetcode.training;

import java.util.Stack;

/**
 * @author BobKing
 * @create 2021-04-16 22:11
 */
public class Number25 {

    // 参考：程序猿代码指南P74
    public ListNode reverseKGroup1(ListNode head, int k) {

        if (head == null || k < 2)
            return head;

        ListNode newHead = head;
        // 反转部分的前一个节点
        ListNode pre = null;
        // 反转部分的后一个节点
        ListNode next = null;
        ListNode cur = head;
        Stack<ListNode> stack = new Stack<ListNode>();

        while (cur != null) {
            next = cur.next;
            stack.push(cur);
            if (stack.size() == k) {
                pre = reversKNodes(stack, pre, next);
                newHead = newHead == head ? cur : newHead;
            }
            cur = next;
        }

        return newHead;
    }

    private ListNode reversKNodes(Stack<ListNode> stack, ListNode pre, ListNode next) {

        ListNode curNode = stack.pop();
        // 注意第一组节点 pre 为空，所以这里需要做个判断
        if (pre != null)
            pre.next = curNode;

        ListNode nextNode = null;
        while (!stack.isEmpty()) {
            nextNode = stack.pop();
            curNode.next = nextNode;
            curNode = nextNode;
        }

        curNode.next = next;
        return curNode;
    }

    // 参考：程序猿代码指南P76
    public ListNode reverseKGroup2(ListNode head, int k) {

        if (head == null || k < 2)
            return head;

        // 反转部分的前一个节点
        ListNode pre = null;
        // 反转部分的后一个节点
        ListNode next = null;
        ListNode cur = head;
        // 每一次反转的开始节点
        // 因为方法一中可以通过栈来记录要反转的部分的所有节点，所以不需要记录要反转部分头节点
        // 同时可以利用 cur 来记录要反转部分的最后一个节点
        ListNode start = null;
        // 注意因为一开始 cur = head，所以初始化为 1 而不是 0
        int count = 1;

        while (cur != null) {
            next = cur.next;
            if (count == k) {
                start = pre == null ? head : pre.next;
                head = pre == null ? cur : head;
                reversKNodes(pre, start, cur, next);
                pre = start;
                count = 0;
            }
            count++;
            cur = next;
        }

        return head;
    }

    private void reversKNodes(ListNode left, ListNode start, ListNode end, ListNode right) {

        ListNode cur = start;
        ListNode pre = null;
        ListNode next = null;
        while (cur != right) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        if (left != null)
            left.next = end;

        start.next = right;
    }
}
