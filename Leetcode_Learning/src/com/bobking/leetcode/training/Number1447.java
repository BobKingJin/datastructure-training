package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2023-04-25 8:09
 */
public class Number1447 {

    // 参考：https://leetcode.cn/problems/simplified-fractions/solution/gong-shui-san-xie-jian-dan-shu-lun-yun-y-wma5/
    public List<String> simplifiedFractions(int n) {

        List<String> ans = new ArrayList<String>();

        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (gcd(i, j) == 1)
                    ans.add(i + "/" + j);
            }
        }

        return ans;
    }

    // 欧几里得算法
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

}
