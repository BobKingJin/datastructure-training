package com.bobking.leetcode.training;

public class Number876 {

    // 参考：程序猿代码指南P45
    // 参考：https://leetcode-cn.com/problems/middle-of-the-linked-list/solution/kuai-man-zhi-zhen-zhu-yao-zai-yu-diao-shi-by-liwei/
    public ListNode middleNode(ListNode head) {

        if (head == null || head.next == null)
            return head;

        if (head.next.next == null)
            return head.next;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
