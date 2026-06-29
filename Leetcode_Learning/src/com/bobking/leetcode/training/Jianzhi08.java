package com.bobking.leetcode.training;

/**
 * @Date: 2024/11/1 12:17
 * @Author: BobKing
 * @Description:
 */
public class Jianzhi08 {

    private class TreeLinkNode {

        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    // 描述: https://www.nowcoder.com/practice/9023a0c988684a53960365b889ceaf5e?tpId=13&tqId=23451&ru=/exam/oj/ta&qru=/ta/coding-interviews/question-ranking&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26tpId%3D13%26type%3D13
    public TreeLinkNode GetNext(TreeLinkNode pNode) {

        // 1.如果给出的节点有右子节点, 则最终要返回的下一个节点即右子树的最左下的节点
        // 2.如果给出的节点无右子节点，且当前节点是其父节点的 左子节点, 则返回其父节点
        // 3.如果给出的节点无右子节点，且当前节点是其父节点的 右子节点, 则先要沿着左上方父节点爬树, 一直爬到当前节点是其父节点的左子节点为止, 返回的就是这个父节点
        // 4.或者没有满足上述情况的则返回为NULL

        // 1.二叉树为空，则返回空；
        if (pNode == null) {
            return null;
        }

        // 2.如果有右子树, 则找右子树的最左节点
        if (pNode.right != null) {
            pNode = pNode.right;
            while (pNode.left != null) {
                pNode = pNode.left;
            }
            return pNode;
        }

        // 3.没右子树，则找第一个当前节点是父节点左孩子的节点
        while (pNode.next != null) {
            if (pNode.next.left == pNode) {
                return pNode.next;
            }
            pNode = pNode.next;
        }
        return null;
    }
}
