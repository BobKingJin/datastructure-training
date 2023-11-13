package com.bobking.leetcode.training;

import java.util.HashMap;

public class Number337 {

    // 参考：https://leetcode-cn.com/problems/house-robber-iii/solution/san-chong-fang-fa-jie-jue-shu-xing-dong-tai-gui-hu/
    public int rob1(TreeNode root) {

        if (root == null)
            return 0;

        int money = root.val;
        // 偷root节点
        if (root.left != null)
            money += (rob1(root.left.left) + rob1(root.left.right));
        if (root.right != null)
            money += (rob1(root.right.left) + rob1(root.right.right));

        // 不偷root节点
        return Math.max(money, rob1(root.left) + rob1(root.right));
    }

    // 参考：https://leetcode-cn.com/problems/house-robber-iii/solution/san-chong-fang-fa-jie-jue-shu-xing-dong-tai-gui-hu/
    public int rob2(TreeNode root) {

        if (root == null)
            return 0;

        if (root.left == null && root.right == null)
            return root.val;

        HashMap<TreeNode, Integer> memo = new HashMap<TreeNode, Integer>();
        return robInternal(root, memo);
    }

    private int robInternal(TreeNode root, HashMap<TreeNode, Integer> memo) {

        if (root == null)
            return 0;

        // 避免重复计算
        if (memo.containsKey(root))
            return memo.get(root);

        int money = root.val;

        if (root.left != null)
            money += (robInternal(root.left.left, memo) + robInternal(root.left.right, memo));
        if (root.right != null)
            money += (robInternal(root.right.left, memo) + robInternal(root.right.right, memo));

        int result = Math.max(money, robInternal(root.left, memo) + robInternal(root.right, memo));
        // 将已经计算的结果添加进map中
        memo.put(root, result);
        return result;
    }

    // 记录选中节点
    HashMap<TreeNode, Integer> f = new HashMap<>();
    // 记录不选中节点
    HashMap<TreeNode, Integer> g = new HashMap<>();

    // 参考：https://leetcode-cn.com/problems/house-robber-iii/solution/da-jia-jie-she-iii-by-leetcode-solution/
    public int rob3(TreeNode root) {

        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return root.val;

        dfs1(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }

    private void dfs1(TreeNode node) {

        if (node == null)
            return;

        dfs1(node.left);
        dfs1(node.right);
        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }

    // 参考：https://leetcode-cn.com/problems/house-robber-iii/solution/da-jia-jie-she-iii-by-leetcode-solution/
    public int rob4(TreeNode root) {

        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return root.val;

        int[] res = dfs2(root);
        return Math.max(res[0], res[1]);
    }

    private int[] dfs2(TreeNode node) {

        if (node == null)
            return new int[]{0, 0};

        int[] l = dfs2(node.left);
        int[] r = dfs2(node.right);

        int selected = node.val + l[1] + r[1];
        int notSelected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);

        return new int[]{selected, notSelected};
    }

}
