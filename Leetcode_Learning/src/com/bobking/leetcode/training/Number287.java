package com.bobking.leetcode.training;

public class Number287 {

    // 遍历数组进行异或，应该会超时 时间复杂度为 O(n)

    // 参考：https://leetcode-cn.com/problems/find-the-duplicate-number/solution/er-fen-fa-si-lu-ji-dai-ma-python-by-liweiwei1419/
    public int findDuplicate1(int[] nums) {

        if (nums == null || nums.length < 2)
            return 0;

        // 注意这里 left 是 1，因为 mid 应该是 1 ~ (nums.length - 1)的中间数
        // 例如：1  2  3  3  4  5  6 这里 7 个数，这里的判断是以 (1 + 6) / 2 = 3 为分界线
        int left = 1;
        int right = nums.length - 1;

        while (left < right) {

            int mid = left + (right - left) / 2;

            int count = 0;
            for (int num : nums) {
                if (num <= mid)
                    count += 1;
            }

            // 根据抽屉原理
            if (count > mid) {
                // 重复元素位于区间 [left..mid]
                right = mid;
            } else {
                // 重复元素位于区间 [(mid + 1)..right]
                left = mid + 1;
            }
        }

        return left;
    }

    // 参考：https://leetcode-cn.com/problems/find-the-duplicate-number/solution/xun-zhao-zhong-fu-shu-by-leetcode-solution/
    public int findDuplicate2(int[] nums) {

        if (nums == null || nums.length < 2)
            return 0;

        int res = 0;

        int maxBit = 31;
        // 从左往右，找到 maxBit 中第一个 1
        // 即 nums 中最大的数 n 的二进位最大位数，例如 4 可表示为 100 即三位，那么 maxBit 为 3
        while ((nums.length - 1) >>> maxBit == 0)
            maxBit--;

        for (int i = 0; i <= maxBit; i++) {

            int x = 0;
            int y = 0;

            for (int j = 0; j < nums.length; j++) {
                if ((nums[j] & (1 << i)) != 0)
                    x++;

                if (j >= 1 && (j & (1 << i)) != 0)
                    y++;
            }

            if (x > y)
                res = res | (1 << i);
        }

        return res;
    }

    // 参考：https://leetcode-cn.com/problems/find-the-duplicate-number/solution/xun-zhao-zhong-fu-shu-by-leetcode-solution/
    public int findDuplicate3(int[] nums) {

        // slow fast一开始都是站在 0 这个起点
        // 例如 nums:  3  4  3  1  2
        //    index:  0  1  2  3  4
        // 那么连成的环是:  0 - 3 - 1 - 4 - 2 - 3
        int slow = 0;
        int fast = 0;

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}
