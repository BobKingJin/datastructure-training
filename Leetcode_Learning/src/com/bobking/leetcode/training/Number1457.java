package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-11-03 11:05
 */
public class Number1457 {

    int res = 0;

    public int pseudoPalindromicPaths(TreeNode root) {
        int[] arr = new int[10];
        preOrder(root, arr);
        return res;
    }

    private void preOrder(TreeNode root, int[] arr) {

        if (root != null) {
            arr[root.val]++;
            // 叶子节点 ，统计数组中奇数个元素的个数
            if (root.left == null && root.right == null) {
                int cnt = 0;
                for (int a : arr) {
                    if (a % 2 != 0)
                        cnt++;
                }
                // 奇数个元素的小于2，才能组成回文序列
                if (cnt <= 1)
                    res++;
                return;
            } else {
                // 左节点不为空，将数组拷贝出一个新的，继续前序遍历
                if (root.left != null)
                    preOrder(root.left, Arrays.copyOfRange(arr, 0, arr.length));

                if (root.right != null)
                    preOrder(root.right, Arrays.copyOfRange(arr, 0, arr.length));
            }
        }
    }
}
