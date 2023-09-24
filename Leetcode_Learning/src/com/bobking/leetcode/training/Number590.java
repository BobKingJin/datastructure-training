package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Number590 {

    private class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // 参考：https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/solution/ncha-shu-de-hou-xu-bian-li-by-leetcode/
    public List<Integer> postorder(Node root) {

        LinkedList<Integer> res = new LinkedList<Integer>();
        if (root == null)
            return res;

        Deque<Node> stack = new ArrayDeque<Node>();
        stack.addLast(root);
        while (!stack.isEmpty()) {
            Node node = stack.removeLast();
            res.addFirst(node.val);
            for (int i = 0; i < node.children.size(); i++)
                stack.addLast(node.children.get(i));
        }

        return res;
    }

}
