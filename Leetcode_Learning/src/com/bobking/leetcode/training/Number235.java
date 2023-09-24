package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-05-14 15:43
 */
public class Number235 {

    // 参考：https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/solution/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-3c/
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {

        while ((root.val - p.val) * (root.val - q.val) > 0)
            root = p.val < root.val ? root.left : root.right;
        // 如果相乘的结果是负数，说明 p 和 q 位于根节点的两侧
        // 如果等于 0，说明至少有一个就是根节点
        return root;
    }

    // 参考：https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/solution/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-3c/
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {

        // 如果小于等于 0，说明 p 和 q 位于 root 的两侧，直接返回即可
        if ((root.val - p.val) * (root.val - q.val) <= 0)
            return root;
        // 否则，p 和 q 位于 root 的同一侧，就继续往下找
        return lowestCommonAncestor2(p.val < root.val ? root.left : root.right, p, q);
    }

    // 参考：https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/solution/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-3c/
    public TreeNode lowestCommonAncestor3(TreeNode cur, TreeNode p, TreeNode q) {

        if (cur == null || cur == p || cur == q)
            return cur;

        TreeNode left = lowestCommonAncestor3(cur.left, p, q);
        TreeNode right = lowestCommonAncestor3(cur.right, p, q);
        // 如果 left 为空，说明这两个节点在 cur 节点的右子树上，只需要返回右子树查找的结果即可
        if (left == null)
            return right;
        if (right == null)
            return left;
        // 如果 left 和 right 都不为空，说明这两个节点一个在 cur 的左子树上一个在 cur 的右子树上，只需要返回 cur 节点即可
        return cur;
    }

}
