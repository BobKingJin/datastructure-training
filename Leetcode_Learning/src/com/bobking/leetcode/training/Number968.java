package com.bobking.leetcode.training;

public class Number968 {

    int res = 0;

    // 参考：https://leetcode.cn/problems/binary-tree-cameras/solution/968-jian-kong-er-cha-shu-di-gui-shang-de-zhuang-ta/
    public int minCameraCover(TreeNode root) {
        // 如果父节点是无覆盖状态，那么需要在父节点添加一台摄像机
        return dfs(root) == 0 ? res + 1 : res;
    }

    private int dfs(TreeNode root) {

        // 从底向上进行推导，因为尽量让叶子节点的父节点安装摄像头，这样摄像头的数量才是最少的

        // 每个节点可以说有如下三种：1、该节点无覆盖 2、本节点有摄像头 3、本节点有覆盖
        // 分别有三个数字来表示：0：该节点无覆盖 1：本节点有摄像头 2：本节点有覆盖
        // 空节点的状态只能是有覆盖，这样就可以在叶子节点的父节点放摄像头了
        if (root == null)
            // 节点有覆盖
            return 2;
        int left = dfs(root.left);
        int right = dfs(root.right);
        // 0表示无覆盖，1表示有相机，2表示有覆盖，左右节点可以组成 9 种状态
        // (0 0), (0 1), (0 2), (1 0), (1 1), (1 2), (2 0), (2 1), (2 2)

        // 只要有一个无覆盖，父节点就需要相机来覆盖这个子节点 (0 0), (0 1), (1 0), (2 0), (0 2)
        if (left == 0 || right == 0) {
            res++;
            return 1;
        }
        // 子节点其中只要有一个有相机，那么父节点就会是有覆盖的状态 (1 1), (2 1), (1 2)
        if (left == 1 || right == 1)
            return 2;

        // 还有一个就是(2 2)，两个子节点都是有覆盖的状态，父节点可以没有相机，可以借助它自己的父节点
        return 0;
    }
}
