package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-07-30 12:35
 */
public class Number781 {

    // 参考：https://leetcode.cn/problems/rabbits-in-forest/solution/gong-shui-san-xie-noxiang-xin-ke-xue-xi-v17p5/
    public int numRabbits(int[] answers) {

        // 不妨设有某种颜色的兔子 m 只，它们回答的答案数值为 cnt，那么 m 和 cnt 满足什么关系？
        // 显然两者关系为 m = cnt + 1
        // 但如果是在 answers 数组里，回答 cnt 的数量为 t 的话呢？这时候需要分情况讨论：
        // t ⩽ cnt + 1 : 为达到「最少的兔子数量」，可以假定这 t 只兔子为同一颜色，这样能够满足题意，同时不会导致「额外」兔子数量增加（颜色数量最少）
        // t > cnt + 1: 知道回答 cnt 的兔子应该有 cnt + 1 只。这时候说明有数量相同的不同颜色的兔子进行了回答
        // 为达到「最少的兔子数量」，应当将 t 分为若干种颜色，并尽可能让某一种颜色的兔子为 cnt + 1 只
        // 这样能够满足题意，同时不会导致「额外」兔子数量增加（颜色数量最少）。
        // 换句话说，应该让「同一颜色的兔子数量」尽量多，从而实现「总的兔子数量」最少

        Arrays.sort(answers);
        int n = answers.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            int cnt = answers[i];
            ans += cnt + 1;
            // 跳过「数值 cnt」后面的 cnt 个「数值 cnt」
            int k = cnt;
            while (k-- > 0 && i + 1 < n && answers[i] == answers[i + 1])
                i++;
        }

        return ans;
    }
}
