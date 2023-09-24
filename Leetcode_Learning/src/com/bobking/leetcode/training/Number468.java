package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-05-29 9:30
 */
public class Number468 {

    public String validIPAddress(String queryIP) {

        if (queryIP.contains(".")) {

            if (queryIP.endsWith("."))
                return "Neither";

            String[] strs = queryIP.split("\\.");
            if (strs.length != 4)
                return "Neither";

            for (String s : strs) {
                if (s.length() < 1 || s.length() > 3)
                    return "Neither";

                if (s.startsWith("0") && s.length() != 1) {
                    return "Neither";
                } else if (!validNum(s)) {
                    return "Neither";
                }
            }
            return "IPv4";
        } else if (queryIP.contains(":")) {

            if (queryIP.endsWith(":"))
                return "Neither";

            String[] strs = queryIP.split(":");
            if (strs.length != 8)
                return "Neither";

            for (String s : strs) {
                if (s.length() < 1 || s.length() > 4)
                    return "Neither";

                if (!validHex(s))
                    return "Neither";
            }
            return "IPv6";
        }
        return "Neither";
    }

    private boolean validHex(String s) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!(ch >= '0' && ch <= '9' || ch >= 'a' && ch <= 'f' || ch >= 'A' && ch <= 'F'))
                return false;
        }
        return true;
    }

    private boolean validNum(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch < '0' || ch > '9')
                return false;

            res = res * 10 + ch - '0';
        }
        return res >= 0 && res <= 255;
    }
}
