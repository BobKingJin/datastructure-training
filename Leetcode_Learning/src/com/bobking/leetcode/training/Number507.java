package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-10-30 15:18
 */
public class Number507 {

    // 参考：https://leetcode.cn/problems/perfect-number/solution/gong-shui-san-xie-jian-dan-mo-ni-tong-ji-e6jk/
    public boolean checkPerfectNumber(int num) {

        if (num == 1)
            return false;

        int ans = 1;

        for (int i = 2; i <= num / i; i++) {
            if (num % i == 0) {
                ans += i;
                // 这个位置注意：因为 for循环 中的遍历 i 只到 (num / i)，所以这个位置还需要判断 ans += num / i
                // 例如 num = 28，那么当 i = 2 时，ans 需要 ans += 14
                if (i * i != num)
                    ans += num / i;
            }
        }

        return ans == num;
    }
}
