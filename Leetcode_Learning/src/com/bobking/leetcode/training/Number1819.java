package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-01-14 9:49
 */
public class Number1819 {

    // 参考：https://leetcode.cn/problems/number-of-different-subsequences-gcds/solution/ji-bai-100mei-ju-gcdxun-huan-you-hua-pyt-get7/
    // 参考：https://leetcode.cn/problems/number-of-different-subsequences-gcds/solution/xu-lie-zhong-bu-tong-zui-da-gong-yue-shu-lrka/
    public int countDifferentSubsequenceGCDs1(int[] nums) {

        int ans = 0;
        int max = 0;
        for (int x : nums)
            max = Math.max(max, x);

        boolean[] has = new boolean[max + 1];
        for (int x : nums)
            has[x] = true;

        for (int i = 1; i <= max; ++i) {
            // 0 和任何数 x 的最大公约数都是 x
            int g = 0;
            // 枚举 i 的倍数 j
            for (int j = i; j <= max && g != i; j += i)
                // 如果 j 在 nums 中
                if (has[j])
                    // 更新最大公约数
                    g = gcd(g, j);
            if (g == i)
                ++ans;
        }
        return ans;
    }

    // 参考：https://leetcode.cn/problems/number-of-different-subsequences-gcds/solution/ji-bai-100mei-ju-gcdxun-huan-you-hua-pyt-get7/
    // 参考：https://leetcode.cn/problems/number-of-different-subsequences-gcds/solution/xu-lie-zhong-bu-tong-zui-da-gong-yue-shu-lrka/
    public int countDifferentSubsequenceGCDs2(int[] nums) {

        int ans = 0;
        int max = 0;
        for (int x : nums)
            max = Math.max(max, x);

        boolean[] has = new boolean[max + 1];
        for (int x : nums) {
            if (!has[x]) {
                has[x] = true;
                ++ans;
            }
        }

        // 优化循环上界
        for (int i = 1; i <= max / 3; ++i) {
            if (has[i])
                continue;
            int g = 0;
            for (int j = i * 2; j <= max && g != i; j += i)
                if (has[j])
                    g = gcd(g, j);
            if (g == i)
                ++ans;
        }

        return ans;
    }

    private int gcd(int a, int b) {
        while (a != 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }

}
