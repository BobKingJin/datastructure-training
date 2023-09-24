package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-08-12 17:55
 */
public class Number1095 {

    private interface MountainArray {

        public int get(int index);

        public int length();
    }

    private class MountainArrayImpl implements MountainArray {

        private int[] arr;
        private int size;

        public MountainArrayImpl(int[] arr) {
            this.arr = arr;
            this.size = this.arr.length;
        }

        @Override
        public int get(int index) {
            return arr[index];
        }

        @Override
        public int length() {
            return size;
        }
    }

    // 参考：https://leetcode-cn.com/problems/find-in-mountain-array/solution/shi-yong-chao-hao-yong-de-er-fen-fa-mo-ban-python-/
    // 特别注意：3 个辅助方法的分支出奇地一样，因此选中位数均选左中位数，才不会发生死循环
    public int findInMountainArray(int target, MountainArray mountainArr) {

        int size = mountainArr.length();
        // 步骤 1：先找到山顶元素所在的索引
        int mountaintop = findMountaintop(mountainArr, 0, size - 1);

        // 步骤 2：在前有序且升序数组中找 target 所在的索引
        int res = findFromSortedArr(mountainArr, 0, mountaintop, target);

        if (res != -1)
            return res;

        // 步骤 3：如果步骤 2 找不到，就在后有序且降序数组中找 target 所在的索引
        return findFromInversedArr(mountainArr, mountaintop + 1, size - 1, target);
    }

    // 返回山顶元素
    private int findMountaintop(MountainArray mountainArr, int l, int r) {

        while (l < r) {

            int mid = l + (r - l) / 2;
            // 取左中位数，因为进入循环，数组一定至少有 2 个元素
            // 因此，左中位数一定有右边元素，数组下标不会发生越界
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                // 如果当前的数比右边的数小，它一定不是山顶
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        // 根据题意，山顶元素一定存在，因此退出 while 循环的时候，不用再单独作判断
        return l;
    }

    // 在前有序且升序数组中找 target 所在的索引
    private int findFromSortedArr(MountainArray mountainArr, int l, int r, int target) {

        while (l < r) {

            int mid = l + (r - l) / 2;
            if (mountainArr.get(mid) < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        // 因为不确定区间收缩成 1个数以后，这个数是不是要找的数，因此单独做一次判断
        if (mountainArr.get(l) == target)
            return l;

        return -1;
    }

    // 在后有序且降序数组中找 target 所在的索引
    private int findFromInversedArr(MountainArray mountainArr, int l, int r, int target) {

        while (l < r) {

            int mid = l + (r - l) / 2;
            // 与 findFromSortedArr 方法不同的地方仅仅在于由原来的小于号改成大于好
            if (mountainArr.get(mid) > target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        // 因为不确定区间收缩成 1个数以后，这个数是不是要找的数，因此单独做一次判断
        if (mountainArr.get(l) == target)
            return l;

        return -1;
    }

}
