package com.bobking.leetcode.training;

public class Number1460 {

    // 参考：https://leetcode.cn/problems/make-two-arrays-equal-by-reversing-sub-arrays/solution/by-ac_oier-pv38/
    public boolean canBeEqual(int[] target, int[] arr) {


        // 当两数组词频相同，且翻转次数不受限制时，至少能通过「逐个调整」将一数组变为另一数组

        int n = arr.length;
        int tot = 0;
        int[] cnt = new int[1010];

        for (int i = 0; i < n; i++) {
            if (++cnt[target[i]] == 1)
                tot++;
            if (--cnt[arr[i]] == 0)
                tot--;
        }

        return tot == 0;
    }
}
