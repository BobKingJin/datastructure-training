package com.bobking.leetcode.training;

public class Number824 {

    public String toGoatLatin(String sentence) {

        int n = sentence.length();
        String last = "a";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ) {
            int j = i;
            // 找到一个单词的结尾部分
            while (j < n && sentence.charAt(j) != ' ')
                j++;
            // 元音
            if ("aeiouAEIOU".indexOf(sentence.charAt(i)) >= 0) {
                sb.append(sentence.substring(i, j)).append("ma");
            } else {
                // 辅音
                sb.append(sentence.substring(i + 1, j)).append(sentence.charAt(i)).append("ma");
            }
            sb.append(last);
            last += "a";
            i = j + 1;
            if (i < n)
                sb.append(" ");
        }

        return sb.toString();
    }
}
