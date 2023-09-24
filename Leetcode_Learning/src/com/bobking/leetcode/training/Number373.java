package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Number373 {

    private boolean flag = true;

    // 参考：https://leetcode.cn/problems/find-k-pairs-with-smallest-sums/solution/gong-shui-san-xie-duo-lu-gui-bing-yun-yo-pgw5/
    public List<List<Integer>> kSmallestPairs1(int[] nums1, int[] nums2, int k) {

        // 此题有个很关键的条件，那就是 nums1，nums2 已经有序
        // 因此每个 nums1[i] 参与构成的点序列也为升序排序，这样才能使用「多路归并」来进行求解

        // 始终确保 nums1 为两数组中长度较少的那个
        // 通过标识位来记录是否发生过交换，确保答案的点顺序的正确性
        int n = nums1.length;
        int m = nums2.length;
        if (n > m && !(flag = false))
            return kSmallestPairs1(nums2, nums1, k);

        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 小根堆
        PriorityQueue<int[]> q = new PriorityQueue<int[]>((a, b) -> (nums1[a[0]] + nums2[a[1]]) - (nums1[b[0]] + nums2[b[1]]));
        for (int i = 0; i < Math.min(n, k); i++)
            q.add(new int[]{i, 0});

        // 每次从优先队列（堆）中取出堆顶元素（含义为当前未被加入到答案的所有点对中的最小值），加入答案
        // 并将该点对所在序列的下一位（如果有）加入优先队列中
        while (ans.size() < k && !q.isEmpty()) {
            int[] poll = q.poll();
            int a = poll[0];
            int b = poll[1];
            ans.add(new ArrayList(){{
                add(flag ? nums1[a] : nums2[b]);
                add(flag ? nums2[b] : nums1[a]);
            }});

            if (b + 1 < m)
                q.add(new int[]{a, b + 1});
        }

        return ans;
    }

    int[] nums1;
    int[] nums2;
    int n;
    int m;

    // 参考：https://leetcode.cn/problems/find-k-pairs-with-smallest-sums/solution/gong-shui-san-xie-duo-lu-gui-bing-yun-yo-pgw5/
    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {

        this.nums1 = nums1;
        this.nums2 = nums2;
        n = nums1.length;
        m = nums2.length;

        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        int l = nums1[0] + nums2[0];
        int r = nums1[n - 1] + nums2[m - 1];
        while (l < r) {
            int mid = (int)(0L + l + r >> 1);
            if (check(mid, k)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        int x = r;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (nums1[i] + nums2[j] < x) {
                    List<Integer> temp = new ArrayList<Integer>();
                    temp.add(nums1[i]);
                    temp.add(nums2[j]);
                    ans.add(temp);
                } else {
                    break;
                }
            }
        }

        for (int i = 0; i < n && ans.size() < k; i++) {
            int a = nums1[i];
            int b = x - a;
            int c = -1;
            int d = -1;
            l = 0;
            r = m - 1;
            while (l < r) {
                int mid = (int)(0L + l + r >> 1);
                if (nums2[mid] >= b) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            if (nums2[r] != b)
                continue;
            c = r;
            l = 0;
            r = m - 1;
            while (l < r) {
                int mid = (int)(0L + l + r + 1) >> 1;
                if (nums2[mid] <= b) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            d = r;
            for (int p = c; p <= d && ans.size() < k; p++) {
                List<Integer> temp = new ArrayList<Integer>();
                temp.add(a);
                temp.add(b);
                ans.add(temp);
            }
        }

        return ans;
    }

    private boolean check(int x, int k) {

        int ans = 0;
        for (int i = 0; i < n && ans < k; i++) {
            for (int j = 0; j < m && ans < k; j++) {
                if (nums1[i] + nums2[j] <= x) {
                    ans++;
                } else {
                    break;
                }
            }
        }

        return ans >= k;
    }

}
