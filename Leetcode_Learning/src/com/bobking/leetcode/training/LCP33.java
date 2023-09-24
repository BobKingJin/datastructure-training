package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-21 10:19
 */
public class LCP33 {

    public int storeWater(int[] bucket, int[] vat) {

        int maxK = 0;
        for(int v : vat)
            maxK = Math.max(maxK, v);

        if(maxK == 0)
            return 0;

        int ans = Integer.MAX_VALUE;

        for(int k = 1; k <= maxK; k++) {

            if(k >= ans)
                break;

            int cur = k;
            for(int i = 0; i < vat.length; i++)
                cur += Math.max(vat[i] / k  + (vat[i] % k == 0 ? 0 : 1) - bucket[i], 0);
            ans = Math.min(ans, cur);
        }

        return ans;
    }
}
