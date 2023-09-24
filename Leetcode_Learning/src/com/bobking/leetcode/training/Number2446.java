package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-17 7:02
 */
public class Number2446 {

    // 参考：https://leetcode.cn/problems/determine-if-two-events-have-conflict/solution/yi-xing-dai-ma-bi-jiao-liang-dui-zi-fu-c-34ae/
    public boolean haveConflict(String[] event1, String[] event2) {
        return event1[0].compareTo(event2[1]) <= 0 && event1[1].compareTo(event2[0]) >= 0;
    }
}
