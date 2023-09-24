package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-09-24 11:29
 */
public class Number945 {

    // 参考：https://leetcode.cn/problems/minimum-increment-to-make-array-unique/solution/ji-shu-onxian-xing-tan-ce-fa-onpai-xu-onlogn-yi-ya/
    public int minIncrementForUnique1(int[] nums) {

        // 先排序
        Arrays.sort(nums);
        int move = 0;

        // 遍历数组，若当前元素小于等于它的前一个元素，则将其变为前一个数 + 1
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) {
                int pre = nums[i];
                nums[i] = nums[i - 1] + 1;
                move += nums[i] - pre;
            }
        }
        return move;
    }

    // 参考：https://leetcode.cn/problems/minimum-increment-to-make-array-unique/solution/ji-shu-onxian-xing-tan-ce-fa-onpai-xu-onlogn-yi-ya/
    public int minIncrementForUnique2(int[] nums) {

        // counter数组统计每个数字的个数
        // 这里为了防止下面遍历 counter 的时候每次都走到 40000，所以设置了一个 max，这个数据量不设也行，再额外设置min也行
        int[] counter = new int[40001];
        int max = -1;
        for (int num: nums) {
            counter[num]++;
            max = Math.max(max, num);
        }

        // 遍历 counter数组，若当前数字的个数 cnt 大于 1 个，则只留下 1 个，其他的 cnt - 1 个后移
        int move = 0;
        for (int num = 0; num <= max; num++) {
            if (counter[num] > 1) {
                int d = counter[num] - 1;
                move += d;
                counter[num + 1] += d;
            }
        }

        // 最后 counter[max + 1] 里可能会有从 counter[max] 后移过来的，counter[max + 1]里只留下 1 个，其它的 d 个后移
        // 设 max + 1 = x，那么后面的 d 个数就是 [x + 1, x + 2, x + 3, ... ,x + d]
        // 因此操作次数是 [1, 2, 3, ... ,d], 用求和公式求和
        int d = counter[max + 1] - 1;
        move += (1 + d) * d / 2;
        return move;
    }

    int[] pos = new int [80000];

    // 参考：https://leetcode.cn/problems/minimum-increment-to-make-array-unique/solution/ji-shu-onxian-xing-tan-ce-fa-onpai-xu-onlogn-yi-ya/
    public int minIncrementForUnique3(int[] nums) {

        // -1 表示空位
        Arrays.fill(pos, -1);
        int move = 0;

        // 遍历每个数字 a 对其寻地址得到位置 b, b 比 a 的增量就是操作数
        for (int a : nums) {
            int b = findPos(a);
            move += b - a;
        }

        return move;
    }

    // 线性探测寻址（含路径压缩）
    private int findPos(int a) {

        int b = pos[a];
        // 如果 a 对应的位置 pos[a] 是空位，直接放入即可
        if (b == -1) {
            pos[a] = a;
            return a;
        }

        // 否则向后寻址
        // 因为 pos[a] 中标记了上次寻址得到的空位，因此从pos[a] + 1开始寻址就行了（不需要从 a + 1 开始）
        b = findPos(b + 1);
        // 寻址后的新空位要重新赋值给 pos[a]，路径压缩就是体现在这里
        pos[a] = b;
        return b;
    }

}
