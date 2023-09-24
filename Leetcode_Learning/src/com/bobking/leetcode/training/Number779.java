package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-10-20 9:09
 */
public class Number779 {

    // 参考：https://leetcode.cn/problems/k-th-symbol-in-grammar/solution/zhao-gui-lu-hou-ban-bu-fen-shi-qian-ban-wahnd/
    public int kthGrammar(int n, int k) {

        if (n == 1)
            return 0;

        // 第 n 行的长度
        int length = (int) Math.pow(2, n - 1);

        if (k > length / 2) {
            int val = kthGrammar(n - 1, k - (length / 2));
            return val == 1 ? 0 : 1;
        } else {
            return kthGrammar(n - 1, k);
        }
    }
}
