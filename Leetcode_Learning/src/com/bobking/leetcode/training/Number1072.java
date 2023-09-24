package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2023-04-02 19:56
 */
public class Number1072 {

    // 参考：https://leetcode.cn/problems/flip-columns-for-maximum-number-of-equal-rows/solution/xun-zhao-ju-you-xiang-tong-de-te-zheng-de-xing-de-/
    public int maxEqualRowsAfterFlips(int[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        Map<String, Integer> map = new HashMap<String, Integer>();
        boolean firstZero = false;
        int res = 0;

        for (int i = 0, len = matrix.length; i < len; i++) {
            if (matrix[i][0] == 0) {
                firstZero = true;
            } else {
                firstZero = false;
            }
            StringBuilder temp = new StringBuilder();
            for (int j = 0, colLen = matrix[i].length; j < colLen; j++) {
                if (firstZero) {
                    temp.append(matrix[i][j]);
                } else {
                    temp.append((matrix[i][j] ^ 1));
                }
            }
            String tempStr = temp.toString();
            res = Math.max(map.getOrDefault(tempStr, 0) + 1, res);
            map.put(tempStr, map.getOrDefault(tempStr, 0) + 1);
        }
        return res;
    }
}
