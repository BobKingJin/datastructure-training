package com.bobking.leetcode.training;

/**
 * @Date: 2024/11/1 11:21
 * @Author: BobKing
 * @Description:
 */
public class LCR155 {

    Node pre;
    Node head;

    public Node treeToDoublyList(Node root) {
        if (root == null)
            return null;
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    private void dfs(Node cur) {
        if (cur == null)
            return;
        dfs(cur.left);
        if (pre != null) {
            pre.right = cur;
        } else {
            head = cur;
        }
        cur.left = pre;
        pre = cur;
        dfs(cur.right);
    }

}
