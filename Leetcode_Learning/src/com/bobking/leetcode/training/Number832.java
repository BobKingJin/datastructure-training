package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-06-24 7:58
 */
public class Number832 {

    // 参考：https://leetcode.cn/problems/flipping-an-image/solution/fan-zhuan-tu-xiang-by-leetcode-solution-yljd/
    public int[][] flipAndInvertImage(int[][] image) {

        int n = image.length;

        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;
            while (left < right) {
                if (image[i][left] == image[i][right]) {
                    image[i][left] ^= 1;
                    image[i][right] ^= 1;
                }
                left++;
                right--;
            }
            if (left == right)
                image[i][left] ^= 1;
        }
        return image;
    }
}
