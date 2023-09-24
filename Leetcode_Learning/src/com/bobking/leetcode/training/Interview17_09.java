package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-28 9:30
 */
public class Interview17_09 {

    // 参考：https://leetcode.cn/problems/get-kth-magic-number-lcci/solution/di-k-ge-shu-jiu-shi-xiang-bu-tong-wei-he-san-zhi-z/
    public int getKthMagicNumber(int k) {

        int[] numList = new int[k + 1];
        int p3 = 0;
        int p5 = 0;
        int p7 = 0;

        numList[0] = 1;

        for (int i = 1; i < k; i++) {
            numList[i] = Math.min(Math.min(numList[p3] * 3, numList[p5] * 5), numList[p7] * 7);
            if (numList[i] == numList[p3] * 3)
                p3++;
            if (numList[i] == numList[p5] * 5)
                p5++;
            if (numList[i] == numList[p7] * 7)
                p7++;
        }
        return numList[k - 1];
    }
}
