package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

public class Number658 {

    // 参考：https://leetcode.cn/problems/find-k-closest-elements/solution/pai-chu-fa-shuang-zhi-zhen-er-fen-fa-python-dai-ma/
    public List<Integer> findClosestElements1(int[] arr, int k, int x) {

        int size = arr.length;

        int left = 0;
        int right = size - 1;

        int removeNums = size - k;

        while (removeNums > 0) {
            if (x - arr[left] <= arr[right] - x) {
                right--;
            } else {
                left++;
            }
            removeNums--;
        }

        List<Integer> res = new ArrayList<Integer>();
        for (int i = left; i < left + k; i++)
            res.add(arr[i]);

        return res;
    }

    // 参考：https://leetcode.cn/problems/find-k-closest-elements/solution/by-ac_oier-8xh5/
    public List<Integer> findClosestElements2(int[] arr, int k, int x) {


        // 先通过「二分」找到与 x 差值最小的位置 idx
        // 然后从 idx 开始使用「双指针」往两边进行拓展（初始化左端点 i = idx - 1，右端点 j = idx + 1）
        // 含义为 [i + 1, j - 1] 范围内子数组为候选区间，不断根据两边界与 x 的差值关系进行扩充
        // 直到候选区间包含 k 个数

        int n = arr.length;
        int l = 0;
        int r = n - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (arr[mid] <= x) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        r = r + 1 < n && Math.abs(arr[r + 1] - x) < Math.abs(arr[r] - x) ? r + 1 : r;
        int i = r - 1;
        int j = r + 1;
        while (j - i - 1 < k) {
            if (i >= 0 && j < n) {
                if (Math.abs(arr[j] - x) < Math.abs(arr[i] - x)) {
                    j++;
                } else {
                    i--;
                }
            } else if (i >= 0) {
                i--;
            } else {
                j++;
            }
        }

        List<Integer> ans = new ArrayList<Integer>();
        for (int p = i + 1; p <= j - 1; p++)
            ans.add(arr[p]);

        return ans;
    }

    // 参考：https://leetcode.cn/problems/find-k-closest-elements/solution/pai-chu-fa-shuang-zhi-zhen-er-fen-fa-python-dai-ma/
    public List<Integer> findClosestElements3(int[] arr, int k, int x) {

        int size = arr.length;

        int left = 0;
        int right = size - k;

        while (left < right) {

            int mid = (left + right) >>> 1;
            // 尝试从长度为 k + 1 的连续子区间删除一个元素
            // 从而定位左区间端点的边界值
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        List<Integer> res = new ArrayList<Integer>();
        for (int i = left; i < left + k; i++)
            res.add(arr[i]);

        return res;
    }

}
