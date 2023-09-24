package com.bobking.leetcode.training;

import java.util.Random;

/**
 * @author BobKing
 * @create 2022-06-05 11:42
 */
public class Number478 {

    // 参考：https://leetcode.cn/problems/generate-random-point-in-a-circle/solution/by-ac_oier-btkm/
    private class Solution1 {

        private double r;
        private double x;
        private double y;
        Random random;

        public Solution1(double radius, double x_center, double y_center) {

            this.r = radius;
            this.x = x_center;
            this.y = y_center;
            this.random = new Random();
        }

        // 对给定圆内的点进行等概率随机采样，容易想到随机化两个信息：
        // 一个是距离圆心的距离 len（在范围 [0, r] 中进行随机）
        // 另外一个是夹角 ang（在范围 [0, 2π] 中随机，随便找个参考线即可，例如以往 x 轴正方向的射线为参考）
        public double[] randPoint() {

            double len = Math.sqrt(random.nextDouble()) * r * r;
            double ang = random.nextDouble() * 2 * Math.PI;
            double nx = x + len * Math.cos(ang);
            double ny = y + len * Math.sin(ang);
            return new double[]{nx, ny};
        }
    }

    // 参考：https://leetcode.cn/problems/generate-random-point-in-a-circle/solution/zai-yuan-nei-sui-ji-sheng-cheng-dian-by-qp342/
    private class Solution2 {

        private double r;
        private double xc;
        private double yc;
        Random random;

        public Solution2(double radius, double x_center, double y_center) {

            this.r = radius;
            this.xc = x_center;
            this.yc = y_center;
            this.random = new Random();
        }

        public double[] randPoint() {

            while (true) {
                double x = random.nextDouble() * (2 * r) - r;
                double y = random.nextDouble() * (2 * r) - r;
                if (x * x + y * y <= r * r)
                    return new double[]{xc + x, yc + y};
            }
        }
    }
}
