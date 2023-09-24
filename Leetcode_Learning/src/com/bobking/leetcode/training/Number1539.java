package com.bobking.leetcode.training;

public class Number1539 {

    public int findKthPositive1(int[] arr, int k) {

        int i;
        int[] ans = new int[2010];

        for (i = 1; i <= 2000; i++)
            ans[i] = i;

        for (i = 0; i < arr.length; i++)
            ans[arr[i]] = -1;

        for (i = 1; i <= 2000; i++) {
            if (ans[i] == -1) {
                continue;
            } else {
                k--;
                if (k == 0)
                    break;
            }
        }
        return ans[i];
    }

    // 参考：https://leetcode.cn/problems/kth-missing-positive-number/solution/duo-chong-jie-fa-by-dao-chang-3/
    public int findKthPositive2(int[] arr, int k) {

        int len = arr.length;

        // 如果数组中没有小于 k 的数，那么第 k 个缺失的数字就是 k
        // 如果有一个 <= k 的数字，k++
        // 最后返回 k
        for (int i = 0; i < len; i++) {
            if (arr[i] <= k)
                k++;
        }

        return k;
    }

    // 参考：https://leetcode.cn/problems/kth-missing-positive-number/solution/-by-max-lfsznscofe-0qh4/
    public int findKthPositive3(int[] arr, int k) {

        // 第一个数比缺失的数要大的话，直接返回 k
        if(arr[0] > k)
            return k;

        // 设：arr = [2, 3, 4, 5, 7, 11], k = 5
        // 可以利用数组下标来得到该位置之前缺失的元素数量，
        // 例如：i = 0，此时数组元素 arr[i] = 2，在 0 位置上，不缺失的情况下，对应的元素应该是 1
        // 所以缺失个数为 arr[i] - i - 1 = 1，因为数组下标是从 0 开始的，而元素是从 1 开始的
        // 所以计算个数的时候，除了减掉下标值之外，还需要再减 1
        // 索引：        0        1        2       4       5
        //       arr = [2,       3,       4,      7,      11]
        // 缺失的个数：2-0-1=1   3-1-1=1   4-2-1=1  7-3-1=3 11-4-1=6

        // 要求的是找到缺失的第 k 个整数，观察每个元素对应的缺失个数，它们所构成的序列是一个非严格递增的序列
        // 也就是说，在这个序列中，能够通过二分查找，找到 k 所对应的位置，有了这个位置，就能通过对应的元素找到缺失的第 k 个整数
        // 举例来说，如果要找到 k = 5，第 5 个缺失的元素，那么需要从一个确定的数向后或向前推算
        // 对于 2, 3, 4来说，它们之前缺少 1 个元素，所以不考虑这些元素
        // 对于 7 来说，它之前缺少 3 个元素，那么从它开始往后推 2 个元素，就有可能是缺失的第 5 个元素
        // 前提是它后面的元素缺失数量要大于 k = 5
        // 对于 11 来说，它之前缺少 6个 元素，要找的第 5 个元素，一定是在它之前缺失的，那从它开始往前推 2 个元素（第 6 个，第 5 个）
        // 就是要找的缺失的第 5 个元素
        // 根据这种分析，就可以在这个缺失数量的序列上进行二分查找，确定一个区间[i, j]，满足 lack[i] < k <= lack[j]
        // 则第 k 个缺失的数为 k - (arr[i] - i - 1) + arr[i]，arr[i] - i - 1表示 arr[i] 位置缺少的元素个数
        // k - 缺失个数表示从 arr[i] 开始还缺少几个元素，再加上 arr[i]，就是第 k 个缺失的元素

        // 找到缺失数量大于 k 的最小的位置
        int left = 0;
        int right = arr.length;

        while(left < right){
            int mid = left + (right - left) / 2;
            int x = mid < arr.length ? arr[mid] : Integer.MAX_VALUE;
            if(x - mid - 1 >= k){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        // 第 k 个缺失的数 - （最小位置之前的那个数所缺失的个数） + 最小位置前面的那个数
        // 5 - （7 - 3 - 1） + 7 = 第五个缺失的数 - 7 前面有 3 个缺失的数 + 7
        return k - (arr[left - 1] - (left - 1) - 1) + arr[left - 1];
    }
}
