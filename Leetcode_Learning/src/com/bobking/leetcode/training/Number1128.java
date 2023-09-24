package com.bobking.leetcode.training;

public class Number1128 {

    // 参考：https://leetcode.cn/problems/number-of-equivalent-domino-pairs/solution/deng-jie-duo-mi-nuo-gu-pai-dui-de-shu-li-yjlz/
    public int numEquivDominoPairs(int[][] dominoes) {

        // 本题中需要统计所有等价的多米诺骨牌，其中多米诺骨牌使用二元对代表
        // 「等价」的定义是，在允许翻转两个二元对的的情况下，使它们的元素一一对应相等
        // 于是不妨直接让每一个二元对都变为指定的格式，即第一维必须不大于第二维。这样两个二元对「等价」当且仅当两个二元对完全相同
        // 注意到二元对中的元素均不大于 9，因此可以将每一个二元对拼接成一个两位的正整数
        // 即 (x, y) → 10 * x + y 这样就无需使用哈希表统计元素数量，而直接使用长度为 100100 的数组即可

        int[] num = new int[100];
        int res = 0;

        for (int[] domino : dominoes) {
            int val = domino[0] < domino[1] ? domino[0] * 10 + domino[1] : domino[1] * 10 + domino[0];
            res += num[val];
            num[val]++;
        }

        return res;
    }

}
