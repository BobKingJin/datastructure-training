package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-21 10:25
 */
public class Number1367 {

    public boolean isSubPath(ListNode head, TreeNode root) {

        if (head == null)
            return true;

        if (root == null)
            return false;

        return isSub(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean isSub(ListNode head, TreeNode node) {

        if (head == null)
            return true;

        if (node == null)
            return false;

        if (head.val != node.val)
            return false;

        return isSub(head.next, node.left) || isSub(head.next, node.right);
    }
}
