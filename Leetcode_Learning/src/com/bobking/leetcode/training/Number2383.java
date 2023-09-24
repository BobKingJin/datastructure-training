package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-03-13 22:49
 */
public class Number2383 {

    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {

        int ans = 0;
        for (int i = 0; i < energy.length; ++i) {
            int e = energy[i];
            if (initialEnergy <= e) {
                ans += e + 1 - initialEnergy;
                // 补到刚好超过
                initialEnergy = e + 1;
            }
            initialEnergy -= e;
            e = experience[i];
            if (initialExperience <= e) {
                ans += e + 1 - initialExperience;
                // 补到刚好超过
                initialExperience = e + 1;
            }
            initialExperience += e;
        }
        return ans;
    }
}
