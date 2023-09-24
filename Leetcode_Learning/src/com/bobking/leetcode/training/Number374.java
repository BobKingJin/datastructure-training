package com.bobking.leetcode.training;

import java.util.Random;

/**
 * @author BobKing
 * @create 2022-06-12 10:43
 */
public class Number374 {

    // 参考：https://leetcode.cn/problems/guess-number-higher-or-lower/solution/gong-shui-san-xie-shi-yong-jiao-hu-han-s-tocm/
    public int guessNumber(int n) {

        int l = 1;
        int r = n;
        while (l < r) {
            // 根据数据范围需要注意计算 mid 时的爆 int 问题，可以通过使用类似 l + (r - l) / 2 的做法解决，也可以通过一个临时 long 来解决
            long tmp = (long)l + r >> 1;
            int mid = (int)tmp;
            if (guess(mid) <= 0) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return r;
    }

    private int guess(int num){

        Random random = new Random();

        int picked = random.nextInt();

        if(num < picked){
            return -1;
        }else if(num > picked){
            return 1;
        }else{
            return 0;
        }
    }
}
