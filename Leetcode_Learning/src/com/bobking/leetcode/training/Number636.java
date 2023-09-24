package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-08-07 9:34
 */
public class Number636 {

    // 参考：https://leetcode.cn/problems/exclusive-time-of-functions/solution/han-shu-de-du-zhan-shi-jian-by-leetcode-d54e2/
    public int[] exclusiveTime(int n, List<String> logs) {

        // 可以用栈来模拟函数调用的过程，栈顶的元素为当前正在执行函数：
        // 当函数调用开始时，如果当前有函数正在运行，则当前正在运行函数应当停止，此时计算其的执行时间，然后将调用函数入栈
        // 当函数调用结束时，将栈顶元素弹出，并计算相应的执行时间，如果此时栈顶有被暂停的函数，则开始运行该函数
        // 由于每一个函数都有一个对应的 start 和 end 日志，且当遇到一个 end 日志时，栈顶元素一定为其对应的 start 日志
        // 那么对于每一个函数记录它的函数标识符和上次开始运行的时间戳，此时只需要在每次函数暂停运行的时候来计算执行时间和开始运行的时候更新时间戳即可

        // idx：开始运行的时间
        Deque<int[]> stack = new ArrayDeque<int[]>();
        int[] res = new int[n];

        for (String log : logs) {
            int idx = Integer.parseInt(log.substring(0, log.indexOf(':')));
            String type = log.substring(log.indexOf(':') + 1, log.lastIndexOf(':'));
            int timestamp = Integer.parseInt(log.substring(log.lastIndexOf(':') + 1));
            if ("start".equals(type)) {
                if (!stack.isEmpty()) {
                    res[stack.peek()[0]] += timestamp - stack.peek()[1];
                    stack.peek()[1] = timestamp;
                }
                stack.push(new int[]{idx, timestamp});
            } else {
                int[] t = stack.pop();
                res[t[0]] += timestamp - t[1] + 1;
                if (!stack.isEmpty())
                    stack.peek()[1] = timestamp + 1;
            }
        }
        return res;
    }
}
