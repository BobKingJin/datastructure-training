package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-20 8:22
 */
public class Number1013 {

    public boolean canThreePartsEqualSum(int[] arr) {

        int sum = 0;
        for (int i : arr)
            sum += i;

        if (sum % 3 != 0)
            return false;

        int left = 0;
        int leftSum = arr[left];
        int right = arr.length - 1;
        int rightSum = arr[right];

        // 使用 left + 1 < right 的原因，防止只能将数组分成两个部分
        // 例如：[1, -1, 1, -1]，使用 left < right 作为判断条件就会出错
        while (left + 1 < right) {

            if (leftSum == sum / 3 && rightSum == sum / 3)
                return true;

            if (leftSum != sum / 3)
                // 注意这个位置是 ++left
                leftSum += arr[++left];

            if (rightSum != sum / 3)
                // 注意这个位置是 --right
                rightSum += arr[--right];
        }
        return false;
    }
}
