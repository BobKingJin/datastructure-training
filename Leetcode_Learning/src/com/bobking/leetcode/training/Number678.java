package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-05-09 21:23
 */
public class Number678 {

    // 参考：程序猿代码指南P290
    public boolean checkValidString1(String s) {

        if (s == null || "".equals(s))
            return false;

        char[] ch = s.toCharArray();
        int status = 0;

        for (int i = 0; i < ch.length; i++) {

            if (ch[i] != '(' && ch[i] != ')')
                return false;

            if (ch[i] == '(')
                status++;
            if (ch[i] == ')' && --status < 0)
                return false;
        }

        return status == 0;
    }

    // 参考：https://leetcode-cn.com/problems/valid-parenthesis-string/solution/you-xiao-de-gua-hao-zi-fu-chuan-by-leetc-osi3/
    public boolean checkValidString2(String s) {

        if (s == null || "".equals(s))
            return false;

        int n = s.length();
        // dp[i][j] 表示字符串 s 从下标 i 到 j 的子串是否为有效的括号字符串，其中 0 <= i < j < n
        boolean[][] dp = new boolean[n][n];
        // 边界情况是子串的长度为 1 或 2 的情况
        // 当子串的长度为 1 时，只有当该字符是 * 时，才是有效的括号字符串
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '*')
                dp[i][i] = true;
        }
        // 当子串的长度为 2 时，只有当两个字符是 (), (*, *), ** 中的一种情况时，才是有效的括号字符串
        for (int i = 1; i < n; i++) {
            char c1 = s.charAt(i - 1);
            char c2 = s.charAt(i);
            dp[i - 1][i] = (c1 == '(' || c1 == '*') && (c2 == ')' || c2 == '*');
        }

        // 当子串的长度大于 2 时，需要根据子串的首尾字符以及中间的字符判断子串是否为有效的括号字符串
        // 字符串 s 从下标 i 到 j 的子串的长度大于 2 即 j - i >= 2，此时 dp[i][j] 只要满足以下一个条件就有 dp[i][j] = true

        for (int i = n - 3; i >= 0; i--) {
            char c1 = s.charAt(i);
            for (int j = i + 2; j < n; j++) {
                char c2 = s.charAt(j);
                // 如果 s[i] 和 s[j] 分别为 ( 和 ) ，或者为 *，则当 dp[i + 1][j − 1] = true 时，dp[i][j] = true
                if ((c1 == '(' || c1 == '*') && (c2 == ')' || c2 == '*'))
                    dp[i][j] = dp[i + 1][j - 1];

                // 如果存在 i <= k < j 使得 dp[i][k] 和 dp[k + 1][j] 都为 true，则 dp[i][j] = true
                for (int k = i; k < j && !dp[i][j]; k++)
                    dp[i][j] = dp[i][k] && dp[k + 1][j];
            }
        }

        return dp[0][n - 1];
    }

    public boolean checkValidString3(String s) {

        if (s == null || "".equals(s))
            return false;

        int l = 0;
        int r = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                l++;
                r++;
            } else if (c == ')') {
                l--;
                r--;
            } else {
                l--;
                r++;
            }
            l = Math.max(l, 0);
            if (l > r)
                return false;
        }

        return l == 0;
    }

    public boolean checkValidString4(String s) {

        // 两次遍历 第一次遍历：从前往后遍历确定')'，排除类似'*))*'的情况
        //         第二次遍历：从后往前遍历确定'('，排除类似'*((*'的情况
        // 其余的都符合
        // 第一次遍历结束后，排除了‘ *))* ’的情况，表示')'都能够符合要求了，但此时排除不了’*((*‘的情况，确定不了’(‘
        // 这时候从右往左遍历，可以排除类似’*((*‘的情况,用于确定'('
        // 更新 m = 0，第一次记录的当前遍历点左侧的*数目，用于抵消不能被左括号抵消的右括号，一旦小于0，代表出现了 *))*的情况
        // 第二次记录的当前遍历点右侧的*数目，用于抵消不能被右括号抵消的左括号
        // 就是一个*，只会被某一侧使用，或者直接不使用，不会存在被两侧同时使用的情况
        // 从左往右遍历时候，*要在右括号的左侧，即‘ *) ’，
        // 从右往左遍历时候，*要在左括号的右侧，即‘ (* ’
        // 如果一个星号同时被使用的话，只能是‘ （*）’，按照代码的逻辑，左右括号相互抵消了，*是不会改变的
        char[] ch = s.toCharArray();

        // 左括号数量
        int l = 0;
        int m = 0;

        for(int i = 0; i < s.length(); ++i){
            if(ch[i] == '('){
                l++;
            } else if(ch[i] == ')'){
                l--;
            } else{
                m++;
            }

            if(l < 0){
                m--;
                l++;
            }

            if(m < 0){
                return false;
            }
        }

        int r = 0;
        m = 0;
        for(int i = s.length() - 1; i >= 0; --i){
            if(ch[i] == ')'){
                r++;
            } else if(ch[i] == '('){
                r--;
            } else{
                m++;
            }
            if(r < 0){
                m--;
                r++;
            }
            if(m < 0){
                return false;
            }
        }
        return true;
    }




}
