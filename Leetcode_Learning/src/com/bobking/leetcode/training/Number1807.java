package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BobKing
 * @create 2022-12-11 10:29
 */
public class Number1807 {

    public String evaluate(String s, List<List<String>> knowledge) {

        Map<String, String> map = new HashMap<String, String>();
        for (List<String> o : knowledge)
            map.put(o.get(0), o.get(1));

        StringBuilder res = new StringBuilder();
        StringBuilder key = new StringBuilder();
        int keyCount = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                // 关键字开始
                keyCount++;
            } else if (c == ')') {
                // 关键字结束
                keyCount--;
                res.append(map.getOrDefault(key.toString(), "?"));
                // 取出关键字对应的value值，没有值就用默认值 ？ 代替
                // 重置关键字
                key = new StringBuilder();
            } else if (keyCount > 0) {
                key.append(c);
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }
}
