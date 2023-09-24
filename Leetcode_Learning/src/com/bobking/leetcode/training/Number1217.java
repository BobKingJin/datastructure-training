package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-06-17 23:56
 */
public class Number1217 {

    // 参考：https://leetcode.cn/problems/minimum-cost-to-move-chips-to-the-same-position/solution/xian-li-jie-ti-yi-zai-li-jie-dai-ma-si-lu-by-athen/
    public int minCostToMoveChips(int[] position) {

        // 因为移动 2 个位置不需要代价，那么奇数位置移到奇数位置不用代价，偶数位置移到偶数位置不用代价
        // 那就分别统计奇数位置和偶数位置的个数，相当于把所有奇数放一起，所有偶数的放一起
        // 然后比较奇数的少还是偶数的少，将少的个数移到多的个数位置上去就可以了

        int odd = 0;
        int even = 0;

        for (int i = 0; i < position.length; i++) {
            if (position[i] % 2 == 0) {
                even++;
            } else if (position[i] % 2 != 0) {
                odd++;
            }
        }

        return Math.min(even, odd);
    }
}
