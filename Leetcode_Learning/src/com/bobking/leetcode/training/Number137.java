package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-06-27 12:30
 */
public class Number137 {

    // 参考：程序猿代码指南P359
    // 多解法参考：https://leetcode-cn.com/problems/single-number-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--31/
    // 关于此解法为什么不通过，见解法讨论
    public int singleNumber1(int[] nums) {

        if (nums == null || nums.length == 0)
            return 0;

        return onceNum(nums, 3);
    }

    private int onceNum(int[] nums, int k) {

        int[] add = new int[32];
        for (int i = 0; i < nums.length; i++)
            setExclusiveOr(add, nums[i], k);
        // 将 k 进制转为十进制
        int res = getNumFromKSysNum(add, k);
        return res;
    }

    private void setExclusiveOr(int[] add, int value, int k) {
        // 将 value 转为 k 进制
        int[] curKSysNum = getKSysNumFromNum(value, k);
        for (int i = 0; i < add.length; i++) {
            add[i] = (add[i] + curKSysNum[i]) % k;
        }
    }

    private int[] getKSysNumFromNum(int value, int k) {

        int[] res = new int[32];
        int index = 0;
        while (value != 0) {
            res[index++] = value % k;
            value = value / k;
        }

        return res;
    }

    private int getNumFromKSysNum(int[] add, int k) {

        int res = 0;

        for (int i = add.length - 1; i >= 0; i--)
            res = res * k + add[i];

        return res;
    }

    // 参考：https://leetcode-cn.com/problems/single-number-ii/solution/single-number-ii-mo-ni-san-jin-zhi-fa-by-jin407891/
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

    // 参考：https://leetcode-cn.com/problems/single-number-ii/solution/single-number-ii-mo-ni-san-jin-zhi-fa-by-jin407891/
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
            res |= counts[31 - i] % m;
        }

        return res;
    }


}
