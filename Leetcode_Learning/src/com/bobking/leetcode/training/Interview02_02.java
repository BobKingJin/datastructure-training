package com.bobking.leetcode.training;

public class Interview02_02 {

    // 对比Number19
    public int kthToLast(ListNode head, int k) {

        if (head.next == null || k < 1)
            return head.val;

        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        // 注意：即顺数第一个节点
        if (length == k)
            return head.val;

        node = head;
        // 倒数第 k 个节点即是顺数第 length - k + 1 个节点
        // 从头节点走 step 步
        int step = length - k;
        while (step > 0) {
            node = node.next;
            step--;
        }

        return node.val;
    }
}
