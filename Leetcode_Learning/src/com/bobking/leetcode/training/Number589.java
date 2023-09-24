package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Number589 {

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

    LinkedList<Node> list = new LinkedList<Node>();
    List<Integer> res1 = new ArrayList<Integer>();

    public List<Integer> preorder1(Node root) {

        if (root == null)
            return res1;

        list.add(root);

        while (!list.isEmpty()) {
            Node node = list.pollLast();
            res1.add(node.val);
            for (int i = node.children.size() - 1; i >= 0; i--) {
                if (node.children.get(i) != null)
                    list.add(node.children.get(i));
            }
        }

        return res1;
    }

    List<Integer> res2 = new ArrayList<Integer>();

    public List<Integer> preorder2(Node root) {
        dfs(root);
        return res2;
    }

    private void dfs(Node root) {

        if (root == null)
            return;

        res2.add(root.val);
        for (int i = 0; i < root.children.size(); i++)
            dfs(root.children.get(i));
    }

}
