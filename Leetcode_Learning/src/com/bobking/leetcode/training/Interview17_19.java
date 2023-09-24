package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-26 8:06
 */
public class Interview17_19 {

    // 参考：https://leetcode.cn/problems/missing-two-lcci/solution/zhuan-zhi-xiao-shi-de-shu-de-san-chong-jie-fa-by-w/
    public int[] missingTwo1(int[] nums) {

        int n = nums.length + 2;
        long sum = 0;

        for (Integer x: nums)
            sum += x;

        long sumTwo = n * (n + 1) / 2 - sum;
        long limits = sumTwo / 2;
        sum = 0;

        for (Integer x: nums) {
            if (x <= limits)
                sum += x;
        }
        // 两个数不相同那么一个大于，一个小于
        // limits * (limits + 1) / 2 为小于 limit 的总数之和
        long one = limits * (limits + 1) / 2 - sum;
        return new int[]{(int)one, (int)(sumTwo - one)};
    }

    // 参考：https://leetcode.cn/problems/missing-two-lcci/solution/zhuan-zhi-xiao-shi-de-shu-de-san-chong-jie-fa-by-w/
    public int[] missingTwo2(int[] nums) {

        int ans = 0;
        int n = nums.length;

        // 1 到 n + 2 异或和
        for (int i = 1; i <= n + 2; i ++)
            ans ^= i;
        // 获得了两个缺失数的异或值
        for (Integer x: nums)
            ans ^= x;

        int one = 0;
        // diff 就是这个异或值的 lowbit，代表着这两个缺失数在 diff 位上必有一个是 0 和一个是 1
        int diff = ans & -ans;

        // 可以将 1 到 n + 2 以及 nums 分为两组，diff 位置为 0 的为一组，diff 位置为 1 的为一组
        // 而缺失的两个数必然分别在不同的分组
        for (int i = 1; i <= n + 2; i ++) {
            if ((diff & i) == 1)
                one ^= i;
        }

        for (Integer x: nums) {
            if ((diff & x) == 1)
                one ^= x;
        }

        return new int[]{one, one ^ ans};
    }


}
