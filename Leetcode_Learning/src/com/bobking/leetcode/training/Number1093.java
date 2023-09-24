package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-27 22:17
 */
public class Number1093 {

    public double[] sampleStats(int[] count) {

        int n = 0;
        int min = -1;
        int max = 0;
        int mode = 0;
        long sum = 0;

        for (int i = 0; i < 256; ++i) {
            if (count[i] == 0)
                continue;
            n += count[i];
            sum += (long) i * count[i];
            if (min == -1)
                min = i;
            max = i;
            if (count[i] > count[mode])
                mode = i;
        }
        double mean = (double) sum / n;
        int i = 0;
        int j = 255;
        // 无论奇偶，中位数的左边和右边的长度一定相等，都等于 (n − 1 ) / 2
        // 考虑从两边删除，中间剩余的就是中位数
        // 从左边删除最多 (n - 1) / 2 个数
        for (int t = (n - 1) >> 1; t >= count[i]; t -= count[i++]) ;
        // 从右边删除最多 (n - 1) / 2 个数
        for (int t = (n - 1) >> 1; t >= count[j]; t -= count[j--]) ;
        // 中间两位数(可能是同一个数)求平均
        double median = (i + j) / 2.0;
        return new double[]{min, max, mean, median, mode};
    }
}
