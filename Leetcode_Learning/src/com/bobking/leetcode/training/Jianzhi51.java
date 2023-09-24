package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-05-22 11:33
 */
public class Jianzhi51 {

    // 参考：https://www.nowcoder.com/practice/96bd6684e04a44eb80e6a68efc0ec6c5?tpId=13&tqId=11188&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking&tab=answerKey
    public int reversePairs(int[] nums) {

        if (nums == null || nums.length == 0)
            return 0;

        // 定义一个辅助数组 help，用来记录每次 merge 过程后的排序，merge 完后，就复制回原数组
        int[] help = new int[nums.length];

        return inversePairsSum(nums, help, 0, nums.length - 1);
    }

    private int inversePairsSum(int[] nums, int[] help, int low, int high) {

        // 即此时子数组只有一个数返回 0
        if (low == high)
            return 0;
        // 将数组进行二分
        int mid = (low + high) >> 1;

        // 左边子数组产生的逆序对数
        int leftInversePairsCount = inversePairsSum(nums, help, low, mid);
        // 右边子数组产生的逆序对数
        int rightInversePairsCount = inversePairsSum(nums, help, mid + 1, high);

        // 左边子数组和右边子数组排好序后在 merge 过程中产生的逆序对数
        // 例:左边子数组排好序后为:4 6 8
        // 右边子数组排好序后为:5 7
        int count = 0;

        // 定义该角标用来表示将数压入辅助数组中的哪个位置
        int helpIndex = high;

        // 在左右两个子数组 merge 的过程中，定义两个角标分别指向两个子数组的尾部(此处和外排，归并排序有点区别，这两个是都指向头部)
        int leftSubArrayIndex = mid;
        int rightSubArrayIndex = high;

        while (leftSubArrayIndex >= low && rightSubArrayIndex > mid) {

            if (nums[leftSubArrayIndex] > nums[rightSubArrayIndex]) {
                count += rightSubArrayIndex - mid;
                // 将数复制进辅助数组中
                help[helpIndex--] = nums[leftSubArrayIndex--];
            } else {
                help[helpIndex--] = nums[rightSubArrayIndex--];
            }
        }

        // 若跳出循环则说明有一个子数组已经遍历完
        // 那么直接将剩下数组的数全部复制进辅助数组中即可
        for (; leftSubArrayIndex >= low; leftSubArrayIndex--)
            help[helpIndex--] = nums[leftSubArrayIndex];
        for (; rightSubArrayIndex > mid; rightSubArrayIndex--)
            help[helpIndex--] = nums[rightSubArrayIndex];

        // 将 help 数组中的数拷贝回原数组，因为每一次 merge 过程中，必须保证两个子数组必须分别有序
        // 因此需要将已排好序的 help 中
        for (int i = low; i <= high; i++)
            nums[i] = help[i];

        return leftInversePairsCount + rightInversePairsCount + count;
    }
}
