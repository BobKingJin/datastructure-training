package com.bobking.leetcode.training;

public class Jianzhi26 {

    // 参考：程序猿代码指南P144
    // 参考：https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/solution/mian-shi-ti-26-shu-de-zi-jie-gou-xian-xu-bian-li-p/
    // 参考: LCR143
    // 注意原题目是 子结构，而程序猿代码指南P144是 子树，这会导致最后在 doesTree1HasTree2()方法中递归结束条件中有区别
    // 对比: Number572
    public boolean isSubStructure(TreeNode A, TreeNode B) {

        // 先序遍历，然后逐个逐个节点进行判断
        boolean result = false;
        if (A == null || B == null) {
            return result;
        }
        // 在doesTree1HasTree2()方法中会判断 root1.val == root2.val ?
        result = doesTree1HasTree2(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
        return result;
    }

    private boolean doesTree1HasTree2(TreeNode root1, TreeNode root2) {

        if (root2 == null) {
            return true;
        }

        if (root1 == null) {
            return false;
        }

        if (root1.val != root2.val) {
            return false;
        }
        return doesTree1HasTree2(root1.left, root2.left) && doesTree1HasTree2(root1.right,
            root2.right);
    }
}
