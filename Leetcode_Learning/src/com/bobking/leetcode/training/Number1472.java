package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-22 9:04
 */
public class Number1472 {

    class BrowserHistory {

        int pos;
        int top;
        String[] history;

        public BrowserHistory(String homepage) {
            this.pos = -1;
            this.top = 0;
            this.history = new String[5001];
            visit(homepage);
        }

        public void visit(String url) {
            pos++;
            top = pos;
            history[top++] = url;
        }

        public String back(int steps) {

            if (steps > pos)
                steps = pos;

            pos -= steps;
            return history[pos];
        }

        public String forward(int steps) {
            steps = Math.min(steps, top - pos - 1);
            pos += steps;
            return history[pos];
        }
    }
}
