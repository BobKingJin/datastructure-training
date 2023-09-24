package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-03-04 9:31
 */
public class Number982 {

    // 参考：https://leetcode.cn/problems/triples-with-bitwise-and-equal-to-zero/solution/you-ji-qiao-de-mei-ju-chang-shu-you-hua-daxit/
    public int countTriplets1(int[] nums) {

        int[] cnt = new int[1 << 16];
        for (int x : nums) {
            for (int y : nums)
                ++cnt[x & y];
        }

        int ans = 0;

        for (int x : nums) {
            for (int y = 0; y < 1 << 16; ++y) {
                if ((x & y) == 0)
                    ans += cnt[y];
            }
        }

        return ans;
    }

    // 参考：https://leetcode.cn/problems/triples-with-bitwise-and-equal-to-zero/solution/you-ji-qiao-de-mei-ju-chang-shu-you-hua-daxit/
    public int countTriplets2(int[] nums) {

        int[] cnt = new int[1 << 16];
        for (int x : nums) {
            for (int y : nums)
                ++cnt[x & y];
        }

        int ans = 0;

        for (int m : nums) {
            m ^= 0xffff;
            int s = m;
            do { // 枚举 m 的子集（包括空集）
                ans += cnt[s];
                s = (s - 1) & m;
            } while (s != m);
        }
        return ans;
    }
}
