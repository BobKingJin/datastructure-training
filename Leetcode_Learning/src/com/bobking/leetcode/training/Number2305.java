package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-03-26 14:06
 */
public class Number2305 {

    int ans = Integer.MAX_VALUE;
    int[] cookies;
    int n;
    int k;

    // 参考：https://leetcode.cn/problems/fair-distribution-of-cookies/solution/jian-zhi-hui-su-by-baoya_uncle-7s48/
    public int distributeCookies1(int[] cookies, int k) {

        Arrays.sort(cookies);

        this.cookies = cookies;
        this.k = k;
        n = cookies.length;
        backtrack(new int[k], n - 1);

        return ans;
    }

    // bucket数组存放 k 个小朋友每个人当前的饼干数量, start为下一个要分发的饼干包下标
    private void backtrack(int[] bucket, int start) {

        // 饼干发完了，统计哪个小朋友获得的饼干最多，更新答案
        if (start < 0) {
            int curAns = Integer.MIN_VALUE;
            for (int count : bucket)
                curAns = Math.max(curAns, count);

            ans = Math.min(ans, curAns);
            return;
        }

        // 剪枝1: 如果剩余的饼干包不够空手的小朋友分了, 直接返回
        int zeroCount = 0;
        for (int count : bucket) {
            if (count == 0)
                zeroCount++;
        }
        if (zeroCount > start + 1)
            return;

        // 剪枝2: 如果某位小朋友的饼干数量比当前的答案还多, 显然继续回溯下去也无法成为最优答案, 直接返回
        for (int i = 0; i < k; i++) {
            if (bucket[i] > ans)
                return;
        }

        for (int i = 0; i < k; i++) {
            // 剪枝3: 第一个零食包不管给哪个小朋友, 所开启的回溯树都一样, 只要给一个小朋友就行了, 这样的回溯树一下子就少了很多
            if (start == n - 1 && i > 0)
                return;

            bucket[i] += cookies[start];
            backtrack(bucket, start - 1);
            bucket[i] -= cookies[start];
        }
        return;
    }

    // 参考：https://leetcode.cn/problems/fair-distribution-of-cookies/solutions/1/gong-shui-san-xie-jian-dan-zhuang-ya-dp-trg25/
    public int distributeCookies2(int[] cookies, int k) {

        int n = cookies.length;
        int mask = 1 << n;
        int INF = 0x3f3f3f3f;
        // g[s] = t 含义为选择 cookies 状态为 s 时得到的饼干总和为 t
        int[] g = new int[mask];

        for (int s = 0; s < mask; s++) {
            int t = 0;
            for (int i = 0; i < n; i++)
                t += ((s >> i) & 1) == 1 ? cookies[i] : 0;
            g[s] = t;
        }
        // 定义 f[i][s] 为考虑前 i 个人，对 cookies 的分配情况为 s 时的最小不公平程度
        int[][] f = new int[k + 10][mask];
        for (int i = 0; i <= k; i++)
            Arrays.fill(f[i], INF);

        f[0][0] = 0;
        // 一般性考虑 f[i][s] 该如何计算，通过枚举第 i 个小朋友所分配到的饼干情况 p（s 的子集）进行转移
        // 若第 i 个小朋友分配到的饼干情况为 p，则此前 i − 1 个小朋友分配到饼干情况为 s − p，前 i − 1 个小朋友的最小不公平程度为 f[i − 1][s − p]
        // 当前第 i 个小朋友的不公平程度为 g[p]，两者中最大值可用于更新 f[i][s]
        for (int i = 1; i <= k; i++) {
            for (int s = 0; s < mask; s++) {
                // 枚举 s 的子集
                for (int p = s; p != 0; p = (p - 1) & s)
                    f[i][s] = Math.min(f[i][s], Math.max(f[i - 1][s - p], g[p]));
            }
        }
        return f[k][mask - 1];
    }
}
