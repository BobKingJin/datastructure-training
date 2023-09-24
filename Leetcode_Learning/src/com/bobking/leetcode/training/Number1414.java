package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-09-24 12:07
 */
public class Number1414 {

    List<Integer> list = new ArrayList<Integer>();

    private void getFibonacciList() {

        list.add(1);
        int a = 1;
        int b = 1;

        while (b <= (int)1e9) {
            int c = a + b;
            a = b;
            b = c;
            list.add(c);
        }
    }

    // 参考：https://leetcode.cn/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k/solution/gong-shui-san-xie-noxiang-xin-ke-xue-xi-rgty8/
    public int findMinFibonacciNumbers1(int k) {

        int ans = 0;

        getFibonacciList();

        while (k != 0) {

            int l = 0;
            int r = list.size() - 1;

            // 从 list 中找到不超过 k 的最大数」这一操作，可使用「二分」

            while (l < r) {

                int mid = l + r + 1 >> 1;

                if (list.get(mid) <= k) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }

            k -= list.get(r);
            ans++;
        }

        return ans;
    }

    // 参考：https://leetcode.cn/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k/solution/gong-shui-san-xie-noxiang-xin-ke-xue-xi-rgty8/
    public int findMinFibonacciNumbers2(int k) {

        int a = 1;
        int b = 1;

        while (b <= k) {
            int c = a + b;
            a = b;
            b = c;
        }

        int ans = 0;

        while (k != 0) {
            if (k >= b) {
                k -= b;
                ans++;
            }
            int c = b - a;
            b = a;
            a = c;
        }

        return ans;
    }

}
