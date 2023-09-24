package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-04-05 11:24
 */
public class Number762 {

    boolean[] hash = new boolean[40];
    
    private void init(){

        // 利用一个 int 的二进制表示不超过 32，可以先将 32 以内的质数进行打表
        int[] nums = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
        for (int x : nums) 
            hash[x] = true;
    }

    // 参考：https://leetcode-cn.com/problems/prime-number-of-set-bits-in-binary-representation/solution/by-ac_oier-w50x/
    public int countPrimeSetBits1(int left, int right) {
        
        int res = 0;

        init();

        // 因为 1 <= left <= right <= 106，所以 count 至少为 1，那么 ++count(先加) 没问题
        for (int i = left; i <= right; i++) {

            int x = i;
            int count = 0;

            while (x != 0 && ++count >= 0) 
                x -= (x & -x);

            if (hash[count])
                res++;
        }
        
        return res;
    }

    // 参考：https://leetcode-cn.com/problems/prime-number-of-set-bits-in-binary-representation/solution/by-ac_oier-w50x/
    public int countPrimeSetBits2(int left, int right) {
        
        int res = 0;

        init();
        
        for (int i = left; i <= right; i++) {
            
            int x = i;
            x = (x & 0x55555555) + ((x >>> 1)  & 0x55555555);
            x = (x & 0x33333333) + ((x >>> 2)  & 0x33333333);
            x = (x & 0x0f0f0f0f) + ((x >>> 4)  & 0x0f0f0f0f);
            x = (x & 0x00ff00ff) + ((x >>> 8)  & 0x00ff00ff);
            x = (x & 0x0000ffff) + ((x >>> 16) & 0x0000ffff);
            
            if (hash[x]) 
                res++;
        }
        
        return res;
    }

}
