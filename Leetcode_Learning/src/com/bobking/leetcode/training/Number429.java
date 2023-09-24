package com.bobking.leetcode.training;

import java.util.*;

public class Number429 {

    private class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // 参考：https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/solution/by-ac_oier-yeye/
    public List<List<Integer>> levelOrder1(Node root) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Deque<Node> d = new ArrayDeque<Node>();
        if (root != null)
            d.addLast(root);

        while (!d.isEmpty()) {

            int size = d.size();
            List<Integer> list = new ArrayList<Integer>();

            while (size-- > 0) {

                Node t = d.pollFirst();
                for (Node node : t.children)
                    d.addLast(node);

                list.add(t.val);
            }

            res.add(list);
        }

        return res;
    }

    int max;
    Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();

    // 参考：https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/solution/by-ac_oier-yeye/
    public List<List<Integer>> levelOrder2(Node root) {
        
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) 
            return res;
        
        dfs(root, 0);
        
        for (int i = 0; i <= max; i++) 
            res.add(map.get(i));
        
        return res;
    }

    private void dfs(Node u, int depth) {

        max = Math.max(max, depth);
        List<Integer> list = map.getOrDefault(depth, new ArrayList<Integer>());
        list.add(u.val);
        map.put(depth, list);

        for (Node node : u.children)
            dfs(node, depth + 1);
    }
    
}
