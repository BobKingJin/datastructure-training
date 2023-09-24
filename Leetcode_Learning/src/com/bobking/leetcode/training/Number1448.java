package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-01-11 11:07
 */
public class Number1448 {

    private int nodeNum = 0;

    public int goodNodes(TreeNode root) {
        recur(root , Integer.MIN_VALUE);
        return nodeNum;
    }

    // max用于记录从顶层递归到本层的最大节点值
    // 若节点大于 max 说明该节点不小于该结点到根节点路径上所有节点
    private void recur(TreeNode node , int max){
        if(node == null)
            return;
        if(node.val >= max){
            nodeNum++;
            max = node.val;
        }
        // 先序遍历
        recur(node.left , max);
        recur(node.right , max);
    }
}
