package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2022-09-17 11:26
 */
public class Number1711 {

    int mod = (int)1e9+7;

    // 参考：https://leetcode.cn/problems/count-good-meals/solution/gong-shui-san-xie-xiang-jie-san-chong-gu-nn4f/
    public int countPairs1(int[] deliciousness) {

        int n = deliciousness.length;
        long ans = 0;

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < n; i++) {
            int x = deliciousness[i];
            for (int other : map.keySet()) {
                if (check(other + x))
                    ans += map.get(other);
            }

            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        return (int)(ans % mod);
    }

    // 需要一个 check 方法来判断某个数是否为 2 的幂
    // 朴素的做法是对 x 应用试除法，当然因为精度问题，我们需要使用乘法实现试除
    // 另一个比较优秀的做法是利用位运算找到符合「大于等于 x」的最近的 2 的幂，然后判断是否与 x 相同

    private boolean check(long x) {
        // 方法一
        // long cur = 1;
        // while (cur < x) {
        //     cur = cur * 2;
        // }
        // return cur == x;

        // 方法二
        return getVal(x) == x;
    }

    private long getVal(long x) {
        long n = x - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n < 0 ? 1 : n + 1;
    }

    int max = 1 << 22;

    // 参考：https://leetcode.cn/problems/count-good-meals/solution/gong-shui-san-xie-xiang-jie-san-chong-gu-nn4f/
    public int countPairs2(int[] deliciousness) {

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int d : deliciousness)
            map.put(d, map.getOrDefault(d, 0) + 1);

        long ans = 0;

        for (int x : map.keySet()) {
            for (int i = 1; i < max; i <<= 1) {
                int t = i - x;
                // 如果哈希表中存在 t = i - x，并且 t = x，这时候方案数应该是 (cnts[x] - 1) * cnts[x]
                // 其余一般情况则是 cnts[t] * cnts[x]
                if (map.containsKey(t)) {
                    if (t == x) {
                        ans += (map.get(x) - 1) * 1L * map.get(x);
                    } else {
                        ans += map.get(x) * 1L * map.get(t);
                    }
                }
            }
        }
        // 同时，这样的计数方式，对于二元组 (x, t) 会分别计数两次（遍历 x 和 遍历 t），因此最后要利用容斥原理，对重复计数的进行减半操作
        ans >>= 1;
        return (int)(ans % mod);
    }

}
