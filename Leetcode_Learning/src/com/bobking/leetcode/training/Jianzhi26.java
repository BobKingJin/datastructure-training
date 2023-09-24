package com.bobking.leetcode.training;

public class Jianzhi26 {

    // 参考：程序猿代码指南P144
    // 参考剑指offer：https://www.nowcoder.com/practice/6e196c44c7004d15b1610b9afca8bd88?tpId=13&&tqId=11170&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
    // 参考：https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/solution/mian-shi-ti-26-shu-de-zi-jie-gou-xian-xu-bian-li-p/
    // 注意原题目是 子结构，而程序猿代码指南P144是 子树，这会导致最后在 doesTree1HasTree2()方法中递归结束条件中有区别
    public boolean isSubStructure(TreeNode A, TreeNode B) {

        // 先序遍历，然后逐个逐个节点进行判断
        boolean result = false;
        if (A == null || B == null)
            return result;
        // 如果 Tree1 的根节点和 Tree2 的根节点值相等，则直接判断
        if (A.val == B.val)
            result = doesTree1HasTree2(A, B);
        // 如果 Tree1 的根节点和 Tree2 的根节点的值不相等或者两棵树的根节点的值相等但是返回的 result 为 false
        // 那么继续判断 Tree1 根节点的左孩子和 Tree2 的根节点值是否相等
        if (!result)
            result = isSubStructure(A.left, B);
        // 同理判断右孩子
        if (!result)
            result = isSubStructure(A.right, B);

        return result;
    }

    private boolean doesTree1HasTree2(TreeNode root1, TreeNode root2) {

        // 如果 Tree2 树遍历完了且与 Tree1 每个节点上面的值都相等，那么返回 true
        if (root2 == null)
            return true;
        // 如果 Tree1 树遍历完了，但是此时 Tree2 树还存在子节点，那么返回 false
        if (root1 == null)
            return false;
        if (root1.val != root2.val)
            return false;

        return doesTree1HasTree2(root1.left, root2.left) && doesTree1HasTree2(root1.right, root2.right);
    }
}
