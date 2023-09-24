package com.bobking.leetcode.training;

import java.util.*;

public class Number107 {

    // 参考：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/solution/san-chong-shi-xian-tu-jie-107er-cha-shu-de-ceng-ci/
    public List<List<Integer>> levelOrderBottom1(TreeNode root) {

        if (root == null)
            return new ArrayList<List<Integer>>();

        // 用来存放最终结果
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        // 创建一个队列，将根节点放入其中
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 每次遍历的数量为队列的长度
            int size = queue.size();
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            // 将这一层的元素全部取出，放入到临时数组中，如果节点的左右孩子不为空，继续放入队列
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
            res.add(tmp);
        }
        // 翻转最终结果并返回
        Collections.reverse(res);
        return res;
    }

    // 参考：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/solution/san-chong-shi-xian-tu-jie-107er-cha-shu-de-ceng-ci/
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {

        if (root == null)
            return new ArrayList<List<Integer>>();

        // 改用链表实现，每次都插入到第一位
        LinkedList<List<Integer>> res = new LinkedList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
            // 每次都保存到链表的第一位，这样自带了翻转的功能
            res.add(0, tmp);
        }
        return res;
    }

    // 参考：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/solution/san-chong-shi-xian-tu-jie-107er-cha-shu-de-ceng-ci/
    public List<List<Integer>> levelOrderBottom3(TreeNode root) {

        if (root == null)
            return new ArrayList<List<Integer>>();

        // 用来存放最终结果
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        // 1 代表层数
        dfs(root, res, 1);
        Collections.reverse(res);
        return res;
    }

    private void dfs(TreeNode root, ArrayList<List<Integer>> res, int index) {

        if (root == null)
            return;

        // 如果 index 大于 res 大小，说明这一层没有对应的集合，需要新创建
        if (index > res.size())
            res.add(new ArrayList<Integer>());
        // 先序遍历
        // 将当前层的元素直接放到对应层的末尾即可
        res.get(index - 1).add(root.val);
        // 继续遍历左右节点，同时将层高 + 1
        dfs(root.left, res, index + 1);
        dfs(root.right, res, index + 1);
    }


}
