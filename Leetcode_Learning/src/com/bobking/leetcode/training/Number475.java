package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-09-02 22:28
 */
public class Number475 {

    // 参考：https://leetcode.cn/problems/heaters/solution/gong-shui-san-xie-er-fen-shuang-zhi-zhen-mys4/
    public int findRadius(int[] houses, int[] heaters) {

        Arrays.sort(houses);
        Arrays.sort(heaters);
        int l = 0;
        int r = (int) 1e9;

        while (l < r) {
            int mid = l + r >> 1;
            if (check(houses, heaters, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return r;
    }

    private boolean check(int[] houses, int[] heaters, int x) {

        // 使用 i 指向当前处理到的 houses[i]
        //     j 指向 可能 覆盖到 houses[i] 的最小下标 heaters[j]
        //     x 代表当前需要 check 的半径
        // 当且仅当 heaters[j] + x < houses[i] 时，houses[i] 必然不能被 heaters[j] 所覆盖，此时让 j 自增
        // 找到合适的 j 之后，再检查 heaters[j] - x <= houses[i] <= heaters[j] + x 是否满足，即可知道 houses[i] 的覆盖情况


        int n = houses.length;
        int m = heaters.length;

        for (int i = 0, j = 0; i < n; i++) {
            while (j < m && houses[i] > heaters[j] + x)
                j++;
            if (j < m && heaters[j] - x <= houses[i] && houses[i] <= heaters[j] + x)
                continue;
            return false;
        }

        return true;
    }

}
