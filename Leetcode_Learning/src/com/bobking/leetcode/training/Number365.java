package com.bobking.leetcode.training;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author BobKing
 * @create 2022-09-09 16:49
 */
public class Number365 {

    // 参考：https://leetcode.cn/problems/water-and-jug-problem/solution/shui-hu-wen-ti-by-leetcode-solution/
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {

        Deque<int[]> stack = new LinkedList<int[]>();
        stack.push(new int[]{0, 0});
        Set<Long> seen = new HashSet<Long>();

        while (!stack.isEmpty()) {

            if (seen.contains(hash(stack.peek()))) {
                stack.pop();
                continue;
            }

            seen.add(hash(stack.peek()));

            int[] state = stack.pop();
            int remain_x = state[0];
            int remain_y = state[1];
            if (remain_x == targetCapacity || remain_y == targetCapacity || remain_x + remain_y == targetCapacity)
                return true;

            // 把 X 壶灌满
            stack.push(new int[]{jug1Capacity, remain_y});
            // 把 Y 壶灌满
            stack.push(new int[]{remain_x, jug2Capacity});
            // 把 X 壶倒空
            stack.push(new int[]{0, remain_y});
            // 把 Y 壶倒空
            stack.push(new int[]{remain_x, 0});
            // 把 X 壶的水灌进 Y 壶，直至灌满或倒空
            stack.push(new int[]{remain_x - Math.min(remain_x, jug2Capacity - remain_y), remain_y + Math.min(remain_x, jug2Capacity - remain_y)});
            // 把 Y 壶的水灌进 X 壶，直至灌满或倒空
            stack.push(new int[]{remain_x + Math.min(remain_y, jug1Capacity - remain_x), remain_y - Math.min(remain_y, jug1Capacity - remain_x)});
        }

        return false;
    }

    private long hash(int[] state) {
        return (long) state[0] * 1000001 + state[1];
    }

}
