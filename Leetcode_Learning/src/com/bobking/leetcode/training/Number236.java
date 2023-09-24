package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Number236 {

    // 参考：程序猿代码指南P155
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null || root == p || root == q)
            return root;

        // 后序遍历
        TreeNode left = lowestCommonAncestor1(root.left, p, q);
        TreeNode right = lowestCommonAncestor1(root.right, p, q);

        if (left != null && right != null)
            return root;

        return left == null ? right : left;
    }


    Map<TreeNode, TreeNode> map = new HashMap<TreeNode, TreeNode>();

    private void setMap(TreeNode root) {

        if (root == null)
            return;

        if (root.left != null)
            map.put(root.left, root);
        if (root.right != null)
            map.put(root.right, root);

        setMap(root.left);
        setMap(root.right);
    }

    // 参考：程序猿代码指南P155
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {

        setMap(root);
        HashSet<TreeNode> set = new HashSet<TreeNode>();
        while (map.containsKey(p)) {
            set.add(p);
            p = map.get(p);
        }

        while (!set.contains(q))
            q = map.get(q);

        return q;
    }
}
