package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-07-24 11:07
 */
public class Number1535 {

    // 参考：https://leetcode.cn/problems/find-the-winner-of-an-array-game/solution/zhao-chu-shu-zu-you-xi-de-ying-jia-by-leetcode-sol/
    public int getWinner(int[] arr, int k) {

        // 首先考虑 k = 1 的情况，当 k = 1 时，只有 arr[0] 和 arr[1] 之间有一回合游戏，由于一定能分出胜负，因此直接返回 arr[0] 和 arr[1] 中的最大值即可
        // 然后考虑 k > 1 的情况。根据题目描述，每回合游戏之后，较小的整数移至数组的末尾。其实，并不需要对数组进行更新
        // 在第一回合游戏之后，无论 arr[0] 和 arr[1] 当中谁取得胜利，第二回合游戏的另一个整数一定是 arr 中的下一个整数
        // 推广到一般的情况，当 2 ≤ i < arr.length 时，第 i 回合游戏一定在第 i - 1 回合游戏中取得胜利的整数和 arr[i] 之间进行
        // 因此，需要记录上一回合游戏中取得胜利的整数和该整数取得连续胜利的回合数
        // 使用 prev 表示上一回合游戏中取得胜利的整数，使用 consecutive 表示该整数取得连续胜利的回合数
        // 第一回合游戏在 arr[0] 和 arr[1] 之间进行，第一回合游戏之后，prev 为 arr[0] 和 arr[1] 中的较大值，consecutive 的值为 1
        // 当 2 ≤ i < arr.length 时，令 cur = arr[i]，第 i 回合游戏在 prev 和 cur 之间进行，可能有以下两种情况：
        // 如果 prev > curr，则 prev 不变，将 consecutive 的值加 1
        // 如果 consecutive 的值更新之后等于 k，则 prev 赢得 k 个连续回合，成为游戏的赢家，将 prev 返回即可
        // 如果 prev < curr，则 curr 取得胜利，令 prev = curr，并将 consecutive 的值更新为 1
        // 在上述过程中，同时维护数组 arr 中的最大值 maxNum
        // 如果遍历 arr 之后仍未发现有整数赢得 k 个连续回合，则不需要继续模拟，数组 arr 中的最大值 maxNum 即为游戏的赢家
        // 为什么不需要继续模拟就能知道 maxNum 为游戏的赢家？因为 maxNum 是数组 arr 中的最大值，无论和哪个整数进行游戏，maxNum 都能取得胜利
        // 因此在遍历 arr 之后，maxNum 一定位于 arr[0]，且将一直位于 arr[0]，在其余的游戏中，maxNum 将永远取得胜利，自然也会赢得 k 个连续回合
        int prev = Math.max(arr[0], arr[1]);
        if (k == 1)
            return prev;

        int consecutive = 1;
        int maxNum = prev;
        int length = arr.length;

        for (int i = 2; i < length; i++) {
            int cur = arr[i];
            if (prev > cur) {
                consecutive++;
                if (consecutive == k)
                    return prev;
            } else {
                prev = cur;
                consecutive = 1;
            }
            maxNum = Math.max(maxNum, cur);
        }
        return maxNum;
    }

}
