package com.bobking.leetcode.training;

public class Number1108 {

    public String defangIPaddr(String address) {

        StringBuilder sb = new StringBuilder();
        int n = address.length();
        int idx = -1;

        while (++idx < n) {

            char c = address.charAt(idx);
            if (c == '.')
                sb.append('[');

            sb.append(c);

            if (c == '.')
                sb.append(']');
        }

        return sb.toString();
    }
}
