package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-08-05 12:02
 */
public class Number2069 {

    private class Robot {

        String[] s = {"East", "North", "West", "South"};
        int width;
        int height;
        int[] pos = {0, 0};
        int i = 0;
        // 外圈周长
        int all;

        public Robot(int width, int height) {
            this.width = width;
            this.height = height;
            all = 2 * (width + height) - 4;
        }

        public void step(int num) {

            if (num >= all && num % all == 0 && pos[0] == 0 && pos[1] == 0)
                // 没这句第 140 个用例过不了，是个坑
                i = 3;

            num = num % all;

            while (num > 0) {
                if (i == 0 && pos[0] == width - 1 ||
                        i == 1 && pos[1] == height - 1 ||
                        i == 2 && pos[0] == 0 ||
                        i == 3 && pos[1] == 0)
                    // 换方向
                    i = (i + 1) % 4;

                int dif;
                if (i == 0) { // 向东
                    dif = Math.min(num, width - 1 - pos[0]);
                    pos[0] = pos[0] + dif;
                } else if (i == 1) { // 向北
                    dif = Math.min(num, height - 1 - pos[1]);
                    pos[1] = pos[1] + dif;
                } else if (i == 2) { // 向西
                    dif = Math.min(pos[0], num);
                    pos[0] = pos[0] - dif;
                } else { // 向南
                    dif = Math.min(pos[1], num);
                    pos[1] = pos[1] - dif;
                }
                num -= dif;
            }
        }

        public int[] getPos() {
            return pos;
        }

        public String getDir() {
            return s[i];
        }
    }
}
