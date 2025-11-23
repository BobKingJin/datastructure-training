package com.bobking.leetcode.training;

public class Number4 {

    // 参考：https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int n = nums2.length;
        int[] nums = new int[m + n];

        if (m == 0) {
            if (n % 2 == 0) {
                return (nums2[n / 2 - 1] + nums2[n / 2]) / 2.0;
            } else {
                return nums2[n / 2];
            }
        }

        if (n == 0) {
            if (m % 2 == 0) {
                return (nums1[m / 2 - 1] + nums1[m / 2]) / 2.0;
            } else {
                return nums1[m / 2];
            }
        }

        int index = 0;
        int i = 0;
        int j = 0;
        // merge 过程，因为 nums1 有序且 nums2 有序
        while (index != (m + n)) {
            if (i == m) {
                while (j != n) {
                    nums[index++] = nums2[j++];
                }
                break;
            }
            if (j == n) {
                while (i != m) {
                    nums[index++] = nums1[i++];
                }
                break;
            }

            if (nums1[i] < nums2[j]) {
                nums[index++] = nums1[i++];
            } else {
                nums[index++] = nums2[j++];
            }
        }
        if (index % 2 == 0) {
            return (nums[index / 2 - 1] + nums[index / 2]) / 2.0;
        } else {
            return nums[index / 2];
        }
    }

    // 参考：https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/
    public double findMedianSortedArrays2(int[] A, int[] B) {

        int m = A.length;
        int n = B.length;
        int len = m + n;
        // 返回中位数的话，奇数需要最后一次遍历的结果就可以了，偶数需要最后一次和上一次遍历的结果
        // 所以用两个变量 left 和 right，right 保存当前循环的结果，在每次循环前将 right 的值赋给 left
        // 这样在最后一次循环的时候，left 将得到 right 的值，也就是上一次循环的结果，接下来 right 更新为
        // 最后一次的结果
        int left = -1;
        int right = -1;
        int aStart = 0;
        int bStart = 0;
        // 无论奇偶，都只需要遍历 (len / 2) + 1 次
        for (int i = 0; i <= len / 2; i++) {
            // 记录上一次遍历到的数
            left = right;
            //                因为会先判断 或条件 的左边 因此当 bStart >= n 时不会执行后面
            //                这个位置注意: bStart >= n 也是一种取 right = A[aStart++] 的情况
            if (aStart < m && (bStart >= n || A[aStart] < B[bStart])) {
                right = A[aStart++];
            } else {
                right = B[bStart++];
            }
        }

        if ((len & 1) == 0) {
            return (left + right) / 2.0;
        } else {
            return right;
        }
    }

    // 参考：https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/
    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {

        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        // 将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0,
            m - 1, right)) * 0.5;
    }

    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2,
        int k) {

        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        // 让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) {
            return getKth(nums2, start2, end2, nums1, start1, end1, k);
        }
        if (len1 == 0) {
            return nums2[start2 + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        //               每次都是取 k/2 的数进行比较，有时候可能会遇到数组长度小于 k/2 的时候
        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        } else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }

}
