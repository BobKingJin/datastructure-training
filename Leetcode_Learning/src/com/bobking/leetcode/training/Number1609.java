package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2022-09-17 11:41
 */
public class Number1609 {

    // 参考：https://leetcode.cn/problems/even-odd-tree/solution/gong-shui-san-xie-yi-ti-shuang-jie-bfs-d-kuyi/
    public boolean isEvenOddTree1(TreeNode root) {

        Deque<TreeNode> d = new ArrayDeque<TreeNode>();
        boolean flag = true;
        d.addLast(root);

        while (!d.isEmpty()) {
            int size = d.size();
            int prev = flag ? 0 : 0x3f3f3f3f;
            while (size-- > 0) {
                TreeNode node = d.pollFirst();
                int cur = node.val;
                if (flag && (cur % 2 == 0 || cur <= prev))
                    return false;
                if (!flag && (cur % 2 != 0 || cur >= prev))
                    return false;
                prev = cur;
                if (node.left != null)
                    d.addLast(node.left);
                if (node.right != null)
                    d.addLast(node.right);
            }
            flag = !flag;
        }
        return true;
    }

    Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    // 参考：https://leetcode.cn/problems/even-odd-tree/solution/gong-shui-san-xie-yi-ti-shuang-jie-bfs-d-kuyi/
    public boolean isEvenOddTree2(TreeNode root) {
        return dfs(root, 0);
    }

    private boolean dfs(TreeNode root, int idx) {

        // 先序遍历

        boolean flag = idx % 2 == 0;
        int prev = map.getOrDefault(idx, flag ? 0 : 0x3f3f3f3f);
        int cur = root.val;
        if (flag && (cur % 2 == 0 || cur <= prev))
            return false;
        if (!flag && (cur % 2 != 0 || cur >= prev))
            return false;
        map.put(idx, root.val);
        if (root.left != null && !dfs(root.left, idx + 1))
            return false;
        if (root.right != null && !dfs(root.right, idx + 1))
            return false;
        return true;
    }
}
