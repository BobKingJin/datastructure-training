package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

public class Number6 {

    // 参考：https://leetcode-cn.com/problems/zigzag-conversion/solution/zzi-xing-bian-huan-by-jyd/
    public String convert(String s, int numRows) {

        if(numRows < 2)
            return s;

        List<StringBuilder> rows = new ArrayList<StringBuilder>();

        for(int i = 0; i < numRows; i++)
            rows.add(new StringBuilder());

        int i = 0;
        int flag = -1;
        for(char c : s.toCharArray()) {
            rows.get(i).append(c);
            if(i == 0 || i == numRows - 1)
                flag = -flag;
            i += flag;
        }

        StringBuilder res = new StringBuilder();
        for(StringBuilder row : rows)
            res.append(row);

        return res.toString();
    }
}
