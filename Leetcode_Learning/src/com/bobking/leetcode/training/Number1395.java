package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-06-18 12:33
 */
public class Number1395 {

    // 参考：https://leetcode.cn/problems/count-number-of-teams/solution/tong-ji-zuo-zhan-dan-wei-shu-by-leetcode-solution/
    public int numTeams1(int[] rating) {

        int n = rating.length;
        int ans = 0;

        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                for (int k = j + 1; k < n; ++k) {
                    if ((rating[i] < rating[j] && rating[j] < rating[k]) || (rating[i] > rating[j] && rating[j] > rating[k]))
                        ++ans;
                }
            }
        }

        return ans;
    }

    // 参考：https://leetcode.cn/problems/count-number-of-teams/solution/tong-ji-zuo-zhan-dan-wei-shu-by-leetcode-solution/
    public int numTeams2(int[] rating) {

        // 可以枚举三元组 (i, j, k) 中的 j，它是三元组的中间点。在这之后，统计
        // 出现在位置 j 左侧且比 j 评分低的士兵数量 iLess
        // 出现在位置 j 左侧且比 j 评分高的士兵数量 iMore
        // 出现在位置 j 右侧且比 j 评分低的士兵数量 kLess
        // 出现在位置 j 右侧且比 j 评分高的士兵数量 kMore
        // 这样，任何一个出现在 iLess 中的士兵 i，以及出现在 kMore中的士兵 k，都可以和 j 组成一个严格单调递增的三元组
        // 同理，任何一个出现在 iMore 中的士兵 i，以及出现在 kLess 中的士兵 k，都可以和 j 组成一个严格单调递减的三元组
        // 因此，以 j 为中间点的三元组的数量为：iLless * kMore + iMore * kLess

        int n = rating.length;
        int ans = 0;

        // 枚举三元组中的 j
        for (int j = 1; j < n - 1; ++j) {
            int iless = 0;
            int imore = 0;
            int kless = 0;
            int kmore = 0;

            for (int i = 0; i < j; ++i) {
                if (rating[i] < rating[j]) {
                    ++iless;
                    // 注意这里不能直接写成 else
                    // 因为可能有评分相同的情况
                } else if (rating[i] > rating[j]) {
                    ++imore;
                }
            }

            for (int k = j + 1; k < n; ++k) {
                if (rating[k] < rating[j]) {
                    ++kless;
                } else if (rating[k] > rating[j]) {
                    ++kmore;
                }
            }

            ans += iless * kmore + imore * kless;
        }

        return ans;
    }

    // 参考：https://leetcode.cn/problems/count-number-of-teams/solution/on2fu-za-du-shuang-100-by-6liuyu123/
    public int numTeams3(int[] rating){

        int n = rating.length;
        if (n <= 2)
            return 0;

        // minToMax2[j]：i -> j 中间有多少数 rating[j] 小
        int[] minToMax2 = new int[n];
        // maxToMin2[j]：i -> j 中间有多少数 rating[j] 大
        int[] maxToMin2 = new int[n];
        int i;
        int j;
        int result = 0;
        for (i = 0; i < n; i++) {

            minToMax2[i] = 0;
            maxToMin2[i] = 0;
            for (j = i - 1; j >= 0; j--) {
                if (rating[i] > rating[j]) {
                    // 计算有多少数比 rating[i] 小
                    minToMax2[i]++;
                    // rating[i] > rating[j] rating[j] 比 minToMax2[j] 个下标小于 j 的数大
                    // 所以 rating[k](k < j) < rating[j] < rating[i] 共有 minToMax2 [j 种情况]
                    result += minToMax2[j];
                }
                if (rating[i] < rating[j]) {
                    maxToMin2[i]++;
                    result += maxToMin2[j];
                }
            }
        }

        return result;
    }

}
