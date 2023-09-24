package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-06 9:41
 */
public class Number1678 {

    public String interpret1(String command) {
        command = command.replace("()", "o");
        command = command.replace("(al)", "al");
        return command;
    }

    public String interpret2(String command) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i) == 'G') {
                sb.append('G');
            } else if (command.charAt(i) == '(' && command.charAt(i + 1) == ')') {
                sb.append("o");
                i++;
            } else {
                sb.append("al");
                i += 3;
            }
        }
        return sb.toString();
    }
}
