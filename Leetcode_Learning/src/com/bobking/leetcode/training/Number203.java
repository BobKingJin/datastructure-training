package com.bobking.leetcode.training;

import java.util.Stack;

public class Number203 {

    // 参考：程序猿代码指南P80
    public ListNode removeElements1(ListNode head, int val) {

        if (head == null)
            return head;

        Stack<ListNode> stack = new Stack<ListNode>();
        while (head != null) {
            if (head.val != val)
                stack.push(head);
            head = head.next;
        }

        while (!stack.isEmpty()) {
            stack.peek().next = head;
            head = stack.pop();
        }

        return head;
    }

    // 参考：程序猿代码指南P80
    public ListNode removeElements2(ListNode head, int val) {

        if (head == null)
            return head;

        while (head != null) {

            if (head.val != val)
                break;
            head = head.next;
        }

        ListNode pre = head;
        ListNode cur = head;

        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }

        return head;
    }

    // 参考：https://leetcode-cn.com/problems/remove-linked-list-elements/solution/203yi-chu-lian-biao-yuan-su-by-lewis-dxstabdzew/
    // 与方法二同理，但是添加一个虚拟头节点
    public ListNode removeElements3(ListNode head, int val) {

        if (head == null)
            return head;

        // 创建一个虚拟头节点
        ListNode dummyNode = new ListNode(val - 1);
        dummyNode.next = head;
        ListNode prev = dummyNode;

        // 确保当前节点后还有节点
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }

        return dummyNode.next;
    }

    // 参考：https://leetcode-cn.com/problems/remove-linked-list-elements/solution/203yi-chu-lian-biao-yuan-su-by-lewis-dxstabdzew/
    public ListNode removeElements4(ListNode head, int val) {

        // 先递归到叶子节点，然后一步步回溯
        if (head == null)
            return null;

        head.next = removeElements4(head.next, val);
        if (head.val == val) {
            return head.next;
        } else {
            return head;
        }
    }
}
