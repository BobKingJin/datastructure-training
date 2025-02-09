package com.bobking.leetcode.training;

/**
 * @Date: 2025/2/9 11:27
 * @Author: BobKing
 * @Description:
 */
public class Number3019 {

    // 参考: https://leetcode.cn/problems/number-of-changing-keys/solutions/2622667/wei-yun-suan-jian-ji-xie-fa-pythonjavacg-h7rz/?envType=daily-question&envId=2025-02-07
    public int countKeyChanges(String s) {
        int ans = 0;
        for (int i = 1; i < s.length(); i++) {
            if ((s.charAt(i - 1) & 31) != (s.charAt(i) & 31)) {
                ans++;
            }
        }
        return ans;
    }

}
