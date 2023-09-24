package com.bobking.leetcode.training;

public class Number237 {

    // 参考：程序猿代码指南P86
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

}
