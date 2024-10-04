package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

public class Number437 {

    // 路径方向必须是向下的，只能从父节点到子节点
    // 参考：https://leetcode-cn.com/problems/path-sum-iii/solution/qian-zhui-he-di-gui-hui-su-by-shi-huo-de-xia-tian/
    // 即判断从根节点到每一个叶节点的路径中时候有 sum 出现
    public int pathSum(TreeNode root, int sum) {

        if (root == null)
            return 0;
        // 记忆   前缀和    二叉树中每个节点值都不相同???
        // 前缀和是从根节点到当前节点的和
        // 因为路径必须是从上往下，所以用前缀和
        Map<Long, Integer> prefixSum = new HashMap<Long, Integer>();
        // 前缀和为 0 的有一条路径
        prefixSum.put(0L, 1);

        return recursionPathSum(root, 0, sum, prefixSum);
    }

    // 递归
    private int recursionPathSum(TreeNode node, long currSum, int sum, Map<Long, Integer> prefixSum) {

        // 递归结束条件
        if (node == null)
            return 0;

        int res = 0;
        // 先序遍历
        currSum = currSum + node.val;
        // 例如：10 -> 5 -> 3 的前缀和是 18，所以应该判断 map 中是否有前缀和为 18 - 8 = 10
        // 如果有的话，那么从 5 -> 3的和就为 target
        res += prefixSum.getOrDefault(currSum - sum, 0);
        prefixSum.put(currSum, prefixSum.getOrDefault(currSum, 0) + 1);
        res += recursionPathSum(node.left, currSum, sum, prefixSum);
        res += recursionPathSum(node.right, currSum, sum, prefixSum);
        // 回溯
        prefixSum.put(currSum, prefixSum.get(currSum) - 1);
        return res;
    }
}
