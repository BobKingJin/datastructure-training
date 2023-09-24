package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-05 11:05
 */
public class Number1723 {

    int[] totalJobs;
    int n;
    int m;
    int ans = 0x3f3f3f3f;

    // 参考：https://leetcode.cn/problems/find-minimum-time-to-finish-all-jobs/solution/gong-shui-san-xie-yi-ti-shuang-jie-jian-4epdd/
    public int minimumTimeRequired1(int[] jobs, int k) {

        this.totalJobs = jobs;
        this.n = jobs.length;
        this.m = k;
        int[] sum = new int[k];
        dfs1(0, sum, 0);
        return ans;
    }

    // u：当前处理到哪个 job
    // sum：工人的分配情况  例如：sum[0] = x 代表 0 号工人工作量为 x
    // max：当前的「最大工作时间」
    private void dfs1(int u, int[] sum, int max) {

        if (max >= ans)
            return;

        if (u == n) {
            ans = max;
            return;
        }

        for (int i = 0; i < m; i++) {
            sum[i] += totalJobs[u];
            dfs1(u + 1, sum, Math.max(sum[i], max));
            // 回溯
            sum[i] -= totalJobs[u];
        }
    }

    // 参考：https://leetcode.cn/problems/find-minimum-time-to-finish-all-jobs/solution/gong-shui-san-xie-yi-ti-shuang-jie-jian-4epdd/
    public int minimumTimeRequired2(int[] jobs, int k) {

        this.totalJobs = jobs;
        this.n = jobs.length;
        this.m = k;
        int[] sum = new int[k];
        dfs2(0, 0, sum, 0);
        return ans;
    }

    // used：当前分配给了多少个工人了
    private void dfs2(int u, int used, int[] sum, int max) {

        if (max >= ans)
            return;

        if (u == n) {
            ans = max;
            return;
        }

        // 优先分配给「空闲工人」
        if (used < m) {
            sum[used] = totalJobs[u];
            dfs2(u + 1, used + 1, sum, Math.max(sum[used], max));
            sum[used] = 0;
        }

        for (int i = 0; i < used; i++) {
            sum[i] += totalJobs[u];
            dfs2(u + 1, used, sum, Math.max(sum[i], max));
            sum[i] -= totalJobs[u];
        }
    }


}
