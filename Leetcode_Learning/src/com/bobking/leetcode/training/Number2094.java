package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-11-06 11:29
 */
public class Number2094 {

    public int[] findEvenNumbers(int[] digits) {

        // 统计 0 ~ 9 各个数字的出现次数
        int[] count = new int[10];
        for (int digit : digits) {
            count[digit]++;
        }

        // 各个位置选取之后统计结果减一，完成当前遍历后恢复（统计结果再加一）
        List<Integer> list = new ArrayList<Integer>();

        // 百位按 1 ~ 9 取存在的数字（保证了顺序且不重复）
        for (int i = 1; i < 10; i++) {
            if (count[i] > 0) {
                count[i]--;
                // 十位取剩余 0 ~ 9 中存在的数字（保证了顺序且不重复）
                for (int j = 0; j < 10; j++) {
                    if (count[j] > 0) {
                        count[j]--;
                        // 个位取剩余存在的偶数（保证了顺序且不重复）
                        for (int k = 0; k < 10; k += 2) {
                            if (count[k] > 0) {
                                list.add(i * 100 + j * 10 + k);
                            }
                        }
                        count[j]++;
                    }
                }
                count[i]++;
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
}
