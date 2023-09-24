package com.bobking.leetcode.training;

public class Number165 {

    // 参考：https://leetcode-cn.com/problems/compare-version-numbers/solution/shuang-100-c-by-mszjaas/
    public int compareVersion1(String version1, String version2) {

        if (version1 == null || version1.length() == 0)
            return (version2 == null || version2.length() == 0) ? 0 : -1;
        if (version2 == null || version2.length() == 0)
            return (version1 == null || version1.length() == 0) ? 0 : 1;

        int index1 = 0;
        int index2 = 0;
        int end = Math.max(version1.length(), version2.length());
        while (index1 < end || index2 < end) {

            int v1 = 0;
            int v2 = 0;

            while (index1 < version1.length() && version1.charAt(index1) != '.') {
                v1 = v1 * 10 + version1.charAt(index1) - '0';
                index1++;
            }

            while (index2 < version2.length() && version2.charAt(index2) != '.') {
                v2 = v2 * 10 + version2.charAt(index2) - '0';
                index2++;
            }

            if (v1 > v2){
                return 1;
            } else if (v1 < v2) {
                return -1;
            }

            index1++;
            index2++;
        }

        return 0;
    }

    // 参考：https://leetcode-cn.com/problems/compare-version-numbers/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-40/
    public int compareVersion2(String version1, String version2) {

        if (version1 == null || version1.length() == 0)
            return (version2 == null || version2.length() == 0) ? 0 : -1;
        if (version2 == null || version2.length() == 0)
            return (version1 == null || version1.length() == 0) ? 0 : 1;

        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        int m = v1.length;
        int n = v2.length;
        int i = 0;
        while (i < m || i < n) {
            // String str = "00009";
            // int num = Integer.parseInt(str);
            // 输出 9
            int a = i < m ? Integer.valueOf(v1[i]) : 0;
            int b = i < n ? Integer.valueOf(v2[i]) : 0;
            if (a == b) {
                i++;
                continue;
            }
            return Integer.compare(a, b);
        }

        return 0;
    }

}
