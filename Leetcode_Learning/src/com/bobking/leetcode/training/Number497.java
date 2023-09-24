package com.bobking.leetcode.training;

import java.util.Random;

public class Number497 {

    // 参考：https://leetcode.cn/problems/random-point-in-non-overlapping-rectangles/solution/by-ac_oier-mhi6/
    private class Solution {

        int[][] rs;
        int[] sum;
        int n;
        Random random = new Random();

        // 一个朴素的想法是「先随机使用哪个矩形，再随机该矩形内的点」，其中后者是极其容易的
        // 根据矩形特质，只需在该矩形的 XY 坐标范围内随机即可确保等概率，而前者（随机使用哪个矩形）为了确保是等概率
        // 不能简单随机坐标，而需要结合面积来做
        //
        // 具体的，可以预处理前缀和数组 sum（前缀和数组下标默认从 1 开始），其中 sum[i] 代表前 i 个矩形
        // 的面积之和（即下标范围 [0, i - 1] 的面积总和），最终 sum[n] 为所有矩形的总面积
        // 可以在 [1, sum[n]] 范围内随机，假定随机到的值为 val，然后利用 sum 数组的具有单调性
        // 进行「二分」，找到 val 所在的矩形（每个矩形均会贡献面积，可看做是每个矩形在数轴 [1, sum[n] 内贡献一段
        // 长度为面积的连续段，二分是为了找到点 val 所在的连续段是由哪个矩形所贡献），然后在该矩形中进行随机，
        // 得到最终的随机点
        public Solution(int[][] rects) {

            this.rs = rects;
            n = rs.length;
            sum = new int[n + 1];
            for (int i = 1; i <= n; i++)
                sum[i] = sum[i - 1] + (rs[i - 1][2] - rs[i - 1][0] + 1) * (rs[i - 1][3] - rs[i - 1][1] + 1);
        }

        public int[] pick() {

            int val = random.nextInt(sum[n]) + 1;
            int l = 0;
            int r = n;
            while (l < r) {
                int mid = l + r >> 1;
                if (sum[mid] >= val){
                    r = mid;
                } else{
                    l = mid + 1;
                }
            }

            int[] cur = rs[r - 1];
            int x = random.nextInt(cur[2] - cur[0] + 1) + cur[0];
            int y = random.nextInt(cur[3] - cur[1] + 1) + cur[1];
            return new int[]{x, y};
        }
    }
}
