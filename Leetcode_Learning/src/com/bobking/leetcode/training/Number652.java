package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2022-09-09 17:02
 */
public class Number652 {

    Map<String, TreeNode> seen = new HashMap<String, TreeNode>();
    Set<TreeNode> repeat = new HashSet<TreeNode>();

    // 参考：https://leetcode.cn/problems/find-duplicate-subtrees/solution/xun-zhao-zhong-fu-de-zi-shu-by-leetcode-zoncw/
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {

        // 使用递归的方法进行序列化
        // 可以将一棵以 x 为根节点值的子树序列化为：x(左子树的序列化结果)(右子树的序列化结果)

        dfs(root);
        return new ArrayList<TreeNode>(repeat);
    }

    private String dfs(TreeNode node) {

        // 如果子树为空，那么序列化结果为空串
        if (node == null)
            return "";

        StringBuilder sb = new StringBuilder();
        sb.append(node.val);
        sb.append("(");
        // 左子树递归地进行序列化
        sb.append(dfs(node.left));
        sb.append(")(");
        // 右子树递归地进行序列化
        sb.append(dfs(node.right));
        sb.append(")");

        String serial = sb.toString();
        if (seen.containsKey(serial)) {
            repeat.add(seen.get(serial));
        } else {
            seen.put(serial, node);
        }
        return serial;
    }
}
