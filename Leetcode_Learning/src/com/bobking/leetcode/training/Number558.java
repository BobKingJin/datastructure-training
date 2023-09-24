package com.bobking.leetcode.training;

public class Number558 {

    private class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {}

        public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    }

    // 参考：https://leetcode.cn/problems/logical-or-of-two-binary-grids-represented-as-quad-trees/solution/sichashu-by-jiang-hui-4-1x8r/
    public Node intersect(Node quadTree1, Node quadTree2) {

        // quadTree1 是叶子节点
        if (quadTree1.isLeaf) {
            // 值是 true
            if (quadTree1.val)
                return new Node(true, true, null, null, null, null);
            // 值不是 true
            return quadTree2;
        }
        // quadTree2 是叶子节点
        if (quadTree2.isLeaf) {
            // 值是 true
            if (quadTree2.val)
                return new Node(true, true, null, null, null, null);
            // 值不是 true
            return quadTree1;
        }
        // 都不是叶子节点
        Node topLeft = intersect(quadTree1.topLeft, quadTree2.topLeft);
        Node topRight = intersect(quadTree1.topRight, quadTree2.topRight);
        Node bottomLeft = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
        Node bottomRight = intersect(quadTree1.bottomRight, quadTree2.bottomRight);
        // 四个子节点都是叶子节点并且值相同
        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
                && topLeft.val == topRight.val && topLeft.val == bottomLeft.val && topLeft.val == bottomRight.val)
            return new Node(topLeft.val, true, null, null, null, null);

        return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
    }


}
