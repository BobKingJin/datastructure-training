package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-09-03 8:46
 */
public class Number646 {

    // 参考：https://leetcode.cn/problems/maximum-length-of-pair-chain/solution/zui-chang-shu-dui-lian-by-leetcode-solut-ifpn/
    public int findLongestChain1(int[][] pairs) {

        // 定义 dp[i] 为以 pairs[i] 为结尾的最长数对链的长度
        // 计算 dp[i] 时，可以先找出所有的满足 pairs[i][0] > pairs[j][1] 的 j，并求出最大的 dp[j]，dp[i] 的值即可赋为这个最大值加一
        // 这种动态规划的思路要求计算 dp[i] 时，所有潜在的 dp[j] 已经计算完成，可以先将 pairs 进行排序来满足这一要求
        // 初始化时，dp 需要全部赋值为 1

        int n = pairs.length;
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        return dp[n - 1];
    }

    // 参考：https://leetcode.cn/problems/maximum-length-of-pair-chain/solution/zui-chang-shu-dui-lian-by-leetcode-solut-ifpn/
    public int findLongestChain2(int[][] pairs) {

        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        List<Integer> arr = new ArrayList<Integer>();

        for (int[] p : pairs) {
            int x = p[0];
            int y = p[1];
            if (arr.isEmpty() || x > arr.get(arr.size() - 1)) {
                arr.add(y);
            } else {
                int idx = binarySearch(arr, x);
                arr.set(idx, Math.min(arr.get(idx), y));
            }
        }

        return arr.size();
    }

    private int binarySearch(List<Integer> arr, int x) {
        int low = 0;
        int high = arr.size() - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr.get(mid) >= x) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    // 参考：https://leetcode.cn/problems/maximum-length-of-pair-chain/solution/zui-chang-shu-dui-lian-by-leetcode-solut-ifpn/
    public int findLongestChain3(int[][] pairs) {

        int curr = Integer.MIN_VALUE;
        int res = 0;
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        for (int[] p : pairs) {
            if (curr < p[0]) {
                curr = p[1];
                res++;
            }
        }

        return res;
    }
}
