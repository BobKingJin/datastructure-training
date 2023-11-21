package com.bobking.leetcode.training;

import java.util.*;

public class Number559 {

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

    // 参考：https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/solution/ncha-shu-de-zui-da-shen-du-by-leetcode/
    // 对比Number104
    // dfs
    public int maxDepth1(Node root) {

        if (root == null) {
            return 0;
        } else if (root.children.size() == 0) {
            return 1;
        } else {
            List<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < root.children.size(); i++)
                list.add(maxDepth1(root.children.get(i)));

            return Collections.max(list) + 1;
        }
    }

    // 参考：https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/solution/ncha-shu-de-zui-da-shen-du-by-leetcode/
    // bfs
    public int maxDepth2(Node root) {

        Queue<AbstractMap.SimpleEntry<Node, Integer>> stack = new LinkedList<AbstractMap.SimpleEntry<Node, Integer>>();

        if (root != null)
            stack.add(new AbstractMap.SimpleEntry(root, 1));

        int depth = 0;
        while (!stack.isEmpty()) {
            AbstractMap.SimpleEntry<Node, Integer> current = stack.poll();
            root = current.getKey();
            int currentDepth = current.getValue();
            if (root != null) {
                depth = Math.max(depth, currentDepth);
                for (Node c : root.children)
                    stack.add(new AbstractMap.SimpleEntry(c, currentDepth + 1));
            }
        }

        return depth;
    }


}
