package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author BobKing
 * @create 2022-06-26 11:25
 */
public class Number710 {

    // 参考：https://leetcode.cn/problems/random-pick-with-blacklist/solution/by-ac_oier-2rww/
    private class Solution {

        List<int[]> list = new ArrayList<int[]>();
        int[] sum = new int[100010];
        int sz;
        Random random = new Random();

        // 先对 blacklist 进行排序，然后从前往后处理所有的 blacklist[i]，将相邻 blacklist[i] 之间的能被选择的「线段」以二元组 (a, b) 的形式进行记录
        // 即一般情况下的 a = blacklist[i - 1] + 1，b = blacklist[i] - 1，存入数组 list 中（注意特殊处理一下两端的线段）
        // 当处理完所有的 blacklist[i] 后，得到了所有可被选择线段，同时对于每个线段可直接算得其所包含的整数点数
        // 可以对 list 数组做一遍「线段所包含点数」的「前缀和」操作，得到 sum 数组，同时得到所有线段所包含的总点数（前缀和数组的最后一位）

        // 对于 pick 操作而言，先在 [1, tot] 范围进行随机（其中 tot 代表总点数），假设取得的随机值为 val
        // 然后在前缀和数组中进行二分，找到第一个满足「值大于等于 val」的位置（含义为找到这个点所在的线段），然后再利用该线段的左右端点的值，取出对应的点

        public Solution(int n, int[] blacklist) {

            Arrays.sort(blacklist);
            int m = blacklist.length;

            if (m == 0) {
                list.add(new int[]{0, n - 1});
            } else {
                if (blacklist[0] != 0)
                    list.add(new int[]{0, blacklist[0] - 1});
                for (int i = 1; i < m; i++) {
                    if (blacklist[i - 1] == blacklist[i] - 1)
                        continue;
                    list.add(new int[]{blacklist[i - 1] + 1, blacklist[i] - 1});
                }
                if (blacklist[m - 1] != n - 1)
                    list.add(new int[]{blacklist[m - 1] + 1, n - 1});
            }
            sz = list.size();
            // 前缀和
            for (int i = 1; i <= sz; i++) {
                int[] info = list.get(i - 1);
                sum[i] = sum[i - 1] + info[1] - info[0] + 1;
            }
        }

        public int pick() {

            int val = random.nextInt(sum[sz]) + 1;
            int l = 1;
            int r = sz;

            while (l < r) {
                int mid = l + r >> 1;
                if (sum[mid] >= val) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            int[] info = list.get(r - 1);
            int a = info[0];
            int b = info[1];
            int end = sum[r];
            return b - (end - val);
        }
    }
}
