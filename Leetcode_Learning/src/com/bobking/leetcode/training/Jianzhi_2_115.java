package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author BobKing
 * @create 2022-07-23 15:06
 */
public class Jianzhi_2_115 {

    int N = 10010;
    int M = N;
    int idx;
    int[] he = new int[N];
    int[] e = new int[M];
    int[] ne = new int[M];
    int[] in = new int[N];

    private void add(int a, int b) {
        e[idx] = b;
        ne[idx] = he[a];
        he[a] = idx++;
        in[b]++;
    }

    // 参考：https://leetcode.cn/problems/ur2n8P/solution/by-ac_oier-oqxs/
    public boolean sequenceReconstruction(int[] nums, int[][] sequences) {

        int n = nums.length;
        Arrays.fill(he, -1);
        for (int[] s : sequences) {
            for (int i = 1; i < s.length; i++)
                add(s[i - 1], s[i]);
        }

        Deque<Integer> d = new ArrayDeque<Integer>();
        for (int i = 1; i <= n; i++){
            if (in[i] == 0)
                d.addLast(i);
        }

        int loc = 0;
        while (!d.isEmpty()) {
            if (d.size() != 1)
                return false;
            int t = d.pollFirst();
            if (nums[loc++] != t)
                return false;
            for (int i = he[t]; i != -1; i = ne[i]) {
                int j = e[i];
                if (--in[j] == 0)
                    d.addLast(j);
            }
        }
        return true;
    }

}
