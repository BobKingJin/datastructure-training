package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Number761 {

    // 参考：思路：https://leetcode.cn/problems/special-binary-string/solution/zhuan-huan-wei-gua-hao-zi-fu-chuan-jiu-hen-rong-yi/
    // 参考：代码：https://leetcode.cn/problems/special-binary-string/solution/te-shu-de-er-jin-zhi-xu-lie-by-capital-w-81ht/
    public String makeLargestSpecial(String s) {

        if (s.length() == 0)
            return "";

        List<String> list = new ArrayList<String>();
        int count = 0;
        int last = 0;

        for (int i = 0, cur = 0; i < s.length(); i++, cur++) {
            if (s.charAt(i) == '1') {
                count++;
            } else {
                count--;
            }
            // 一组有效的括号匹配 去掉括号进行 内部排序
            if (count == 0) {
                String str = "1" + makeLargestSpecial(s.substring(last + 1, cur)) + "0";
                list.add(str);
                last = cur + 1;
            }
        }

        // 进行排序，根据冒泡排序，交换两个相邻的元素进行排序，总能让内部的括号由大到小排列
        list.sort(Comparator.reverseOrder());
        // 拼成完整的字符串
        StringBuilder sb = new StringBuilder();
        for (String str : list)
            sb.append(str);

        return sb.toString();
    }
}
