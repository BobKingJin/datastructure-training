package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-12-13 11:02
 */
public class Number1832 {

    // 参考：https://leetcode.cn/problems/check-if-the-sentence-is-pangram/solution/wei-yun-suan-zhi-shu-ji-shu-zhi-neng-zai-mu0c/
    public boolean checkIfPangram(String sentence) {

        // 把每一个字母映射到一个 int 值的二进制位上，最后与二十六个字母全满时候的状态做对比即可

        int res = 0;

        for (char c : sentence.toCharArray()) {
            res |= 1 << (c - 'a');
            if ((res ^ 0x3ffffff) == 0)
                return true;
        }
        return false;
    }
}
