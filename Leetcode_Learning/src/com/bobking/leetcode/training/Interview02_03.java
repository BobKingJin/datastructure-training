package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-06-23 9:10
 */
public class Interview02_03 {

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
