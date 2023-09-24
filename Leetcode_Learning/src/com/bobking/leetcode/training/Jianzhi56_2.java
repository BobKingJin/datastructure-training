package com.bobking.leetcode.training;

public class Jianzhi56_2 {

    // 参考：程序猿代码指南P359
    public int singleNumber1(int[] nums) {

        if (nums == null || nums.length == 0)
            return 0;

        return onceNum(nums, 3);
    }

    private int onceNum(int[] nums, int k) {

        int[] add = new int[32];
        for (int i = 0; i < nums.length; i++)
            setExclusiveOr(add, nums[i], k);
        // 将 K 进制转为 10 进制
        int res = getNumFromKSysNum(add, k);
        return res;
    }

    private void setExclusiveOr(int[] add, int value, int k) {

        // 将 value 转为 k 进制
        int[] curKSysNum = getKSysNumFromNum(value, k);
        for (int i = 0; i < add.length; i++)
            add[i] = (add[i] + curKSysNum[i]) % k;
    }

    // 将 value 转为 k 进制
    private int[] getKSysNumFromNum(int value, int k) {

        int[] res = new int[32];
        int index = 0;
        while (value != 0) {
            // 注意从 左到右  是  低位到高位
            res[index++] = value % k;
            value = value / k;
        }

        return res;
    }

    // 将 k 进制转为 十进制
    private int getNumFromKSysNum(int[] add, int k) {

        int res = 0;
        // 注意是从后往前  从左到右  是  低位到高位
        for (int i = add.length - 1; i >= 0; i--)
            res = res * k + add[i];

        return res;
    }

    // 参考：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/solution/mian-shi-ti-56-ii-shu-zu-zhong-shu-zi-chu-xian-d-4/
    public int singleNumber2(int[] nums) {

        if (nums == null || nums.length == 0)
            return 0;

        int ones = 0;
        int twos = 0;
        for (int num : nums) {
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }

        return ones;
    }

    // 参考：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/solution/mian-shi-ti-56-ii-shu-zu-zhong-shu-zi-chu-xian-d-4/
    public int singleNumber3(int[] nums) {

        if (nums == null || nums.length == 0)
            return 0;

        int[] counts = new int[32];
        for (int num : nums) {
            for (int j = 0; j < 32; j++) {
                counts[j] += num & 1;
                num >>>= 1;
            }
        }

        int res = 0;
        int m = 3;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            // 位或 |
            res |= counts[31 - i] % m;
        }

        return res;
    }
}
