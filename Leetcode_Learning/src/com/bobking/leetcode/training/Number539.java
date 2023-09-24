package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-04-04 20:23
 */
public class Number539 {

    // 参考：https://leetcode-cn.com/problems/minimum-time-difference/solution/gong-shui-san-xie-jian-dan-mo-ni-ti-by-a-eygg/
    public int findMinDifference1(List<String> timePoints) {

        int n = timePoints.size() * 2;
        int[] nums = new int[n];
        for (int i = 0, idx = 0; i < n / 2; i++, idx += 2) {

            String[] ss = timePoints.get(i).split(":");
            int h = Integer.parseInt(ss[0]);
            int m = Integer.parseInt(ss[1]);
            nums[idx] = h * 60 + m;
            // 分布在 00:00 左右两侧（横跨了一天）的时间点
            // 因此，一种简单的方式是对于每个 timePoints[i]，不仅记录「当天该时间点」对应的的偏移量，还记录「下一天该时间点」对应的偏移量
            // 当 timePoints 中相邻的两个元素没有相隔 24 个小时，那么必不会成为最后的 res，所以不需要担心这里相邻两个元素到底是不是相隔一天
            nums[idx + 1] = nums[idx] + 1440;
        }

        Arrays.sort(nums);
        int res = nums[1] - nums[0];
        for (int i = 0; i < n - 1; i++)
            res = Math.min(res, nums[i + 1] - nums[i]);

        return res;
    }

    // 参考：https://leetcode-cn.com/problems/minimum-time-difference/solution/gong-shui-san-xie-jian-dan-mo-ni-ti-by-a-eygg/
    public int findMinDifference2(List<String> timePoints) {
        
        int n = timePoints.size();
        
        if (n > 1440) 
            return 0;
        
        int[] cnts = new int[1440 * 2 + 10];
        for (String s : timePoints) {

            String[] ss = s.split(":");
            int h = Integer.parseInt(ss[0]);
            int m = Integer.parseInt(ss[1]);
            cnts[h * 60 + m]++;
            cnts[h * 60 + m + 1440]++;
        }
        
        int res = 1440;
        int last = -1;

        for (int i = 0; i <= 1440 * 2 && res != 0; i++) {

            if (cnts[i] == 0)
                continue;

            // 根据题意，一共有 24 × 60 = 1440 种不同的时间。由鸽巢原理可知，如果 timePoints 的长度超过 1440，那么必然会有两个相同的时间，此时可以直接返回 0
            if (cnts[i] > 1) {
                res = 0;
            } else if (last != -1) {
                res = Math.min(res, i - last);
            }

            last = i;
        }
        
        return res;
    }


}
