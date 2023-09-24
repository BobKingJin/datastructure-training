package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Number1104 {

    // 参考：https://leetcode.cn/problems/path-in-zigzag-labelled-binary-tree/solution/er-cha-shu-xun-lu-by-leetcode-solution-ryx0/
    public List<Integer> pathInZigZagTree(int label) {

        // 得到标号为 label 的节点的「从左到右标号」之后，即可得到从该节点到根节点的路径
        // 以及路径上的每个节点的「从左到右标号」
        // 对于路径上的每个节点，需要根据节点所在行的奇偶性，得到该节点的实际标号：
        // 当 i 是奇数时，第 i 行的每个节点的「从左到右标号」即为该节点的实际标号
        // 当 i 是偶数时，如果第 i 行的一个节点的「从左到右标号」为 val，则该节点的实际标号为
        // 2 ^ (i − 1) + 2 ^ i − 1 − val

        int row = 1;
        int rowStart = 1;
        // 找到 label 所在的行
        while (rowStart * 2 <= label) {
            row++;
            rowStart *= 2;
        }

        if (row % 2 == 0)
            label = getReverse(label, row);

        List<Integer> path = new ArrayList<Integer>();

        while (row > 0) {
            if (row % 2 == 0) {
                path.add(getReverse(label, row));
            } else {
                path.add(label);
            }
            row--;
            // label 父节点的值为 label / 2
            label >>= 1;
        }

        Collections.reverse(path);
        return path;
    }

    private int getReverse(int label, int row) {
        return (1 << row - 1) + (1 << row) - 1 - label;
    }
}
