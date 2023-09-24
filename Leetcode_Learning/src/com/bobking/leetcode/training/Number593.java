package com.bobking.leetcode.training;

public class Number593 {

    long len = -1;

    // 参考：https://leetcode.cn/problems/valid-square/solution/by-ac_oier-lwdf/
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {

        // 从给定的 4 个顶点中选 3 个顶点，检查其能否形成「直角三角形」，同时保存下来首个直角三角形的直角边边长
        // 供后续其余直角三角形进行对比（注意不能共点，即直角边长不能为 0）

        return calc(p1, p2, p3) && calc(p1, p2, p4) && calc(p1, p3, p4) && calc(p2, p3, p4);
    }

    private boolean calc(int[] a, int[] b, int[] c) {

        long l1 = (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
        long l2 = (a[0] - c[0]) * (a[0] - c[0]) + (a[1] - c[1]) * (a[1] - c[1]);
        long l3 = (b[0] - c[0]) * (b[0] - c[0]) + (b[1] - c[1]) * (b[1] - c[1]);

        boolean ok = (l1 == l2 && l1 + l2 == l3) || (l1 == l3 && l1 + l3 == l2) || (l2 == l3 && l2 + l3 == l1);

        if (!ok)
            return false;

        if (len == -1){
            // 保存下来首个直角三角形的直角边边长
            len = Math.min(l1, l2);
        } else if (len == 0 || len != Math.min(l1, l2)) {
            return false;
        }

        return true;
    }
}
