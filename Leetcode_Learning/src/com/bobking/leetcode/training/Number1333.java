package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 2023/12/17 6:40
 * @Author: BobKing
 * @Description:
 */
public class Number1333 {

    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {

        Arrays.sort(restaurants, (a, b) -> a[1] == b[1] ? b[0] - a[0] : b[1] - a[1]);
        List<Integer> ans = new ArrayList<Integer>();

        for (int[] r : restaurants) {
            if (r[2] >= veganFriendly && r[3] <= maxPrice && r[4] <= maxDistance)
                ans.add(r[0]);
        }
        return ans;
    }
}
