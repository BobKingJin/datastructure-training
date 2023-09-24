package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

public class Number784 {

    // 参考：https://leetcode-cn.com/problems/letter-case-permutation/solution/shen-du-you-xian-bian-li-hui-su-suan-fa-python-dai/
    public List<String> letterCasePermutation1(String S) {

        List<String> res = new ArrayList<>();

        if (S == null || "".equals(S) || S.length() == 0)
            return res;

        char[] charArray = S.toCharArray();
        dfs(charArray, 0, res);
        return res;
    }

    // 注意这题是可以多个位置同时改变的，并没有规定只有一个位置可以改变
    // 例如：a1b2 -> A1B2 是可以同时改变 a b 两个字符的  因此这道题是不需要回溯的
    private void dfs(char[] charArray, int index, List<String> res) {

        if (index == charArray.length) {
            res.add(new String(charArray));
            return;
        }

        // 不改变 index 位置的数
        dfs(charArray, index + 1, res);
        if (Character.isLetter(charArray[index])) {
            // 大写字符与其对应的小写字符的 ASCII 的差为 32 = 2^5，即可以表示为 1 << 5
            // 变换大小写这件事等价于：
            //                      如果字符是小写字符，减去 32 得到大写字符
            //                      如果字符是大写字符，加上 32 得到小写字符
            // 而这两者合并起来，就是给这个字符做一次不进位的加法，即异或上 1 << 5
            charArray[index] ^= 1 << 5;
            dfs(charArray, index + 1, res);
        }
    }

    List<String> res = new ArrayList<String>();

    // 参考：https://leetcode-cn.com/problems/letter-case-permutation/solution/zi-mu-da-xiao-xie-quan-pai-lie-by-leetcode/
    public List<String> letterCasePermutation2(String S) {

        if (S == null || "".equals(S) || S.length() == 0)
            return res;

        char[] chs = S.toCharArray();
        dfs(chs, 0, chs.length);
        return res;
    }

    private void dfs(char[] chs, int index, int length) {

        // 没有改变 index 的位置
        res.add(new String(chs));

        for (int i = index; i < length; i++) {
            if (!Character.isDigit(chs[i])) {
                char tmp = chs[i];
                chs[i] = (char) (chs[i] - 'a' >= 0 ? chs[i] - 32 : chs[i] + 32);
                dfs(chs, i + 1, length);
                chs[i] = tmp;
            }
        }
    }

}
