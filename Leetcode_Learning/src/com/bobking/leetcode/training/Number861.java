package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2020-12-07 21:19
 */
public class Number861 {

    // 参考：https://leetcode-cn.com/problems/score-after-flipping-matrix/solution/fan-zhuan-ju-zhen-hou-de-de-fen-by-leetc-cxma/
    public int matrixScore(int[][] A) {

        // 给定一个翻转方案，则它们之间任意交换顺序后，得到的结果保持不变，即最终结果与翻转顺序无关
        // 因此总可以先考虑所有的行翻转，再考虑所有的列翻转
        // 1、为了得到最高的分数，矩阵的每一行的最左边的数都必须为 1。为了做到这一点，可以翻转那些最左边的数不为 1 的那些行，
        // 而其他的行则保持不动
        // 2、当将每一行的最左边的数都变为 1 之后，就只能进行列翻转了，为了使得总得分最大，要让每个列中 1 的数目尽可能多
        // 因此，扫描除了最左边的列以外的每一列，如果该列 0 的数目多于 1 的数目，就翻转该列，其他的列则保持不变
        if (A == null || A.length == 0 || A[0].length == 0)
            return 0;

        int result = 0;
        int row = A.length;
        int column = A[0].length;
        // 对于最左边的列而言，由于最优情况下，它们的取值都为 1，因此每个元素对分数的贡献都为 2^(n - 1)
        // 总贡献为 m * 2^(n - 1)
        // 注意这里不需要先每一行进行翻转，后面 A[j][0] == 1 这个位置会判断每一行是否进行过翻转，则不会对结果产生影响
        result += row * (1 << (column - 1));

        // 对于第 j 列（j > 0，此处规定最左边的列是第 0 列）而言，统计这一列 0, 1的数量，令其中的最大值为 k
        // 则 k 是列翻转后的 1 的数量，该列的总贡献为 k * 2^(n - j - 1)
        // 需要注意的是，在统计 0, 1的数量的时候，要考虑最初进行的行反转
        for (int i = 1; i < column; i++) {
            // 从第一列开始，每一列含有的 1 的数量
            int oneNum = 0;
            for (int j = 0; j < row; j++) {
                // 注意：这里是判断的是 A[j][0]，即矩阵中原本的值，而不是进行移动之后的值
                if (A[j][0] == 1) {
                    // 若 A[j][0] == 1意味着原本即为 1，则这一行是没有移动的，那么A[j][i]原本是 1 的话就 + 1
                    oneNum += A[j][i];
                } else {
                    // 若 A[j][0] == 0意味着原本即为 0，则这一行是有移动的，那么A[j][i]原本是 0 的话现在就是 1，就 + 1
                    oneNum += (1 - A[j][i]);
                }
            }

            int K = Math.max(oneNum, row - oneNum);
            result += K * (1 << (column - i - 1));
        }

        return result;
    }
}
