package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BobKing
 * @create 2023-04-16 13:53
 */
public class Number1157 {

    // 参考：https://leetcode.cn/problems/online-majority-element-in-subarray/solution/san-chong-fang-fa-bao-li-fen-kuai-xian-duan-shu-by/
    class MajorityChecker1 {

        int n;
        int[] a = new int[20005];

        public MajorityChecker1(int[] arr) {
            n = arr.length;
            for (int i = 0; i < n; i++)
                a[i] = arr[i];
        }

        public int query(int left, int right, int threshold) {

            int i;
            int j = 0;
            int k = 0;

            for (i = left; i <= right; i++) {
                if (a[i] == j) {
                    k++;
                } else if (k > 0) {
                    k--;
                } else {
                    j = a[i];
                    k = 1;
                }
            }

            for (i = left, k = 0; i <= right; i++) {
                if (a[i] == j)
                    k++;
            }

            if (k < threshold)
                j = -1;
            return j;
        }
    }

    // 参考：https://leetcode.cn/problems/online-majority-element-in-subarray/solution/by-424479543-8wxq/
    class MajorityChecker2 {

        // 可以按位获取 arr 的前缀和，这样可以在常数时间获取 [left, right] 范围内某一位的 0 或 1 的个数
        // 假设当前范围 [left, right] 内满足要求的元素是 x，且 x 的第 i 位为 1，那么第 i 位 [left, right] 范围内 1 的个数肯定就大于等于 threshold
        // 因此可以遍历 0 ~ 15 每一位 [left, right] 范围内 1 的个数 a 和 0 的个数 b，若 a >= threshold，那么 x 的当前位肯定是 1
        // 若 b >= threshold，那么 x 的当前位肯定是 0，否则不存在 x，返回 -1
        // 注意在获取到 x 后还要进一步判断 [left, right] 范围内元素 x 的个数是否大于等于 threshold
        int[][] bitArr;
        int n;
        int m = 16;
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();

        public MajorityChecker2(int[] arr) {
            n = arr.length;
            bitArr = new int[n + 1][m];
            for (int i = 0; i < n; i++) {
                map.computeIfAbsent(arr[i], a -> new ArrayList<Integer>()).add(i);
                for (int j = 0; j < m; j++)
                    bitArr[i + 1][j] = bitArr[i][j] + (arr[i] >> j & 1);
            }
        }

        public int query(int left, int right, int threshold) {
            int cand = 0;
            for (int j = 0; j < m; j++) {
                // 1 的数量
                int a = bitArr[right + 1][j] - bitArr[left][j];
                // 0 的数量
                int b = right - left + 1 - a;
                if (a >= threshold) {
                    cand |= 1 << j;
                } else if (b < threshold) {
                    return -1;
                }
            }
            if (!map.containsKey(cand))
                return -1;
            List<Integer> list = map.get(cand);
            int cc = binarySearch1(list, right) - binarySearch2(list, left);
            if (cc >= threshold) {
                return cand;
            } else {
                return -1;
            }
        }

        // 找到小于等于 t 的最大下标
        private int binarySearch1(List<Integer> nums, int t) {
            int left = 0;
            int right = nums.size();
            while (left < right) {
                int mid = left + right >> 1;
                if (nums.get(mid) <= t) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return left - 1;
        }

        // 找到小于 t 的最大下标
        private int binarySearch2(List<Integer> nums, int t) {
            int left = 0;
            int right = nums.size();
            while (left < right) {
                int mid = left + right >> 1;
                if (nums.get(mid) < t) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return left - 1;
        }
    }
}
