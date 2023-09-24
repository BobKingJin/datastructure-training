package com.bobking.leetcode.training;

public class Interview08_05 {

    // 参考：https://leetcode-cn.com/problems/recursive-mulitply-lcci/solution/java-jiu-shi-bu-yong-di-gui-by-npe_tle/
    public int multiply(int A, int B) {

        int min = Math.min(A, B);
        int max = Math.max(A, B);
        int res = 0;

        // 例如：3 * 4 = (1 + 2) * 4  3 的二进制为 011
        // 优化：之所以选择较小的数进行位移，是因为这样更快
        for (int i = 0; min != 0; i++) {
            if ((min & 1) == 1)
                res += max << i;
            // 注意这里每次要更新 min，每次右移 1 位
            min >>= 1;
        }

        return res;
    }


}
