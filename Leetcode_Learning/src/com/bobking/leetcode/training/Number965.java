package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

public class Number965 {

    List<Integer> vals;

    // 参考：https://leetcode-cn.com/problems/univalued-binary-tree/solution/dan-zhi-er-cha-shu-by-leetcode/
    public boolean isUnivalTree1(TreeNode root) {

        // 将数中所有节点添加进 vals 中
        vals = new ArrayList<Integer>();
        dfs(root);
        for (int v : vals)
            if (v != vals.get(0))
                return false;
        return true;
    }

    private void dfs(TreeNode node) {
        if (node != null) {
            vals.add(node.val);
            dfs(node.left);
            dfs(node.right);
        }
    }

    // 参考：https://leetcode-cn.com/problems/univalued-binary-tree/solution/dan-zhi-er-cha-shu-by-leetcode/
    public boolean isUnivalTree2(TreeNode root) {

        // 一开始 root 必不为空
        boolean left_correct = (root.left == null ||
                (root.val == root.left.val && isUnivalTree2(root.left)));
        boolean right_correct = (root.right == null ||
                (root.val == root.right.val && isUnivalTree2(root.right)));
        return left_correct && right_correct;
    }


}
