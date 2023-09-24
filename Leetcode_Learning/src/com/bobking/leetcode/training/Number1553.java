package com.bobking.leetcode.training;

import java.util.HashMap;

/**
 * @author BobKing
 * @create 2022-10-15 9:55
 */
public class Number1553 {

    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

    // 参考：https://leetcode.cn/problems/minimum-number-of-days-to-eat-n-oranges/solution/java-tan-xin-you-wo-xie-zhe-dao-ti-de-si-lu-by-f1o/
    public int minDays(int n) {

        if (n <= 1)
            return n;

        if (map.containsKey(n))
            return map.get(n);

        int min = Integer.MAX_VALUE;
        // 吃到 条件2 成立的情况下需要的天数
        // n % 2 代表吃到能给 2 整除的天数
        // minDays(n / 2)就是剩下的橘子需要的天数
        // 加起来就是吃到 2 成立的所需天数
        min = Math.min(min, minDays(n / 2) + (n % 2));
        // 吃到 条件3 成立情况下需要的天数
        // minDays(n / 3) + (n % 3)也是如此
        min = Math.min(min, minDays(n / 3) + (n % 3));

        // +1 是因为需要一天时间来吃掉 1/2 或者 2/3
        // 或者可以把上面两行代码写成
        // min = Math.min(min, minDays(n / 2) + 1 + (n % 2));
        // min = Math.min(min, minDays(n / 3) + 1 + (n % 3));
        // 那么此处就不需要 +1 了

        min += 1;
        // 把 n 个橘子吃的天数最优解保存起来
        map.put(n, min);

        return min;
    }
}
