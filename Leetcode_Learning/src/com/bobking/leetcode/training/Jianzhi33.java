package com.bobking.leetcode.training;

public class Jianzhi33 {

    // 参考：程序猿代码指南P148
    public boolean verifyPostorder(int[] postorder) {

        if (postorder == null || postorder.length < 1)
            return false;

        return verifyPostorder(postorder, 0, postorder.length - 1);
    }

    private boolean verifyPostorder(int[] postOrder, int start, int end) {

        if (start == end)
            return true;

        // less 为小于 postOrder[end] 部分的最右角标
        int less = -1;
        // more 为大于 postOrder[end] 部分的最左角标
        int more = end;

        for (int i = start; i < end; i++) {

            if (postOrder[i] < postOrder[end]) {
                less = i;
            } else {
                more = more == end ? i : more;
            }
        }

        // 注意：若 less = -1 或者 more = end，则说明严格升序或者降序
        if (less == -1 || more == end)
            return verifyPostorder(postOrder, start, end - 1);

        // 注意这个位置可以提前返回结果
        if (less != more - 1)
            return false;

        // 左子树                                          右子树
        return verifyPostorder(postOrder, start, less) && verifyPostorder(postOrder, more, end - 1);
    }
}
