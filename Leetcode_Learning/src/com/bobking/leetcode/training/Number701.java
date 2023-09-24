package com.bobking.leetcode.training;

public class Number701 {

    // 参考：https://leetcode-cn.com/problems/same-tree/solution/xie-shu-suan-fa-de-tao-lu-kuang-jia-by-wei-lai-bu-/
    public TreeNode insertIntoBST1(TreeNode root, int val) {

        // 找到空位置插入新节点
        if (root == null)
            return new TreeNode(val);
        // if (root.val == val)
        //     BST 中一般不会插入已存在元素
        if (root.val < val)
            root.right = insertIntoBST1(root.right, val);
        if (root.val > val)
            root.left = insertIntoBST1(root.left, val);
        return root;
    }

    // 参考：https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/solution/2-de-cha-ru-by-sweetiee/
    public TreeNode insertIntoBST2(TreeNode root, int val) {

        TreeNode node = new TreeNode(val);
        if (root == null)
            return node;

        TreeNode cur = root;
        while (true) {
            if (cur.val > val) {
                if (cur.left == null) {
                    cur.left = node;
                    break;
                }
                cur = cur.left;
            } else {
                if (cur.right == null) {
                    cur.right = node;
                    break;
                }
                cur = cur.right;
            }
        }

        return root;
    }

}
