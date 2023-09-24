package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

public class Number166 {

    // 参考：https://leetcode-cn.com/problems/fraction-to-recurring-decimal/solution/gong-shui-san-xie-mo-ni-shu-shi-ji-suan-kq8c4/
    public String fractionToDecimal(int numerator, int denominator) {

        // 转 long 计算，防止溢出
        long a = numerator;
        long b = denominator;

        // 如果本身能够整除，直接返回计算结果
        if (a % b == 0)
            return String.valueOf(a / b);

        StringBuilder sb = new StringBuilder();
        // 如果其一为负数，先追加负号
        if (a * b < 0)
            sb.append('-');

        a = Math.abs(a);
        b = Math.abs(b);

        // 计算小数点前的部分，并将余数赋值给 a
        sb.append(String.valueOf(a / b) + ".");
        a %= b;
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        while (a != 0) {
            // 使用「哈希表」记录某个余数最早在什么位置出现过，一旦出现相同余数，即出现死循环
            // 则将「出现位置」到「当前结尾」之间的字符串抠出来，即是「循环小数」部分
            // 记录当前余数所在答案的位置，并继续模拟除法运算
            map.put(a, sb.length());
            a *= 10;
            sb.append(a / b);
            a %= b;
            // 如果当前余数之前出现过，则将 [出现位置 到 当前位置] 的部分抠出来（循环小数部分）
            if (map.containsKey(a)) {
                int u = map.get(a);
                return String.format("%s(%s)", sb.substring(0, u), sb.substring(u));
            }
        }

        return sb.toString();
    }
}
