package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-07-31 14:46
 */
public class Interview16_16 {

    // 参考：https://leetcode.cn/problems/sub-sort-lcci/
    public int[] subSort(int[] array) {

        // 原理：如果左侧最大值大于中间的最小值，则一定会被中间序列包括
        // 同理，如果右侧最小值大于中间的最大值，则一定会被中间序列包括
        // 一遍遍历 + 两个指针（两次扫描可一次遍历完成）
        // 1、从前向后扫描数组，判断当前 array[i] 是否比 max 小，是则将 last 置为当前 array 下标 i，否则更新 max
        // 2、从后向前扫描数组，判断当前 array[len - 1 - i] 是否比 min 大，是则将 first 置位当前下标 len - 1 - i，否则更新 min
        // 3、返回 {first， last}

        if(array == null || array.length == 0)
            return new int[]{-1, -1};

        int first = -1;
        int last = -1;
        // 左侧最大值
        int max = Integer.MIN_VALUE;
        // 右侧最小值
        int min = Integer.MAX_VALUE;
        int len = array.length;

        for(int i = 0; i < len; i++){
            if(array[i] < max){
                last = i;
            }else{
                max = Math.max(max, array[i]);
            }

            if(array[len - 1 - i] > min){
                first = len - 1 - i;
            }else{
                min = Math.min(min, array[len - 1 - i]);
            }
        }

        return new int[] {first, last};
    }
}
