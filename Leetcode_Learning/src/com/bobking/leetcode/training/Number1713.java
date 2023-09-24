package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2022-06-11 12:34
 */
public class Number1713 {

    // 参考：https://leetcode.cn/problems/minimum-operations-to-make-a-subsequence/solution/gong-shui-san-xie-noxiang-xin-ke-xue-xi-oj7yu/
    public int minOperations(int[] target, int[] arr) {

        // target 和 arr 这两个数组的公共子序列越长，需要添加的元素个数也就越少
        // 因此最少添加的元素个数为 n 减去两数组的最长公共子序列的长度

        int n = target.length;
        int m = arr.length;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++)
            map.put(target[i], i);

        // target = [5, 1, 3],  arr = [9, 4, 2, 3, 4]
        // 因为 1.只涉及给 arr 增加 元素 2.最终答案与 arr 的长度也无关
        // 据此可以把 arr 中出现的，且在 target 中不存在的元素去掉，其并不会有什么影响
        // target = [6, 4, 8, 1, 3, 2],  arr = [4, 7, 6, 2, 3, 8, 6, 1]
        // target = [6, 4, 8, 1, 3, 2], arr' = [4, 6, 2, 3, 8, 6, 1]
        // 这时候注意到 target 中元素无重复，这个性质就像是索引一样，当然就可以把当成索引，得到一个新性质: 有序
        // 为了方便后续的比较使用，可以让其每个数字映射其对应的数组下标:
        // idx = [0, 1, 2, 3, 4, 5]
        // target = [6, 4, 8, 1, 3, 2]
        // (target -> idx) = [(6 -> 0), (4 -> 1), (8 -> 2), (1 -> 3), (3 -> 4), (2 -> 5)]
        // 这样, arr' = [4, 6, 2, 3, 8, 6, 1] -> [1, 0, 5, 4, 2, 0, 3]
        // 经过上述变换, 相当于把原始的 target 与 arr 分别变为
        // target' = [0, 1, 2, 3, 4, 5]
        // arr' = [1, 0, 5, 4, 2, 0, 3]
        // 其中 target' 是递增的顺序集合, 而 arr' 是一种乱序集合

        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < m; i++) {
            int x = arr[i];
            if (map.containsKey(x))
                list.add(map.get(x));
        }

        int len = list.size();
        int[] f = new int[len];
        // g[length] = x 代表上升子序列长度为 length 的上升子序列的「最小结尾元素」为 x
        int[] g = new int[len + 1];
        Arrays.fill(g, Integer.MAX_VALUE);
        int max = 0;
        // 关于利用二分查找求最长递增子序列，参考 程序猿代码指南P212 和 leetcode300
        for (int i = 0; i < len; i++) {
            // 如果 g数组 具有「单调递增」特性的话，可以通过「二分」找到符合 g[idx] < nums[i] 分割点 idx(下标最大)
            int l = 0;
            int r = len;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (g[mid] < list.get(i)) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }

            int clen = r + 1;
            f[i] = clen;
            g[clen] = Math.min(g[clen], list.get(i));
            max = Math.max(max, clen);
        }

        return n - max;
    }
}
