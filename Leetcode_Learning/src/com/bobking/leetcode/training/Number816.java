package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-11-07 9:23
 */
public class Number816 {

    public List<String> ambiguousCoordinates(String s) {

        int n = s.length() - 2;
        List<String> res = new ArrayList<String>();
        s = s.substring(1, s.length() - 1);

        // 首先在字符串 s 中枚举添加逗号和空格的位置，将 s 的数字部分为两个部分
        for (int l = 1; l < n; ++l) {
            // 分别对于前后部分数字枚举添加小数点的位置（也可以不添加）
            List<String> lt = getPos(s.substring(0, l));
            if (lt.isEmpty())
                continue;
            // 分别对于前后部分数字枚举添加小数点的位置（也可以不添加）
            List<String> rt = getPos(s.substring(l));
            if (rt.isEmpty())
                continue;

            for (String i : lt) {
                for (String j : rt)
                    res.add("(" + i + ", " + j + ")");
            }
        }
        return res;
    }

    private List<String> getPos(String s) {

        List<String> pos = new ArrayList<String>();

        if (s.charAt(0) != '0' || "0".equals(s))
            pos.add(s);

        for (int p = 1; p < s.length(); ++p) {
            if ((p != 1 && s.charAt(0) == '0') || s.charAt(s.length() - 1) == '0')
                continue;

            pos.add(s.substring(0, p) + "." + s.substring(p));
        }

        return pos;
    }

}
