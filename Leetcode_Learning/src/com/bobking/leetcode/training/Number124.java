package com.bobking.leetcode.training;

public class Number124 {

    int res = Integer.MIN_VALUE;

    // 参考：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/solution/shou-hui-tu-jie-hen-you-ya-de-yi-dao-dfsti-by-hyj8/
    public int maxPathSum(TreeNode root) {

        if (root == null)
            return 0;

        // dfs 能够保证当进入某条路径之后不回头
        dfs(root);
        return res;
    }

    // 函数功能：返回当前子树能向父节点提供的最大路径和
    // 顺序为 从底 -> 上
    // 后序遍历
    private int dfs(TreeNode root) {

        if (root == null)
            return 0;

        // 如果不是叶子节点，计算当前节点的左右孩子对自身的贡献 left 和 right
        int left = dfs(root.left);
        int right = dfs(root.right);
        // 更新最大值
        // 最大路径和可能产生于局部子树中
        // 所以每递归一个子树都求一下当前子树内部的最大路径和
        res = Math.max(res, root.val + left + right);
        // 计算当前节点能为父亲提供的最大贡献，必须是把 val 加上
        // 因为当进入某条路径之后不能回头，所以此处需要比较一下左右两个子树的大小，选择路径
        int max = Math.max(left, right) + root.val;
        return max < 0 ? 0 : max;
    }
}
