package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author BobKing
 * @create 2022-05-28 15:15
 */
public class Number173 {

    // 参考：https://leetcode.cn/problems/binary-search-tree-iterator/solution/xiang-jie-ru-he-dui-die-dai-ban-de-zhong-4rxj/
    private class BSTIterator {

        Deque<TreeNode> d = new ArrayDeque<TreeNode>();

        public BSTIterator(TreeNode root) {
            dfsLeft(root);
        }

        public int next() {
            // 步骤 2
            TreeNode root = d.pollLast();
            int res = root.val;
            // 步骤 3
            root = root.right;
            // 步骤 1
            dfsLeft(root);
            return res;
        }

        private void dfsLeft(TreeNode root) {
            while (root != null) {
                d.addLast(root);
                root = root.left;
            }
        }

        public boolean hasNext() {
            return !d.isEmpty();
        }
    }
}
