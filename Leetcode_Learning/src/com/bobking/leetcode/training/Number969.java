package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-09-03 8:20
 */
public class Number969 {

    // 参考：https://leetcode.cn/problems/pancake-sorting/solution/gong-shui-san-xie-mou-pao-pai-xu-yun-yon-c0mn/
    public List<Integer> pancakeSort(int[] arr) {

        // 由于每次都对「某段前缀」进行整体翻转，并且规定了翻转次数在一定范围内的方案均为合法翻转方案，同时 arr 又是 1 到 n 的排列
        // 可以很自然想到「冒泡排序」：每次确定未排序部分最右端的元素（最大值）
        // 具体的，假设下标 [k + 1, n - 1] 部分已有序，如果希望当前值 t 出现在某个位置 k 上，可以进行的操作为：
        // 如果当前值 t 已在 k 上，无须进行操作
        // 如果当前值不在 k 上，根据当前值是否在数组头部（下标为 0）进行分情况讨论：
        // 当前值在数组头部（下标为 0），直接将 [0, k] 部分进行翻转（将 k + 1 加入答案中），即可将当前值 t 放到位置 k 上
        // 当前值不在数组头部（下标为 0），而是在 idx 位置上，需要先将 [0, idx] 部分进行翻转（将 idx + 1 加入答案中），这样使得当前值 t 出现数组头部（下标为 0）
        // 然后再将 [0, k] 部分进行翻转（将 k + 1 加入答案中），即可将当前值 t 放到位置 k 上

        int n = arr.length;
        int[] idxs = new int[n + 10];
        for (int i = 0; i < n; i++)
            idxs[arr[i]] = i;

        List<Integer> ans = new ArrayList<Integer>();

        for (int i = n; i >= 1; i--) {
            int idx = idxs[i];
            if (idx == i - 1)
                continue;
            if (idx != 0) {
                ans.add(idx + 1);
                reverse(arr, 0, idx, idxs);
            }
            ans.add(i);
            reverse(arr, 0, i - 1, idxs);
        }

        return ans;
    }

    private void reverse(int[] arr, int i, int j, int[] idxs) {
        while (i < j) {
            idxs[arr[i]] = j;
            idxs[arr[j]] = i;
            int c = arr[i];
            arr[i++] = arr[j];
            arr[j--] = c;
        }
    }
}
