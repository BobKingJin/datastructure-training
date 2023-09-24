package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-05-28 0:20
 */
public class Number109 {

    public TreeNode sortedListToBST(ListNode head) {

        if (head == null)
            return null;

        if (head.next == null)
            return new TreeNode(head.val);

        ListNode p = head;
        ListNode q = head;
        ListNode pre = null;
        while (q != null && q.next != null) {
            pre = p;
            p = p.next;
            q = q.next.next;
        }
        // 此时需要把 pre.next 置空
        pre.next = null;

        // 以升序链表的中间元素作为根节点 root，递归的构建 root 的左子树与右子树
        TreeNode root = new TreeNode(p.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(p.next);
        return root;
    }
}
