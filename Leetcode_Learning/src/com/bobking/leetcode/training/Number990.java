package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-08-20 22:05
 */
public class Number990 {

    // 参考：https://leetcode.cn/problems/satisfiability-of-equality-equations/solution/deng-shi-fang-cheng-de-ke-man-zu-xing-by-leetcode-/
    public boolean equationsPossible(String[] equations) {

        // 可以将每一个变量看作图中的一个节点，把相等的关系 == 看作是连接两个节点的边，那么由于表示相等关系的等式方程具有传递性
        // 即如果 a == b 和 b == c 成立，则 a == c 也成立。也就是说，所有相等的变量属于同一个连通分量
        // 因此，可以使用并查集来维护这种连通分量的关系，首先遍历所有的等式，构造并查集。同一个等式中的两个变量属于同一个连通分量，因此将两个变量进行合并
        // 然后遍历所有的不等式。同一个不等式中的两个变量不能属于同一个连通分量，因此对两个变量分别查找其所在的连通分量
        // 如果两个变量在同一个连通分量中，则产生矛盾，返回 false
        // 如果遍历完所有的不等式没有发现矛盾，则返回 true

        int[] parent = new int[26];
        for (int i = 0; i < 26; i++)
            parent[i] = i;

        for (String str : equations) {
            if (str.charAt(1) == '=') {
                int index1 = str.charAt(0) - 'a';
                int index2 = str.charAt(3) - 'a';
                union(parent, index1, index2);
            }
        }

        for (String str : equations) {
            if (str.charAt(1) == '!') {
                int index1 = str.charAt(0) - 'a';
                int index2 = str.charAt(3) - 'a';
                if (find(parent, index1) == find(parent, index2))
                    return false;
            }
        }

        return true;
    }

    private void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    private int find(int[] parent, int index) {
        while (parent[index] != index) {
            parent[index] = parent[parent[index]];
            index = parent[index];
        }

        return index;
    }

}
