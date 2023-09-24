package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author BobKing
 * @create 2021-04-30 11:25
 */
public class Number354 {

    private class Envelope {

        public int length;
        public int width;

        public Envelope(int length, int width) {
            this.length = length;
            this.width = width;
        }
    }

    private class EnvelopeComparator implements Comparator<Envelope> {

        @Override
        public int compare(Envelope o1, Envelope o2) {
            return o1.length != o2.length ? o1.length - o2.length : o2.width - o1.width;
        }
    }

    private Envelope[] getSortedEnvelopes(int[][] matrix) {

        Envelope[] envelopes = new Envelope[matrix.length];
        for (int i = 0; i < envelopes.length; i++)
            envelopes[i] = new Envelope(matrix[i][0], matrix[i][1]);

        Arrays.sort(envelopes, new EnvelopeComparator());
        return envelopes;
    }

    // 参考：程序猿代码指南P214
    public int maxEnvelopes1(int[][] envelopes) {

        if (envelopes == null || envelopes.length == 0)
            return 0;

        Envelope[] e = getSortedEnvelopes(envelopes);
        int[] ends = new int[envelopes.length];
        ends[0] = e[0].width;
        int right = 0;

        int l = 0;
        int r = 0;
        int mid = 0;

        for (int i = 1; i < e.length; i++) {

            l = 0;
            r = right;
            while (l <= r) {
                mid = (l + r) / 2;
                if (e[i].width > ends[mid]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }

            right = Math.max(right, l);
            ends[l] = e[i].width;
        }

        return right + 1;
    }

    // 参考：程序猿代码指南P214
    public int maxEnvelopes2(int[][] envelopes) {

        if (envelopes == null || envelopes.length == 0)
            return 0;

        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1];
            }
        });

        int[] ends = new int[envelopes.length];
        ends[0] = envelopes[0][1];
        int right = 0;

        int l = 0;
        int r = 0;
        int mid = 0;

        for (int i = 1; i < envelopes.length; i++) {

            l = 0;
            r = right;
            while (l <= r) {
                mid = (l + r) / 2;
                if (envelopes[i][1] > ends[mid]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }

            right = Math.max(right, l);
            ends[l] = envelopes[i][1];
        }

        return right + 1;
    }

}
