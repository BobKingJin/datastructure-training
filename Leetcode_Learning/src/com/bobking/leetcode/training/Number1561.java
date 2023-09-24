package com.bobking.leetcode.training;

import java.util.Arrays;

public class Number1561 {

    // 参考：https://leetcode.cn/problems/maximum-number-of-coins-you-can-get/solution/ni-ke-yi-huo-de-de-zui-da-ying-bi-shu-mu-by-leetco/
    public int maxCoins(int[] piles) {

        // 当 n ≥ 2 时，如何获得最大硬币数目？和 n = 1 的情况相似
        // 第一轮时，Alice 取走数量最多的，我们取走数量第二多的
        // 但不同的是我们不让 Bob 取走数量第三多的，我们让 Bob 取走数量最少的
        // 第二轮时，应该让 Alice 取走数量第三多的一堆硬币，我们就取走数量第四多的一堆硬币
        // 以此类推，试想如果我们在第一轮让 Bob 拿走了第三多的，按照这个策略我们只能在第二轮拿到数量第五多的
        // 以此类推，这个策略不如前面的策略好

        // 基于上述分析，可以看到，每一轮中，Alice 取数量最多的一堆硬币，我们取数量第二多的一堆硬币，可以让我们获得最大硬币数目
        // 由于每一轮中要选出 3 堆硬币，其中的最后一堆由 Bob 取走，为了不让 Bob 影响我们获得的最大硬币数目
        // 只要让 Bob 每次取的硬币是所有堆中数量最少的即可

        // 为了方便地知道每一堆硬币的数量之间的关系，首先对数组进行排序。排序后的数组的前 n 个元素是最小的元素，留给 Bob
        // 其余的元素则分别属于我们和 Alice
        // 每一轮，我们选出 3 堆硬币，包括数量最多的 2 堆硬币和数量最少的 1 堆硬币，我们总能获得这 3 堆硬币中的数量第二多的硬币
        // 计算可以获得的最大硬币数目时，按照从大到小的顺序遍历数组中的元素，每次遍历 2 个元素，其中较小的元素即为这一轮取走的硬币数量

        Arrays.sort(piles);
        int length = piles.length;
        int rounds = length / 3;
        int coins = 0;
        int index = length - 2;

        for (int i = 0; i < rounds; i++) {
            coins += piles[index];
            index -= 2;
        }

        return coins;
    }
}
