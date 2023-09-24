package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

public class Number671 {

    private Set<Integer> set = new HashSet<Integer>();

    // 参考：https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree/
    public int findSecondMinimumValue1(TreeNode root) {

        if (root == null || (root.left == null && root.right == null))
            return -1;

        dfs1(root);
        if (set.size() < 2)
            return -1;

        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int i : set) {
            if (i <= first) {
                second = first;
                first = i;
            } else if (i <= second) {
                second = i;
            }
        }

        return second;
    }

    // 先序遍历
    private void dfs1(TreeNode root) {

        if (root == null)
            return;
        set.add(root.val);
        dfs1(root.left);
        dfs1(root.right);
    }

    int res = -1;

    // 参考：https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree/
    public int findSecondMinimumValue2(TreeNode root) {

        dfs(root, root.val);
        return res;
    }

    // 从 root 为根的树进行搜索，找到值比 cur 大的最小数
    // 注意 root 根节点即为整颗树的最小值
    private void dfs(TreeNode root, int cur) {

        // 结合「每个子节点数量要么是 0 要么是 2」，可以特判一下 res 是否为第一次赋值
        // 如果给 res 赋了新值或者更新了更小的 res，则不再需要往下搜索了
        if (root == null)
            return;

        // 因为一遇到和总的根节点的值 root.val = cur 不同的点，就把结果存下来，并返回
        // 所以就不会再比较它下面的所有点，也就是直接比较子树的最小值后返回
        // 总共比较两个子树的最小值，即可得到次最小值
        if (root.val != cur) {

            if (res == -1) {
                res = root.val;
            } else {
                // 因为是 dfs，即当第一次遇到 root.val != cur 时，找到了对应的结果，后面会进行递归返回，所以这里还需
                // 取 res, root.val 中的更小者，即保留上次返回的结果
                res = Math.min(res, root.val);
            }

            return;
        }

        dfs(root.left, cur);
        dfs(root.right, cur);
    }


}
