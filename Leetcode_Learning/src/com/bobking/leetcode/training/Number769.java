package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-10-13 9:12
 */
public class Number769 {

    // 参考：https://leetcode.cn/problems/max-chunks-to-make-sorted/solution/by-zrzhuo-xvm6/
    public int maxChunksToSorted(int[] arr) {

        int ans = 0;
        // sum记录当前区间的元素值之和
        int sum = 0;
        // total记录当前区间的下标和
        int total = 0;

        for(int i = 0; i < arr.length; ++i){
            sum += arr[i];
            total += i;
            // 当 sum == total 时，说明当前区间中所有元素和所有下标存在一一对应关系
            // 则当前区间就可以当作一个块
            if(sum == total)
                ++ans;
                // sum = 0, total = 0;
        }
        return ans;
    }
}
