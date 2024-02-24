package com.bobking.leetcode.training;

public class  Number34 {

    public int[] searchRange1(int[] nums, int target) {

        if (nums == null || nums.length == 0)
            return new int[]{-1, -1};

        int[] res = new int[]{-1, -1};

        int l = 0;
        int h = nums.length - 1;
        int mid = 0;

        while (l <= h) {
            mid = l + (h - l) / 2;
            if (nums[mid] == target) {
                res[0] = mid;
                res[1] = mid;
                int lIndex = mid - 1;
                int rIndex = mid + 1;
                while (lIndex >= 0) {
                    if (nums[lIndex] == target) {
                        res[0] = lIndex;
                        lIndex--;
                    } else {
                        break;
                    }
                }
                while (rIndex <= nums.length - 1) {
                    if (nums[rIndex] == target) {
                        res[1] = rIndex;
                        rIndex++;
                    } else {
                        break;
                    }
                }

                return res;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }

        return res;
    }

    // 参考：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/zai-pai-xu-shu-zu-zhong-cha-zhao-yuan-su-de-di-3-4/
    public int[] searchRange2(int[] nums, int target) {

        if (nums == null || nums.length == 0)
            return new int[]{-1, -1};

        int[] res = new int[]{-1, -1};

        // 查找第一个大于等于 target 的下标
        int leftIndex = binarySearch(nums, target, true);
        // 查找第一个大于 target 的下标
        int rightIndex = binarySearch(nums, target, false) - 1;

        if ((leftIndex >= 0 && leftIndex <= nums.length - 1 && nums[leftIndex] == target) &&
                (rightIndex >= 0 && rightIndex <= nums.length - 1 && nums[rightIndex] == target)) {
            res[0] = leftIndex;
            res[1] = rightIndex;
            return res;
        }

        return res;
    }

    // lower 为 true 时表示查找 nums 中最左边大于等于 target 的数的角标(即第一个大于等于 target 的数的角标)
    // loewer 为 false 时查找 nums 中第一个大于 target 的数的角标
    private int binarySearch(int[] nums, int target, boolean lower) {

        int l = 0;
        int h = nums.length - 1;
        // res 初始化为 nums.length
        int res = nums.length;
        int mid = 0;

        while (l <= h) {
            mid = l + (h - l) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                h = mid - 1;
                res = mid;
            } else {
                l = mid + 1;
            }
        }

        return res;
    }

    // 参考：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/si-lu-hen-jian-dan-xi-jie-fei-mo-gui-de-er-fen-cha/
    public int[] searchRange3(int[] nums, int target) {

        if (nums == null || nums.length == 0)
            return new int[]{-1, -1};

        int firstPosition = findFirstPosition(nums, target);
        if (firstPosition == -1)
            return new int[]{-1, -1};

        int lastPosition = findLastPosition(nums, target);
        return new int[]{firstPosition, lastPosition};
    }

    private int findFirstPosition(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 小于一定不是解
            if (nums[mid] < target) {
                // 下一轮搜索区间是 [mid + 1..right]
                left = mid + 1;
            } else {
                // nums[mid] > target，下一轮搜索区间是 [left..mid]
                right = mid;
            }
        }

        if (nums[left] == target)
            return left;

        return -1;
    }

    private int findLastPosition(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // 0 1 2 3 4 5 即当 nums.length 为偶数时，l = 0，r = 5 则 m = 2
            int mid = left + (right - left + 1) / 2;
            if (nums[mid] > target) {
                // 下一轮搜索区间是 [left..mid - 1]
                right = mid - 1;
            } else
                // 下一轮搜索区间是 [mid..right]
                left = mid;
        }

        return left;
    }
}
