package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-05-03 16:17
 */
public class Number937 {

    private class Log {

        int type;
        int idx;
        String ori;
        String sign;
        String content;

        Log(String s, int idx) {

            this.idx = idx;
            int n = s.length();
            int i = 0;

            while (i < n && s.charAt(i) != ' ')
                i++;

            this.sign = s.substring(0, i);
            this.content = s.substring(i + 1);
            this.ori = s;
            // 字母日志：1  数字日志：0
            this.type = Character.isDigit(content.charAt(0)) ? 1 : 0;
        }
    }

    // 参考：https://leetcode-cn.com/problems/reorder-data-in-log-files/solution/by-ac_oier-ap28/
    public String[] reorderLogFiles(String[] logs) {

        int n = logs.length;
        List<Log> list = new ArrayList<Log>();

        for (int i = 0; i < n; i++)
            list.add(new Log(logs[i], i));

        Collections.sort(list, (a, b)->{

            if (a.type != b.type)
                return a.type - b.type;

            if (a.type == 1)
                return a.idx - b.idx;

            return !a.content.equals(b.content) ? a.content.compareTo(b.content) : a.sign.compareTo(b.sign);
        });

        String[] res = new String[n];
        for (int i = 0; i < n; i++)
            res[i] = list.get(i).ori;

        return res;
    }

}
