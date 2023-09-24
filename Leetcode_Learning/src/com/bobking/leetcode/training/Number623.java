package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author BobKing
 * @create 2022-08-05 11:38
 */
public class Number623 {

    // 参考：https://leetcode.cn/problems/add-one-row-to-tree/solution/by-ac_oier-sc34/
    public TreeNode addOneRow(TreeNode root, int val, int depth) {

        if (depth == 1) {
            TreeNode ans = new TreeNode(val);
            ans.left = root;
            return ans;
        }

        // 按层遍历
        Deque<TreeNode> d = new ArrayDeque<TreeNode>();
        d.addLast(root);
        int cur = 1;
        while (!d.isEmpty()) {
            int sz = d.size();
            while (sz-- > 0) {
                TreeNode t = d.pollFirst();
                if (cur == depth - 1) {
                    TreeNode a = new TreeNode(val);
                    TreeNode b = new TreeNode(val);
                    a.left = t.left;
                    b.right = t.right;
                    t.left = a;
                    t.right = b;
                } else {
                    if (t.left != null)
                        d.addLast(t.left);
                    if (t.right != null)
                        d.addLast(t.right);
                }
            }
            cur++;
        }
        return root;
    }
}
