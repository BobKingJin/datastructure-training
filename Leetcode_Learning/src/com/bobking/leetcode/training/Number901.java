package com.bobking.leetcode.training;

import java.util.Stack;

/**
 * @author BobKing
 * @create 2021-07-24 15:50
 */
public class Number901 {

    // 参考：https://leetcode-cn.com/problems/online-stock-span/solution/gu-piao-jie-ge-kua-du-by-leetcode/
    private class StockSpanner {

        Stack<Integer> prices;
        Stack<Integer> weights;

        public StockSpanner() {
            prices = new Stack();
            weights = new Stack();
        }

        public int next(int price) {
            // 因为自身也包括在内，所以 w 初始即为 1
            int w = 1;
            // 单调栈 小 -> 大
            while (!prices.isEmpty() && prices.peek() <= price) {
                prices.pop();
                w += weights.pop();
            }

            prices.push(price);
            weights.push(w);
            return w;
        }
    }

}
