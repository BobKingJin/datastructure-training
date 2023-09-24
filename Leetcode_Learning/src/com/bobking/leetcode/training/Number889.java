package com.bobking.leetcode.training;

import java.util.Arrays;

public class Number889 {

    // 参考：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/solution/gen-ju-qian-xu-he-hou-xu-bian-li-gou-zao-er-cha-sh/
    public TreeNode constructFromPrePost(int[] pre, int[] post) {

        int N = pre.length;
        if (N == 0)
            return null;

        TreeNode root = new TreeNode(pre[0]);
        if (N == 1)
            return root;

        // 先序遍历左分支的头节点为 pre[1]，但它也出现在左分支的后序表示的最后
        // 所以 pre[1] = post[L - 1]
        int L = 0;
        for (int i = 0; i < N; ++i)
            if (post[i] == pre[1])
                L = i + 1;

        root.left = constructFromPrePost(Arrays.copyOfRange(pre, 1, L + 1),
                Arrays.copyOfRange(post, 0, L));
        root.right = constructFromPrePost(Arrays.copyOfRange(pre, L + 1, N),
                Arrays.copyOfRange(post, L, N - 1));

        return root;
    }
}
