package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-03-26 21:07
 */
public class Number12 {

    // 参考：https://leetcode-cn.com/problems/integer-to-roman/solution/tan-xin-suan-fa-by-liweiwei1419/
    public String intToRoman(int num) {

        // 转换的时候默认使用加法，如果一个字符超过 3 次重复使用，就改成减法
        // 这样就可以用两个字符表示一个罗马数字（数量更少），所以 4 应该看成 5 - 1，即 IV

        // 4、9、40、90、400、900 对应的罗马数字字符只出现一次
        // 其余字符可以连续出现的次数不超过 3 次
        // 把阿拉伯数字与罗马数字可能出现的所有情况和对应关系，放在两个数组中，并且按照阿拉伯数字的大小降序排列
        // 从大先尝试，所以把 1000 放在数组位置 0 上面
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (index < 13) {

            // 特别注意：这里是等号
            while (num >= nums[index]) {
                sb.append(romans[index]);
                num -= nums[index];
            }

            index++;
        }

        return sb.toString();
    }
}
