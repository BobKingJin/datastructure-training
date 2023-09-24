package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-07 20:41
 */
public class Number1433 {

    public boolean checkIfCanBreak(String s1, String s2) {

        int[] arr1 = new int[26];
        int[] arr2 = new int[26];
        // 遍历 s1、s2 字符串
        for (int i = 0; i < s1.length(); i++)
            arr1[s1.charAt(i) - 'a']++;

        for (int i = 0; i < s2.length(); i++)
            arr2[s2.charAt(i) - 'a']++;

        // 这里进行了两次遍历，一次检测 arr1 是否恒 >= arr2，另一次检测 arr1 是否恒 <= arr2  可以做进一步优化
        int cur = 0;
        boolean flag = true;
        for (int i = 0; i < 26; i++) {
            cur += arr1[i] - arr2[i];
            if (cur < 0)
                flag = false;
        }
        // 遍历后，恒 >= 0，即返回 true
        if (flag)
            return true;

        // 经过一轮遍历后，cur必然为 0，无需重新赋初值
        flag = true;
        for (int i = 0; i < 26; i++) {
            cur += arr1[i] - arr2[i];
            if (cur > 0)
                flag = false;
        }
        // 遍历后，恒 <= 0，即返回 true，否则返回 false
        return flag;
    }
}
