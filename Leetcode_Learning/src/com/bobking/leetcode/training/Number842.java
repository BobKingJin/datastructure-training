package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2020-12-08 20:53
 */
public class Number842 {

    // 参考：https://leetcode-cn.com/problems/split-array-into-fibonacci-sequence/solution/javahui-su-suan-fa-tu-wen-xiang-jie-ji-b-vg5z/
    public List<Integer> splitIntoFibonacci(String S) {

        List<Integer> result = new ArrayList<Integer>();
        backTrace(S.toCharArray(), result, 0);
        return result;
    }

    private boolean backTrace(char[] ch, List<Integer> list, int index) {

        if (index == ch.length && list.size() >= 3)
            return true;

        for (int i = index; i < ch.length; i++) {
            // 两位以上的数字不能以 0 开头
            // 即当起始为 0(ch[index] = 0)，那么后面的以 index 起始的两位及以上的数是直接跳出循环的
            // 注意单独是 0 是可以的，例如："1101111"，输出: [110, 1, 111]
            if (ch[index] == '0' && i > index)
                break;

            // cur 为 index - i 之间的数
            long curNum = subDigital(ch, index, i + 1);
            if (curNum > Integer.MAX_VALUE)
                break;

            if (list.size() >= 2 && curNum > list.get(list.size() - 1) + list.get(list.size() - 2))
                break;

            if (list.size() < 2 || curNum == list.get(list.size() - 1) + list.get(list.size() - 2)) {
                list.add((int) curNum);
                if (backTrace(ch, list, i + 1))
                    return true;
                // 回溯
                list.remove(list.size() - 1);
            }
        }

        return false;
    }

    private long subDigital(char[] ch, int start, int end) {

        long sum = 0;

        for (int i = start; i < end; i++)
            sum = sum * 10 + ch[i] - '0';

        return sum;
    }
}
