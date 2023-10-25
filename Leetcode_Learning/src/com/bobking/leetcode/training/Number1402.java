package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-10-22 10:07
 */
public class Number1402 {

    // 参考: https://leetcode.cn/problems/reducing-dishes/solutions/2492854/mei-ju-zuo-ji-dao-cai-tan-xin-pythonjava-k7w2/?envType=daily-question&envId=2023-10-22
    public int maxSatisfaction(int[] satisfaction) {

        // 越往后乘积越大，越大的数应该放在最后
        // 从后面一个一个加入，每新加一个数，之前加过的所有数都会多加一遍
        Arrays.sort(satisfaction);
        // f(0) = 0
        int f = 0;
        int s = 0;

        for (int i = satisfaction.length - 1; i >= 0; i--) {
            s += satisfaction[i];
            // 后面不可能找到更大的 f(k)
            if (s <= 0)
                break;
            // f(k) = f(k - 1) + s
            f += s;
        }
        return f;
    }
}
