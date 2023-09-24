package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

public class Number545 {

    // 参考：程序猿代码指南P100，第一种边界节点定义
    public List<Integer> boundaryOfBinaryTree1(TreeNode root) {

        List<Integer> res = new ArrayList<>();

        if (root == null)
            return res;

        int height = getHeight(root, 0);
        TreeNode[][] edgeMap = new TreeNode[height][2];
        setEdgeMap(root, 0, edgeMap);

        // 打印左边界节点
        for (int i = 0; i < edgeMap.length; i++)
            res.add(edgeMap[i][0].val);

        // 打印不是某一层最左或最右节点，但同时又是叶子节点的节点
        printLeafNotInMap(root, 0, edgeMap, res);

        // 打印右边界节点
        for (int i = edgeMap.length - 1; i >= 0; i--)
            res.add(edgeMap[i][1].val);

        return res;
    }

    private int getHeight(TreeNode node, int height) {

        if (node == null)
            return height;

        return Math.max(getHeight(node.left, height + 1), getHeight(node.right, height + 1));
    }

    // 先序遍历
    private void setEdgeMap(TreeNode node, int l, TreeNode[][] edgeMap) {

        if (node == null)
            return;

        edgeMap[l][0] = edgeMap[l][0] == null ? node : edgeMap[l][0];
        edgeMap[l][1] = node;

        setEdgeMap(node.left, l + 1, edgeMap);
        setEdgeMap(node.right, l + 1, edgeMap);
    }

    private void printLeafNotInMap(TreeNode node, int l, TreeNode[][] edgeMap, List<Integer> list) {

        if (node == null)
            return;

        if (node.left == null && node.right == null && node.left != edgeMap[l][0] && node.right != edgeMap[l][1])
            list.add(node.val);

        printLeafNotInMap(node.left, l + 1, edgeMap, list);
        printLeafNotInMap(node.right, l + 1, edgeMap, list);
    }

    List<Integer> res = new ArrayList<Integer>();

    public List<Integer> boundaryOfBinaryTree2(TreeNode root) {

        if (root == null)
            return res;

        res.add(root.val);
        if (root.left != null && root.right != null) {
            printLeftEdge(root.left, true);
            printRightEdge(root.right, true);
        } else {
            boundaryOfBinaryTree2(root.left == null ? root.right : root.left);
        }

        return res;
    }

    private void printLeftEdge(TreeNode node, boolean print) {

        if (node == null)
            return;

        if (print || (node.left == null && node.right == null))
            res.add(node.val);

        printLeftEdge(node.left, print);
        //                                  左节点不为空，那么此时应置 print 为 false，即左边界已经遍历完
        printLeftEdge(node.right, print && (node.left == null ? true : false));
    }

    private void printRightEdge(TreeNode node, boolean print) {

        if (node == null)
            return;

        printRightEdge(node.left, print && (node.right == null ? true : false));
        printRightEdge(node.right, print);
        if (print || (node.left == null && node.right == null))
            res.add(node.val);
    }

}
