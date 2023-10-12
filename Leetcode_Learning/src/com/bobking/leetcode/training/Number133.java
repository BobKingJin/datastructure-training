package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author BobKing
 * @create 2023-10-11 8:03
 */
public class Number133 {

    private class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    private HashMap<Node, Node> visited = new HashMap<Node, Node>();

    // 参考: https://leetcode.cn/problems/clone-graph/solutions/370663/ke-long-tu-by-leetcode-solution/
    public Node cloneGraph(Node node) {

        // 深拷贝即构建一张与原图结构，值均一样的图，但是其中的节点不再是原来图节点的引用

        if (node == null)
            return node;

        // 避免在深拷贝时陷入死循环
        // 如果该节点已经被访问过了，则直接从哈希表中取出对应的克隆节点返回
        if (visited.containsKey(node))
            return visited.get(node);

        // 克隆节点，注意到为了深拷贝不会克隆它的邻居的列表
        Node cloneNode = new Node(node.val, new ArrayList<Node>());
        // 哈希表存储
        visited.put(node, cloneNode);

        // 遍历该节点的邻居并更新克隆节点的邻居列表
        for (Node neighbor : node.neighbors)
            cloneNode.neighbors.add(cloneGraph(neighbor));

        return cloneNode;
    }
}
