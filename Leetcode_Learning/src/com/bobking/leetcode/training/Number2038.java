package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-03-22 14:34
 */
public class Number2038 {

    // 参考：https://leetcode-cn.com/problems/remove-colored-pieces-if-both-neighbors-are-the-same-color/solution/gong-shui-san-xie-nao-jin-ji-zhuan-wan-y-a8xm/
    public boolean winnerOfGame(String colors) {

        if(colors == null || colors.length() < 3)
            return false;

        char[] ch = colors.toCharArray();

        // 根据删除规则，删除任意一个 A 不会影响可被删除的 B 的数量，反之亦然
        // 因此直接统计「可删除的 A 的数量」和「可删除的 B 的数量」，分别记为 a 和 b
        // 比较 a 和 b 的大小即可得到答案（只有 a > b 时，先手获胜）

        int n = ch.length;
        int aCount = 0;
        int bCount = 0;

        for (int i = 1; i < n - 1; i++) {
            if (ch[i] == 'A' && ch[i - 1] == 'A' && ch[i + 1] == 'A')
                aCount++;
            if (ch[i] == 'B' && ch[i - 1] == 'B' && ch[i + 1] == 'B')
                bCount++;
        }

        return aCount > bCount;
    }
}
